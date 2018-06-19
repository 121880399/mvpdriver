package org.zzy.driver.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/15.
 */

public class PaySettingActivity extends BaseActivity {

    @BindView(R.id.rl_modifyPassword)
    RelativeLayout rlModifyPassword;
    @BindView(R.id.rl_findPassword)
    RelativeLayout rlFindPassword;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_paysetting;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("支付设置");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /**
     * 点击修改密码
     * */
    @OnClick(R.id.rl_modifyPassword)
    public void clickModifyPassword(){
        Router.newIntent(this)
                .to(UpdatePayPasswordActivity.class)
                .launch();
    }

    /**
     * 点击找回密码
     * */
    @OnClick(R.id.rl_findPassword)
    public void clickFindPassword(){
        Router.newIntent(this)
                .to(ResetPayPasswordActivity.class)
                .launch();
    }
}
