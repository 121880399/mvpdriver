package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.StringUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.WithdrawPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/14.
 */

public class WithdrawActivity extends BaseActivity<WithdrawPresenter> {

    @BindView(R.id.tv_bankName)
    TextView tvBankName;
    @BindView(R.id.tv_cardNum)
    TextView tvCardNum;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_withdrawAccount)
    TextView tvWithdrawAccount;
    @BindView(R.id.tv_allWithdraw)
    TextView tvAllWithdraw;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;

    //允许提现最大额度
    private double mMaxWithdrawMoney;

    private GridPasswordView pswView;

    @Override
    public WithdrawPresenter newPresenter() {
        return new WithdrawPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("提现");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getPresenter().getBindingBankCard();
        getPresenter().getWalletInfo();
    }


    /**
     * 设置绑定银行卡的信息
     * */
    public void setBankCardInfo(String bankCardNum, String userName, String bankCardName) {
        tvBankName.setText(bankCardName);
        tvCardNum.setText(bankCardNum);
        tvUserName.setText(userName);
    }



    /**
     * 设置钱包信息
     * */
    public void setWalletInfo(double withdrawAccount) {
        mMaxWithdrawMoney=withdrawAccount;
        tvWithdrawAccount.setText(StringUtils.formatMoney(String.valueOf(withdrawAccount)));
    }


    /**
     *  点击全部提现按钮
     */
    @OnClick(R.id.tv_allWithdraw)
    public void clickALLWithdraw(){
        etMoney.setText(String.valueOf(mMaxWithdrawMoney));
    }

    /**
     * 点击提现按钮
     * */
    @OnClick(R.id.btn_withdraw)
    public void clickWithdraw(){
        getPresenter().withdrawVerify(etMoney.getText().toString());
    }

    /**
     * 数据验证通过以后，输入支付密码
     * */
    public void inputPayPassword(final String withdrawMoney) {
        View passwordView = LayoutInflater.from(this).inflate(R.layout.dialog_inputpaypassword, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(passwordView);
        final AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        ImageView ivClose = (ImageView) passwordView.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView moneyTv = (TextView) passwordView.findViewById(R.id.tv_money);
        moneyTv.setText(StringUtils.formatMoney(etMoney.getText().toString()));
        pswView = (GridPasswordView) passwordView.findViewById(R.id.pswView);
        pswView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if(psw.length()==6){
                    getPresenter().withdraw(withdrawMoney,psw);
                }
            }

            @Override
            public void onInputFinish(String psw) {

            }
        });
    }

    /**
     * 提现成功
     * */
    public void withdrawSuccess() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("提现成功")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(WithdrawActivity.this)
                                .to(WalletActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .launch();
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }

    /**
     * 显示密码错误
     * */
    public void showPasswordError() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("提现失败")
                .setMessage("支付密码错误")
                .setPositiveButton("重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(WithdrawActivity.this)
                                .to(SetPayPasswordActivity.class)
                                .launch();
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }


    /**
     * 清空密码
     * */
    public void clearPassword() {
        if(pswView!=null) {
            pswView.clearPassword();
        }
    }
}
