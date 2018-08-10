package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;

import com.jungly.gridpasswordview.GridPasswordView;
import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.UnbindingCardPresenter;

import butterknife.BindView;

/**
 * Created by zhou on 2018/6/15.
 */

public class UnbindingCardActivity extends BaseActivity<UnbindingCardPresenter> implements GridPasswordView.OnPasswordChangedListener {


    @BindView(R.id.pswView)
    GridPasswordView pswView;

    @Override
    public UnbindingCardPresenter newPresenter() {
        return new UnbindingCardPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_unbindingcard;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("解绑银行卡");
        getTopbar().setRightText("忘记密码");
        getTopbar().setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.newIntent(UnbindingCardActivity.this)
                        .to(ResetPayPasswordActivity.class)
                        .launch();
            }
        });

        pswView.setOnPasswordChangedListener(this);
    }

    @Override
    public void onTextChanged(String psw) {
        if(psw.length()==6){
            getPresenter().checkPayPassword(psw);
        }
    }

    @Override
    public void onInputFinish(String psw) {

    }

    /**
     * 显示解绑提示
     * */
    public void showUnbindingTip() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("将当前绑定的银行卡解绑")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    getPresenter().unBindingBankCard();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pswView.clearPassword();
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }


    /**
     * 跳转到钱包首页
     * */
    public void goWallet() {
        Router.newIntent(this)
                .to(WalletActivity.class)
                .launch();
    }

    /**
     * 显示密码错误提示
     * */
    public void showPasswordError() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("密码错误")
                .setPositiveButton("忘记密码", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     Router.newIntent(UnbindingCardActivity.this)
                             .to(ResetPayPasswordActivity.class)
                             .launch();
                    }
                })
                .setNegativeButton("重试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pswView.clearPassword();
                    }
                });
        AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setGravity(Gravity.CENTER);
    }
}
