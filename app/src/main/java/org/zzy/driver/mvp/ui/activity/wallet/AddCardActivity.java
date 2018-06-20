package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.request.RequestAddCard;
import org.zzy.driver.mvp.model.bean.response.ResponseCardInfo;
import org.zzy.driver.mvp.presenter.AddCardPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/15.
 */

public class AddCardActivity extends BaseActivity<AddCardPresenter> implements TextWatcher {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_bankCardNum)
    EditText etBankCardNum;
    @BindView(R.id.tv_bankName)
    TextView tvBankName;
    @BindView(R.id.et_verifyCode)
    EditText etVerifyCode;
    @BindView(R.id.tv_sendVerifyCode)
    TextView tvSendVerifyCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private RequestAddCard mAddCard;

    @Override
    public AddCardPresenter newPresenter() {
        return new AddCardPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addcard;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("绑定银行卡");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        etBankCardNum.addTextChangedListener(this);
        mAddCard=new RequestAddCard();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length()==10){
            getPresenter().getBankRelative(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 从服务器返回数据以后，显示到ui上
     * */
    public void setCardInfo(ResponseCardInfo cardInfo) {
        mAddCard.setBankCardCode(cardInfo.getCode());
        mAddCard.setBankCardName(cardInfo.getName());
        tvBankName.setText(cardInfo.getName());
    }


    /**
     * 点击发送验证码
     * */
    @OnClick(R.id.tv_sendVerifyCode)
    public void clickSendSms(){
        getPresenter().getBindSms();
    }

    /**
     * 点击提交按钮
     * */
    @OnClick(R.id.btn_submit)
    public void clickSumbit() {
        mAddCard.setBankCardNum(etBankCardNum.getText().toString());
        mAddCard.setVerifyCode(etVerifyCode.getText().toString());
        mAddCard.setUserName(etName.getText().toString());
        getPresenter().bindingBankCard(mAddCard);
    }


    /**
     * 是否能点击发送验证码
     * */
    public void setEnabled(boolean isClick) {
        tvSendVerifyCode.setEnabled(isClick);
    }

    /**
     * 设置倒数时间
     * */
    public void setTime(String time) {
        tvSendVerifyCode.setText(time);
    }

    /**
     * 倒数结束后，重新设置为发送验证码
     * */
    public void renewSms(){
        tvSendVerifyCode.setText("获取验证码");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().stopTimeCount();
    }

    /**
     * 显示绑定银行卡确认提醒
     * */
    public void showTip() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("请确认银行卡信息")
                .setMessage("持卡人姓名:"+mAddCard.getUserName()+"\n银行卡号:"+mAddCard.getBankCardNum()+"\n发卡行:"+mAddCard.getBankCardName())
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(AddCardActivity.this)
                                .to(MyBankCardActivity.class)
                                .launch();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }
}
