package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.mvp.model.bean.custom.CitySortData;
import org.zzy.driver.mvp.model.bean.response.ResponseCityList;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.ChooseCityActivity;
import org.zzy.driver.utils.CharacterParser;
import org.zzy.driver.utils.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhou on 2018/6/8.
 */

public class ChooseCityPresenter extends BasePresenter<ChooseCityActivity> implements HttpCallBack {

    public void getCityList(){
        BusinessApi api=new BusinessApi();
        api.getCityList(this);
    }


    /**
     * 用服务器返回的数据来构造城市列表中需要的数据
     * */
    public List<CitySortData> filledData(List<ResponseCityList> cityList){
        List<CitySortData> sortList=new ArrayList<>();
        CharacterParser characterParser=new CharacterParser();
        for (ResponseCityList city : cityList) {
            CitySortData citySort=new CitySortData();
            citySort.setName(city.getName());
            citySort.setNameCode(city.getCode());
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
                sort(filledData(cityList));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
