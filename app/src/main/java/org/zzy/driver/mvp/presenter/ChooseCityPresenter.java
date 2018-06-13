package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.BaseApplication;
import org.zzy.driver.mvp.model.bean.custom.CitySortData;
import org.zzy.driver.mvp.model.bean.custom.CityUseHistory;
import org.zzy.driver.mvp.model.bean.custom.CityUseHistory_;
import org.zzy.driver.mvp.model.bean.response.ResponseCityList;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.ChooseCityActivity;
import org.zzy.driver.utils.CharacterParser;
import org.zzy.driver.utils.PinyinComparator;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

/**
 * Created by zhou on 2018/6/8.
 */

public class ChooseCityPresenter extends BasePresenter<ChooseCityActivity> implements HttpCallBack {

    private Box<CityUseHistory> mBox;
    private List<CitySortData> citySortData;
    //汉子转换为拼音
    private CharacterParser characterParser;

    public ChooseCityPresenter(){
        mBox= BaseApplication.getBoxStore().boxFor(CityUseHistory.class);
        characterParser=CharacterParser.getInstance();
    }

    /**
     * 得到所有城市列表
     * */
    public void getCityList(){
        BusinessApi api=new BusinessApi();
        api.getCityList(this);
    }

    /**
     * @function 将选中的城市插入数据库，或者更新
     * */
    public void insertOrUpdate(CityUseHistory city){
        //先从数据库中查询城市，如果没有就插入，如果有的话就把count数量加1再更新数据库
        CityUseHistory searchCity = mBox.query().equal(CityUseHistory_.code, city.getCode())
                .equal(CityUseHistory_.createUser, city.getCreateUser()).build().findUnique();
        if(searchCity==null){
            mBox.put(city);
        }else{
            searchCity.setCount(searchCity.getCount()+1);
            mBox.put(searchCity);
        }
    }

    /**
     * 从数据库中得到常用的城市
     * */
    public void getFrequentlyCity(){
        ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
        Query<CityUseHistory> build=null;
        //用户没有登录则搜索userid为0
        if(userInfo!=null) {
            build= mBox.query().equal(CityUseHistory_.createUser, String.valueOf(userInfo.getId()))
                    .orderDesc(CityUseHistory_.count).build();
        }else{
            build=mBox.query().equal(CityUseHistory_.createUser, "0")
                    .orderDesc(CityUseHistory_.count).build();
        }
        getView().showFrequentlyCity(build.find(0,9));
    }




    /**
     * 用服务器返回的数据来构造城市列表中需要的数据
     * */
    public List<CitySortData> filledData(List<ResponseCityList> cityList){
        List<CitySortData> sortList=new ArrayList<>();
        for (ResponseCityList city : cityList) {
            CitySortData citySort=new CitySortData();
            citySort.setName(city.getName());
            citySort.setNameCode(city.getCode());
            citySort.setCenterLat(city.getCenterLat());
            citySort.setCenterLng(city.getCenterLng());
            citySort.setFullName(city.getFullName());
            citySort.setParentName(city.getParentName());
            citySort.setParentCode(city.getParentCode());
            //将城市中文转换为大写拼音
            String pinyin=characterParser.getSelling(city.getName()).substring(0,1).toUpperCase();
            if(pinyin.matches("[A-Z]")){
                citySort.setSortLetters(pinyin);
            }else{
                citySort.setSortLetters("#");
            }
            sortList.add(citySort);
        }
        return sortList;
    }


    /**
     * 根据输入框中的内容过滤数据
     * 如果搜索框为空，则城市列表恢复初始，否则过滤数据列表
     * */
    public void filterData(String filterStr){
        List<CitySortData> filterDataList=new ArrayList<>();
        if(TextUtils.isEmpty(filterStr)){
            filterDataList=citySortData;
        }else{
            //搜索字符串不为空时，首先要清空以前的数据，避免上次的数据存留
            filterDataList.clear();
            //遍历所有数据，找出与搜索字符串相关的数据
            for (CitySortData city: citySortData) {
                String name=city.getName();
                //遍历到重庆的时候，要做特殊处理，因为重庆是多音字
                if(TextUtils.equals(name,"重庆")){
                    if(name.indexOf(filterStr)!=-1 || "chongqing".startsWith(filterStr)){
                        filterDataList.add(city);
                    }
                }else{
                    if(name.indexOf(filterStr) != -1 || characterParser.getSelling(name).startsWith(filterStr)){
                        filterDataList.add(city);
                    }
                }
            }
        }
        sort(filterDataList);
        getView().updateCityList(filterDataList);
    }


    /**
     * 对每一个城市根据首字母排序
     * */
    private void sort(List<CitySortData> sortList){
        PinyinComparator pinyinComparator=new PinyinComparator();
        Collections.sort(sortList,pinyinComparator);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.GET_CITY_LIST_METHOD)) {
            JSONObject mainData = response.getMainData();
            try {
                List<ResponseCityList> cityList=JsonFactory.getJsonUtils().parseArray(mainData.getString("cityList"), ResponseCityList.class);
                citySortData = filledData(cityList);
                sort(citySortData);
                getView().showCityList(citySortData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
