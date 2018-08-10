package org.zzy.driver.mvp.ui.activity.wallet;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.SetPayPasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/15.
 */

public class SetPayPasswordActivity extends BaseActivity<SetPayPasswordPresenter> {

    @BindView(R.id.et_idCard)
    EditText etIdCard;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getCode)
    TextView tvGetCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public SetPayPasswordPresenter newPresenter() {
        return new SetPayPasswordPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setpaypassword;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("设置支付密码");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 点击提交按钮
     * */
    @OnClick(R.id.btn_submit)
    public void clickSubmit(){
        getPresenter().submit(etIdCard.getText().toString(),etPassword.getText().toString(),etCode.getText().toString());
    }

    /**
     * 点击发送验证码
     * */
    @OnClick(R.id.tv_getCode)
    public void clickSendSms(){
        getPresenter().getSmsCode();
    }

    /**
     * 倒数结束后，重新设置为发送验证码
     * */
    public void renewSms() {
        tvGetCode.setText("获取验证码");
    }

    /**
     * 是否能点击发送验证码
     */
    public void setEnabled(boolean isClick) {
        tvGetCode.setEnabled(isClick);
    }

    /**
     * 设置倒数时间
     * */
    public void setTime(String time) {
        tvGetCode.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().stopTimeCount();
    }

}
