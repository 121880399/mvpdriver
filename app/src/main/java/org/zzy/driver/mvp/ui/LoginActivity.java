package org.zzy.driver.mvp.ui;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.LoginPresenter;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoginActivity extends BaseActivity<LoginPresenter>{


    @Override
    public LoginPresenter newPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void showError(String msg) {

    }
}
