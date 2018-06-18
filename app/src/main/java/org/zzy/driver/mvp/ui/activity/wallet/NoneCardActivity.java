package org.zzy.driver.mvp.ui.activity.wallet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.presenter.NoneCardPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/6/14.
 */

public class NoneCardActivity extends BaseActivity<NoneCardPresenter> {

    @BindView(R.id.btn_addCard)
    Button btnAddCard;

    @Override
    public NoneCardPresenter newPresenter() {
        return new NoneCardPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nonecard;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("我的银行卡");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    /**
     * 点击添加银行卡
     * */
    @OnClick(R.id.btn_addCard)
    public void clickAddCard() {
        getPresenter().verify();
    }

    public void promptSettingPassword() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.show();
        builder.setTitle("设置支付密码")
                .setMessage("您还未设置支付密码，是否设置？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Router.newIntent(NoneCardActivity.this)
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

    public void goInputPassword() {
        Router.newIntent(this)
                .to(InputPasswordActivity.class)
                .launch();
    }
}
