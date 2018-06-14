package org.zzy.driver.mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.utils.QrUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @fucntion 显示二维码界面
 * Created by zhou on 2018/5/11.
 */

public class QRCodeActivity extends BaseActivity {

    @BindView(R.id.iv_qr)
    ImageView ivQr;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    public void initData() {
        String title = getIntent().getStringExtra("title");
        String code = getIntent().getStringExtra("code");
        getTopbar().setTitle(title);
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        QrUtils.createQRImage(code, this,ivQr);
    }

}
