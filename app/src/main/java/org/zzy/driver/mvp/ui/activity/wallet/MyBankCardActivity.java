package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.MyBankCardPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/14.
 */

public class MyBankCardActivity extends BaseActivity<MyBankCardPresenter> implements View.OnClickListener {

    @BindView(R.id.tv_bankName)
    TextView tvBankName;
    @BindView(R.id.tv_bankCardNum)
    TextView tvBankCardNum;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_unbind)
    TextView tvUnbind;
    @BindView(R.id.btn_changeCard)
    TextView btnChangeCard;

    @Override
    public MyBankCardPresenter newPresenter() {
        return new MyBankCardPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mycard;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("我的银行卡");
        getTopbar().setRightText("管理");
        getTopbar().setRightTextListener(this);
        getTopbar().setLeftImageListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getBindingBankCard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_actionBarLeft:
                onBackPressed();
                break;
            case R.id.tv_actionRight:
                showTipDialog();
                break;
        }
    }

    /**
     * 变更银行卡
     * */
    @OnClick(R.id.btn_changeCard)
    public void changeCard(){
        Router.newIntent(this)
                .to(InputPasswordActivity.class)
                .launch();
    }


    /**
     *解绑银行卡
     * */
    @OnClick(R.id.tv_unbind)
    public void unbindCard(){
        Router.newIntent(this)
                .to(UnbindingCardActivity.class)
                .launch();
    }


    /**
     * 显示绑定银行卡信息
     * */
    public void showCardInfo(String bankCardNum,String userName,String bankCardName){
        tvBankCardNum.setText(bankCardNum);
        tvBankName.setText(bankCardName);
        tvUserName.setText(userName);
    }

    private void showTipDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("解绑银行卡")
                .setMessage("您确定解绑当前银行卡？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(MyBankCardActivity.this)
                                .to(UnbindingCardActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                .launch();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
