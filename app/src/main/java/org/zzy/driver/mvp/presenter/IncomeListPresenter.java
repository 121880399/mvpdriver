package org.zzy.driver.mvp.presenter;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.bean.response.ResponseIncome;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.IncomeListActivity;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.List;

/**
 * Created by zhou on 2018/6/19.
 */

public class IncomeListPresenter extends BasePresenter<IncomeListActivity> implements HttpCallBack {

    /**
     * 得到收入明细列表
     */
    public void getAccountBalanceList() {
        BusinessApi.getInstance().getAccountBalanceList(UserInfoUtils.getUserInfo().getDriverId(), this);
    }


    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_ACCOUNTBALANCELIST_METHOD)) {
            JSONObject mainData = response.getMainData();
                List<ResponseIncome> mIncomeDatas = JsonFactory.getJsonUtils().parseArray(mainData.getString("accountBalanceList"), ResponseIncome.class);
                getView().initAdapter(mIncomeDatas);
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
