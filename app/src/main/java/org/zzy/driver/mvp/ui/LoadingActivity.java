package org.zzy.driver.mvp.ui;

import android.content.Intent;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.LoadingPresenter;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoadingActivity extends BaseActivity<LoadingPresenter> {


    @Override
    public LoadingPresenter newPresenter() {
        return new LoadingPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initData() {
        //判断是否登录，登录直接跳转主页面，没登录的要判断是否是第一次使用APP，如果第一次使用跳转到引导页面，否则跳转登录页面
        if (getPresenter().isLogin()){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            if(getPresenter().isFirstAccess()){
                getPresenter().setAlreadyAccess();
                startActivity(new Intent(this,UserGuideActivity.class));
            }else{
                startActivity(new Intent(this,LoginActivity.class));
            }
        }
        finish();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void showError(String msg) {

    }

}
