package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        getPresenter().withdraw(etMoney.getText().toString());
    }

    /**
     * 数据验证通过以后，输入支付密码
     * */
    public void inputPayPassword() {
        View passwordView = LayoutInflater.from(this).inflate(R.layout.dialog_inputpaypassword, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(passwordView);
        AlertDialog dialog = builder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);

    }
}
