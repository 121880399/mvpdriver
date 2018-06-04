package org.zzy.driver.mvp.ui.activity;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;

/**
 * Created by zhou on 2018/5/10.
 */

public class WalletActivity extends BaseActivity {

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public void initData() {

    }

    @Override
    public void showError(String msg) {

    }
}
