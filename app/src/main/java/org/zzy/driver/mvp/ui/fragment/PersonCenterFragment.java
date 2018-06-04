package org.zzy.driver.mvp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.image.ImageFactory;
import com.zzy.quick.mvp.ui.BaseFragment;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.StatusBarUtils;

import org.zzy.driver.R;
import org.zzy.driver.common.AppConfig;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.presenter.PersonCenterPresenter;
import org.zzy.driver.mvp.ui.activity.HistoryWaybillActivity;
import org.zzy.driver.mvp.ui.activity.PersonInfoActivity;
import org.zzy.driver.mvp.ui.activity.QRCodeActivity;
import org.zzy.driver.mvp.ui.activity.RecommendActivity;
import org.zzy.driver.mvp.ui.activity.SellCapacityRecordActivity;
import org.zzy.driver.mvp.ui.activity.VehicleManagerActivity;
import org.zzy.driver.mvp.ui.activity.WalletActivity;
import org.zzy.driver.mvp.ui.activity.WebViewLoadActivity;
import org.zzy.driver.utils.QrUtils;
import org.zzy.driver.utils.UserInfoUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/5/10.
 */

public class PersonCenterFragment extends BaseFragment<PersonCenterPresenter> {


    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_code)
    ImageView ivCode;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public void initData() {
        //登录名的初始化设置
        ResponseUserInfo userInfo=UserInfoUtils.getUserInfo();
        if(userInfo==null){
            tvName.setText("未登录");
        }else{
            if(TextUtils.isEmpty(userInfo.getReal_name())){
                tvName.setText(userInfo.getPhone());
            }else{
                tvName.setText(userInfo.getReal_name());
            }
        }
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        StatusBarUtils.setImage(getActivity());
        //初始化二维码
        QrUtils.createQRImage(String.valueOf(UserInfoUtils.getUserInfo().getId()), getActivity(),ivCode);
        showAvatar();
    }

    /**
     * 显示头像
     * */
    public void showAvatar(){
        ImageFactory.getImageLoader()
                .loadCircleImage(AppConfig.IMAGE_URL + UserInfoUtils.getUserInfo().getIcon(),ivAvatar,R.drawable.img_default_avatar);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public PersonCenterPresenter newPresenter() {
        return new PersonCenterPresenter();
    }

    /**
     * @function 点击钱包按钮
     */
    @OnClick(R.id.tv_wallet)
    public void clickWallet() {
        Router.newIntent(getActivity()).to(WalletActivity.class).launch();
    }


    /**
     * @function 点击出售运力记录按钮
     */
    @OnClick(R.id.tv_sellCapacityRecord)
    public void clickSellCapacityRecord() {
        Router.newIntent(getActivity()).to(SellCapacityRecordActivity.class).launch();
    }

    /**
     * @function 点击历史运单按钮
     */
    @OnClick(R.id.tv_historyWaybill)
    public void clickHistoryWaybill() {
        Router.newIntent(getActivity()).to(HistoryWaybillActivity.class).launch();
    }

    /**
     * @function 点击车辆管理按钮
     */
    @OnClick(R.id.tv_vehicleManager)
    public void clickVehicleManager() {
        Router.newIntent(getActivity()).to(VehicleManagerActivity.class).launch();
    }

    /**
     * @function 点击关于我们按钮
     */
    @OnClick(R.id.ll_about_us)
    public void clickAboutUs() {
        Router.newIntent(getActivity()).to(WebViewLoadActivity.class)
                .putString("url","http://60.205.166.221:8084/apprest/about.jsp")
                .putString("title","关于")
                .launch();
    }

    /**
     * @function 点击联系客服按钮
     */
    @OnClick(R.id.ll_contact_service)
    public void clickContactService() {
        Router.newIntent(getActivity())
                .setAction(Intent.ACTION_DIAL)
                .setUri(Uri.parse("tel:" + AppConfig.CALLCENTER))
                .launch();
    }

    /**
     * @function 点击推荐有奖按钮
     */
    @OnClick(R.id.ll_recommend)
    public void clickRecommend() {
        Router.newIntent(getActivity())
                .to(RecommendActivity.class)
                .launch();
    }

    /**
     * @function 点击更新按钮
     */
    @OnClick(R.id.ll_update)
    public void clickUpdate() {
        getPresenter().checkUpdate();
    }

    /**
     * @function 点击个人信息按钮
     */
    @OnClick(R.id.ll_personInfo)
    public void clickPersonInfo() {
        Router.newIntent(getActivity())
                .to(PersonInfoActivity.class)
                .launch();
    }

    /**
     * @function 点击二维码按钮
     */
    @OnClick(R.id.iv_code)
    public void clickCode() {
        Router.newIntent(getActivity())
                .to(QRCodeActivity.class)
                .putString("code", String.valueOf(UserInfoUtils.getUserInfo().getId()))
                .putString("title", "二维码")
                .launch();
    }


}
