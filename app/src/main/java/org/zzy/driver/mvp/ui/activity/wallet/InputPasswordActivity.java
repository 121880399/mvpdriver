package org.zzy.driver.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;

import com.jungly.gridpasswordview.GridPasswordView;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.InputPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/6/15.
 */

public class InputPasswordActivity extends BaseActivity<InputPasswordPresenter> {


    @BindView(R.id.pswView)
    GridPasswordView pswView;

    @Override
    public InputPasswordPresenter newPresenter() {
        return new InputPasswordPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_inputpassword;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("输入支付密码");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if(psw.length()==6){
                    getPresenter().checkPayPassword(psw);
                }
            }

            @Override
            public void onInputFinish(String psw) {

            }
        });
    }

    /**
     * 跳转到添加银行卡
     * */
    public void goAddCard() {
        Router.newIntent(this)
                .to(AddCardActivity.class)
                .launch();
        finish();
    }
}
