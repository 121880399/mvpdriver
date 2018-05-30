package org.zzy.driver.mvp.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zzy.quick.mvp.ui.BaseFragment;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.interf.OnRecyclerItemClickListener;
import org.zzy.driver.mvp.model.bean.response.ResponseOrder;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.presenter.FindCargoPresenter;
import org.zzy.driver.mvp.ui.activity.VehicleManagerActivity;
import org.zzy.driver.mvp.ui.adapter.FindCargoAdapter;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.utils.VehicleInfoUtils;

import java.util.List;

import butterknife.BindView;


/**
 * @function 找货界面，只显示后台推送的订单（这些订单是用户点击取消，或者返回按钮的订单），保存在客户端本地数据，
 * 不一定能够抢单成功，因为有可能已经被其他用户所抢。
 * Created by zhou on 2018/5/10.
 */

public class FindCargoFragment extends BaseFragment<FindCargoPresenter> implements FindCargoAdapter.OnBtnClickListener, OnRecyclerItemClickListener {

    @BindView(R.id.rv_cargo)
    RecyclerView rvCargo;
    @BindView(R.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R.id.rl_no_order)
    RelativeLayout rlNoOrder;

    private FindCargoAdapter mFindCargoAdapter;
    private List<ResponseOrder> mOrders;

    @Override
    public FindCargoPresenter newPresenter() {
        return new FindCargoPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_cargo;
    }

    @Override
    public void initData() {
        mOrders = getPresenter().loadOrderData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCargo.setLayoutManager(linearLayoutManager);
        if (mOrders != null && mOrders.size()!=0) {
            rlNoOrder.setVisibility(View.GONE);
            rvCargo.setVisibility(View.VISIBLE);
            mFindCargoAdapter = new FindCargoAdapter(mOrders, getActivity());
            mFindCargoAdapter.setmOnItemClickListener(this);
            mFindCargoAdapter.setmStatusBtnClickListener(this);
            rvCargo.setAdapter(mFindCargoAdapter);
        } else {
            rlNoOrder.setVisibility(View.VISIBLE);
            rvCargo.setVisibility(View.GONE);
            ToastUtils.showShort("暂无可接订单！");
        }

    }

    @Override
    public void showError(String msg) {

    }

    public void showSuccess(String msg){
        ToastUtils.showShort(msg);
    }

    /**
     * 抢单成功后更新数据
     * */
    public void updateDate(List<ResponseOrder> orders){
        mFindCargoAdapter.setmOrderList(orders);
        mFindCargoAdapter.notifyDataSetChanged();
    }

    /**
     * 跳转到车辆管理界面
     * */
    public void goVehicleManager(){
        Router.newIntent(getActivity())
                .to(VehicleManagerActivity.class)
                .launch();
    }



    @Override
    public void onBtnClick(ResponseOrder order) {
        //点击抢单
       getPresenter().acceptOrder(order);
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object data) {

    }
}
