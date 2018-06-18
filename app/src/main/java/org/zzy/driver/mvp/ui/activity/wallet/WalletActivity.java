package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.WalletPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/5/10.
 */

public class WalletActivity extends BaseActivity<WalletPresenter> {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_incomeList)
    TextView tvIncomeList;
    @BindView(R.id.tv_generalIncome)
    TextView tvGeneralIncome;
    @BindView(R.id.tv_waitConfirmedIncome)
    TextView tvWaitConfirmedIncome;
    @BindView(R.id.tv_withdrawAccount)
    TextView tvWithdrawAccount;
    @BindView(R.id.rl_mycard)
    RelativeLayout rlMycard;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rl_paySetting)
    RelativeLayout rlPaySetting;
    @BindView(R.id.btn_withdraw)
    Button btnWithdraw;


    @Override
    public WalletPresenter newPresenter() {
        return new WalletPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        getPresenter().getWalletInfo();
    }

    /**
     * 显示获取到的钱包数据
     */
    public void showData(double generalIncome, double waitConfirmedIncome, double withdrawAccount) {
        tvGeneralIncome.setText(String.valueOf(generalIncome));
        tvWaitConfirmedIncome.setText(String.valueOf(waitConfirmedIncome));
        tvWithdrawAccount.setText(String.valueOf(withdrawAccount));
    }

    /**
     * 点击我的银行卡
     * */
    @OnClick(R.id.rl_mycard)
    public void clickMyCard(){
        getPresenter().verifyData(WalletPresenter.MYBANKCARD);
    }

    /**
     * 点击返回按钮
     * */
    @OnClick(R.id.iv_back)
    public void clickBack(){
        onBackPressed();
    }

    /**
     * 点击支付设置
     * */
    @OnClick(R.id.rl_paySetting)
    public void clickPaySetting(){
        getPresenter().paySettingVerify();
    }

    /**
     * 点击收入明细
     * */
    @OnClick(R.id.tv_incomeList)
    public void clickPayList(){
        Router.newIntent(this)
                .to(IncomeListActivity.class)
                .launch();
    }

    /**
     * 提示绑定银行卡
     * */
    public void promptBoundCard(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.show();
        builder.setTitle("绑定银行卡")
                .setMessage("您还未绑定银行卡，是否绑定？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(WalletActivity.this)
                                .to(NoneCardActivity.class)
                                .launch();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }


    /**
     * 跳转到我的银行卡界面
     * */
    public void goMyBankCard() {
        Router.newIntent(this)
                .to(MyBankCardActivity.class)
                .launch();
    }

    /**
     * 跳转到提现界面
     * */
    public void goWithdraw() {
        Router.newIntent(this)
                .to(WithdrawActivity.class)
                .launch();
    }


    /**
     * 提示设置密码
     * */
    public void promptSettingPassword() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.show();
        builder.setTitle("设置支付密码")
                .setMessage("您还未设置支付密码，是否设置？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(WalletActivity.this)
                                .to(SetPayPasswordActivity.class)
                                .launch();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }

    public void goPaySetting() {
        Router.newIntent(this)
                .to(PaySettingActivity.class)
                .launch();
    }
}
