package org.zzy.driver.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseCityList;
import org.zzy.driver.mvp.presenter.ChooseCityPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/6/4.
 */

public class ChooseCityActivity extends BaseActivity<ChooseCityPresenter> {

    @BindView(R.id.et_cityName)
    EditText etCityName;
    @BindView(R.id.lv_data)
    ListView lvData;

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
        String title=getIntent().getStringExtra("title");
        getTopbar().setTitle(title);
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getPresenter().getCityList();
    }

    @Override
    public void showError(String msg) {

    }



}
