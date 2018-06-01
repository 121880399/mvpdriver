package org.zzy.driver.mvp.presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.SPUtils;
import com.zzy.quick.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.R;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUpdate;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.SystemApi;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.fragment.PersonCenterFragment;
import org.zzy.driver.utils.ChannelUtil;
import org.zzy.driver.utils.UserInfoUtils;

import java.io.File;

/**
 * Created by zhou on 2018/5/14.
 */

public class PersonCenterPresenter extends BasePresenter<PersonCenterFragment> implements HttpCallBack {

    public void checkUpdate() {
        SystemApi systemApi = new SystemApi();
        systemApi.checkUpdate(ChannelUtil.getVersionCode(getView().getActivity()), ChannelUtil.getVersionName(getView().getActivity()), this);
    }



    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.VERSION_ACTION) && method.equals(RequestCenter.CHECKVERSION)) {
            JSONObject mainData = response.getMainData();
            try {
                int hasNewVersion = mainData.getInt("hasNewVersion");
                if (hasNewVersion == 1) {
                    final ResponseUpdate update = JsonFactory.getJsonUtils().parseObject(mainData.getString("versionInfo"), ResponseUpdate.class);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getView().getActivity());
                    builder.setTitle("更新提示");
                    builder.setMessage(update.getUpdateContent());
                    //是否
                    if (update.getIsForce() == 1) {

                        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse(update.getUrl());
                                Router.newIntent(getView().getActivity())
                                        .setAction(Intent.ACTION_VIEW)
                                        .setUri(uri)
                                        .launch();
                            }
                        });
                    } else {
                        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse(update.getUrl());
                                Router.newIntent(getView().getActivity())
                                        .setAction(Intent.ACTION_VIEW)
                                        .setUri(uri)
                                        .launch();
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                    }
                    builder.show();
                } else {
                    ToastUtils.showShort("暂无更新！");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
