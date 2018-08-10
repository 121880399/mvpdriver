package org.zzy.driver.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.UpdatePayPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/19.
 */

public class UpdatePayPasswordActivity extends BaseActivity<UpdatePayPasswordPresenter> {

    @BindView(R.id.et_oldPassword)
    EditText etOldPassword;
    @BindView(R.id.iv_clearoldPassword)
    ImageView ivClearoldPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_newPassword)
    ImageView ivNewPassword;
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
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;

    @Override
    public UpdatePayPasswordPresenter newPresenter() {
        return new UpdatePayPasswordPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatepaypassword;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("修改支付密码");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 清空原支付密码
     * */
    @OnClick(R.id.iv_clearoldPassword)
    public void clearOldPassword(){
        etOldPassword.setText("");
    }


    /**
     * 清空新支付密码
     * */
    @OnClick(R.id.iv_newPassword)
    public void clearNewPassword(){
        etPassword.setText("");
    }


    /**
     * 清空确认密码
     * */
    @OnClick(R.id.iv_clearConfirmPassword)
    public void clearConfirmPassword(){
        etConfirmPassword.setText("");
    }

    /**
     * 点击提交按钮
     * */
    @OnClick(R.id.btn_submit)
    public void clickSubmit(){
        getPresenter().submit(etOldPassword.getText().toString(),etPassword.getText().toString(),etConfirmPassword.getText().toString(),etCode.getText().toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().stopTimeCount();
    }


    /**
     * 更改密码成功
     * */
    public void changeSuccess() {
        ToastUtils.showShort("修改支付密码成功！");
    }
}
