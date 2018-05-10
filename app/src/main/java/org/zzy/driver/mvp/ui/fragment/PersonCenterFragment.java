package org.zzy.driver.mvp.ui.fragment;

import com.zzy.quick.mvp.ui.BaseFragment;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.mvp.ui.activity.WalletActivity;

import butterknife.OnClick;

/**
 * Created by zhou on 2018/5/10.
 */

public class PersonCenterFragment extends BaseFragment {


    @Override
    public int getLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public void initData() {

    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public Object newPresenter() {
        return null;
    }

    /**
     * @function 点击钱包按钮
     * */
    @OnClick(R.id.tv_wallet)
    public void clickWallet(){
        Router.newIntent(getActivity()).to(WalletActivity.class).launch();
    }


    /**
     * @function 点击出售运力记录按钮
     * */
    @OnClick(R.id.tv_sellCapacityRecord)
    public void clickSellCapacityRecord(){

    }

    /**
     * @function 点击历史运单按钮
     * */
    @OnClick(R.id.tv_historyWaybill)
    public void clickHistoryWaybill(){

    }

    /**
     * @function 点击车辆管理按钮
     * */
    @OnClick(R.id.tv_vehicleManager)
    public void clickVehicleManager(){

    }

    /**
     * @function 点击关于我们按钮
     * */
    @OnClick(R.id.ll_about_us)
    public void clickAboutUs(){

    }

    /**
     * @function 点击联系客服按钮
     * */
    @OnClick(R.id.ll_contact_service)
    public void clickContactService(){

    }

    /**
     * @function 点击推荐有奖按钮
     * */
    @OnClick(R.id.ll_recommend)
    public void clickRecommend(){

    }

    /**
     * @function 点击更新按钮
     * */
    @OnClick(R.id.ll_update)
    public void clickUpdate(){

    }

    /**
     * @function 点击个人信息按钮
     * */
    @OnClick(R.id.ll_personInfo)
    public void clickPersonInfo(){

    }

    /**
     * @function 点击二维码按钮
     * */
    @OnClick(R.id.iv_code)
    public void clickCode(){

    }


}
