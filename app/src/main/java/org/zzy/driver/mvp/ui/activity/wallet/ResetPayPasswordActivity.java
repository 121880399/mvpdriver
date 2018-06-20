package org.zzy.driver.mvp.ui.activity.wallet;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.ResetPayPasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 钱包-重置支付密码界面
 * Created by zhou on 2018/6/15.
 */

public class ResetPayPasswordActivity extends BaseActivity<ResetPayPasswordPresenter> {


    @BindView(R.id.et_idCard)
    EditText etIdCard;
    @BindView(R.id.iv_clearidCard)
    ImageView ivClearidCard;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_clearPassword)
    ImageView ivClearPassword;
    @BindView(R.id.et_confirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.iv_clearConfirmPassword)
    ImageView ivClearConfirmPassword;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getCode)
    TextView tvGetCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public ResetPayPasswordPresenter newPresenter() {
        return new ResetPayPasswordPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_resetpaypassword;
    }

    @Override
    public void initView() {
        super.initView();
    }


    @Override
    public void initData() {
        getTopbar().setTitle("重置支付密码");
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
        getPresenter().submit(etIdCard.getText().toString(),etPassword.getText().toString(),etConfirmPassword.getText().toString(),etCode.getText().toString());
    }

    /**
     * 重设支付密码成功
     * */
    public void resetSuccess(){
        ToastUtils.showShort("支付密码重置成功！");
        Router.newIntent(this)
                .to(WalletActivity.class)
                .launch();
    }

    /**
     * 点击清除身份证号
     * */
    @OnClick(R.id.iv_clearidCard)
    public void clickClearIdCard(){
        etIdCard.setText("");
    }

    /**
     * 点击清除密码
     * */
    @OnClick(R.id.iv_clearPassword)
    public void clickClearPassword(){
        etPassword.setText("");
    }

    /**
     * 点击清除确认密码
     * */
    @OnClick(R.id.iv_clearConfirmPassword)
    public void clickClearConfirmPassword(){
        etConfirmPassword.setText("");
    }

    /**
     * 点击发送验证码
     * */
    @OnClick(R.id.tv_getCode)
    public void clickSendSms(){
        getPresenter().getSmsCode();
    }


    /**
     * 是否能点击发送验证码
     * */
    public void setEnabled(boolean isClick) {
        tvGetCode.setEnabled(isClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().stopTimeCount();
    }

    /**
     * 倒数结束后，重新设置为发送验证码
     * */
    public void renewSms() {
        tvGetCode.setText("获取验证码");
    }

    /**
     * 设置倒数时间
     * */
    public void setTime(String time) {
        tvGetCode.setText(time);
    }
}
