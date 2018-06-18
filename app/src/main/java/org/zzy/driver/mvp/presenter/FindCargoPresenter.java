package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.BaseApplication;
import org.zzy.driver.mvp.model.bean.response.ResponseOrder;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.fragment.FindCargoFragment;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.utils.VehicleInfoUtils;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by zhou on 2018/5/29.
 */

public class FindCargoPresenter extends BasePresenter<FindCargoFragment> implements HttpCallBack {

    private Box<ResponseOrder> mBox;

    private List<ResponseOrder> mOrderDatas;

    public FindCargoPresenter(){
        mBox= BaseApplication.getBoxStore().boxFor(ResponseOrder.class);
    }

    //记录被抢订单
    private ResponseOrder accepterOrder;

    /**
     * 从数据库中读取所有的Order数据
     * */
    public List<ResponseOrder> loadOrderData(){
        mOrderDatas=mBox.getAll();
        return mOrderDatas;
    }

    /**
     * 从数据库中删除订单
     * */
    public void deleteOrder(ResponseOrder order){
        mBox.remove(order);
    }

    /**
     * 抢单
     * */
    public void acceptOrder(ResponseOrder order){
        ResponseVehicle vehicleInfo = VehicleInfoUtils.getVehicleInfo();
        if(vehicleInfo==null){
            ToastUtils.showShort("您还没有绑定车辆，请先添加默认车辆");
            getView().goVehicleManager();
        }else{
            //如果通过审核
            if (UserInfoUtils.getUserInfo().getIdentStatus() == 2 && UserInfoUtils.getUserInfo().getQuaStatus() == 2) {
                accepterOrder=order;
                BusinessApi api=BusinessApi.getInstance();
                api.acceptOrder(UserInfoUtils.getUserInfo().getDriverId()
                        ,Integer.valueOf(order.getCapacity_apply_order_id())
                        ,Integer.valueOf(order.getWaybill_group_id())
                        , VehicleInfoUtils.getVehicleInfo().getSupport_40gp()
                        ,this);

            } else {
                ToastUtils.showShort("资料审核中，暂时无法抢单");
            }
        }

    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WAYBILL_ACTION) && method.equals(RequestCenter.ACCESSORDER_METHOD)){
            getView().showSuccess("抢单成功");
            deleteOrder(accepterOrder);
            getView().updateDate(loadOrderData());
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.WAYBILL_ACTION) && method.equals(RequestCenter.ACCESSORDER_METHOD)) {
            deleteOrder(accepterOrder);
            getView().updateDate(loadOrderData());
            getView().showError("抢单失败，该订单已被抢！");
        }
    }
}
