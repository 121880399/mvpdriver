package org.zzy.driver.mvp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.KeyboardUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.custom.CitySortData;
import org.zzy.driver.mvp.model.bean.custom.CityUseHistory;
import org.zzy.driver.mvp.presenter.ChooseCityPresenter;
import org.zzy.driver.mvp.ui.adapter.CityListAdapter;
import org.zzy.driver.mvp.ui.adapter.FrequentlyCityAdapter;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.view.NoScrollGridView;
import org.zzy.driver.view.SideBar;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/6/4.
 */

public class ChooseCityActivity extends BaseActivity<ChooseCityPresenter> implements AdapterView.OnItemClickListener, TextWatcher {

    @BindView(R.id.et_cityName)
    EditText etCityName;
    @BindView(R.id.lv_cityList)
    ListView lvCityList;
    @BindView(R.id.sidebar)
    SideBar sidebar;
    @BindView(R.id.dialog)
    TextView dialog;

    private CityListAdapter cityListAdapter;
    private FrequentlyCityAdapter frequentlyCityAdapter;

    @Override
    public ChooseCityPresenter newPresenter() {
        return new ChooseCityPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_city;
    }

    @Override
    public void initData() {
        String title = getIntent().getStringExtra("title");
        getTopbar().setTitle(title);
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getPresenter().getFrequentlyCity();
        getPresenter().getCityList();
    }

    /**
     * @function 从数据库中查询常用城市数据后，显示UI
     * */
    public void showFrequentlyCity(List<CityUseHistory> cityUseHistories) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_frequentlycity_list, null);
        NoScrollGridView gridView = view.findViewById(R.id.gv_frequently);
        frequentlyCityAdapter = new FrequentlyCityAdapter(cityUseHistories, this);
        gridView.setAdapter(frequentlyCityAdapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setOnItemClickListener(this);
        lvCityList.addHeaderView(view);
        lvCityList.setOnItemClickListener(this);
    }

    /**
     * @fuction 从服务器得到返回的所有城市数据，并且排序后为显示做准备
     * */
    public void showCityList(List<CitySortData> citySortData) {
        cityListAdapter = new CityListAdapter(citySortData, this);
        lvCityList.setAdapter(cityListAdapter);
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //触摸sideBar上面的字母时，得到字母在城市列表中间的位置
                int position=cityListAdapter.getInitialIndex(s);
                if(position != -1){
                    lvCityList.setSelection(position);
                }
            }
        });
        //设置sidebar的弹出dialog,当点击字母时，屏幕中间会出现dialog提示效果
        sidebar.setTextView(dialog);
        //监听搜索框
        etCityName.addTextChangedListener(this);
    }


    /**
     *@fucntion 根据搜索字符串筛选完城市列表以后更新ui
     * */
    public void updateCityList(List<CitySortData> filterCityData){
        cityListAdapter.setCitySortDatas(filterCityData);
        cityListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityUseHistory cityhistory=null;
        switch (parent.getId()){
            case R.id.lv_cityList:
                //如果用户点击的是城市列表的item，则position是从1开始的，因为listview添加了header,所以取数据的时候要减1
                cityhistory=new CityUseHistory();
                CitySortData city = (CitySortData)cityListAdapter.getItem(position - 1);
                cityhistory.setCode(city.getNameCode());
                cityhistory.setName(city.getName());
                cityhistory.setUpdateTime(new Date());
                cityhistory.setCenterLat(String.valueOf(city.getCenterLat()));
                cityhistory.setCenterLng(String.valueOf(city.getCenterLng()));
                cityhistory.setFullCityName(city.getFullName());
                cityhistory.setParentName(city.getParentName());
                cityhistory.setCount(1);
                if(UserInfoUtils.getUserInfo()==null){
                    cityhistory.setCreateUser("0");
                }else{
                    cityhistory.setCreateUser(String.valueOf(UserInfoUtils.getUserInfo().getId()));
                }
                break;
            case R.id.gv_frequently:
                //如果用户点击的是常用城市，gridView没添加，所以正常从0开始
                cityhistory=(CityUseHistory)frequentlyCityAdapter.getItem(position);
                break;
        }
        getPresenter().insertOrUpdate(cityhistory);
        Intent intent = getIntent();
        intent.putExtra("city",cityhistory);
        setResult(RESULT_OK,intent);
        KeyboardUtils.hideSoftInput(this);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //返回的时候关闭软键盘
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        getPresenter().filterData(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
