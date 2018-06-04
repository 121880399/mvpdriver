package org.zzy.driver.mvp.ui.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.StatusBarUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forgetPassword)
    TextView tvForgetPassword;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.iv_passwordStatus)
    ImageView ivPasswordStatus;

    private boolean showPassword;

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
        //初始化不显示密码
        showPassword=false;
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setImage(this);
    }

    @Override
    public void showError(String msg) {

    }


    @OnClick(R.id.btn_login)
    public void clickLoginBtn(){
        getPresenter().login(etPhone.getText().toString(),etPassword.getText().toString());
    }

    @OnClick(R.id.tv_forgetPassword)
    public void clickForgetPassword(){
    }


    @OnClick(R.id.tv_register)
    public void clickRegister(){

    }

    /**
     * 显示还是隐藏密码
     * */
    @OnClick(R.id.iv_passwordStatus)
    public void showHidePassword(){
        if(showPassword){
            //如果当前是显示密码，就隐藏
            ivPasswordStatus.setImageResource(R.mipmap.ic_eye_close);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }else{
            //如果当期是隐藏密码，就显示
            ivPasswordStatus.setImageResource(R.mipmap.ic_eye);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        showPassword=!showPassword;
    }

    /**
     * 登录成功后跳转到主页
     * */
    public void goMain(){
        Router.newIntent(this)
                .to(MainActivity.class)
                .launch();
    }


}
