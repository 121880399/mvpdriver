package org.zzy.driver.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseFragment;
import com.zzy.quick.router.Router;
import com.zzy.quick.utils.TimeUtils;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.custom.CityUseHistory;
import org.zzy.driver.mvp.model.bean.request.RequestSellCapacity;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.presenter.SellCapacityPresenter;
import org.zzy.driver.mvp.ui.activity.ChooseCityActivity;
import org.zzy.driver.mvp.ui.activity.SellCapacityRecordActivity;
import org.zzy.driver.mvp.ui.activity.VehicleManagerActivity;
import org.zzy.driver.mvp.ui.adapter.VehicleListAdapter;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.utils.VehicleInfoUtils;
import org.zzy.driver.view.CommonCalendarView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by zhou on 2018/5/10.
 */

public class SellCapacityFragment extends BaseFragment<SellCapacityPresenter> {

    @BindView(R.id.tv_startCity)
    TextView tvStartCity;
    @BindView(R.id.iv_change)
    ImageView ivChange;
    @BindView(R.id.tv_endCity)
    TextView tvEndCity;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_vehicleCode)
    TextView tvVehicleCode;
    @BindView(R.id.tv_vehicleChange)
    TextView tvVehicleChange;
    @BindView(R.id.tv_vehicleType)
    TextView tvVehicleType;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    private RequestSellCapacity mSellCapacity;
    private String defaultVehicleCode;


    @Override
    public SellCapacityPresenter newPresenter() {
        return new SellCapacityPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sell_capacity;
    }

    @Override
    public void initData() {
        mSellCapacity = new RequestSellCapacity();
        ResponseVehicle vehicle = VehicleInfoUtils.getVehicleInfo();
        if (vehicle == null) {
            ToastUtils.showShort("您还没有添加车辆，请先添加车辆。");
            Router.newIntent(getActivity())
                    .to(VehicleManagerActivity.class)
                    .launch();
        } else {
            defaultVehicleCode=vehicle.getCode();
            tvVehicleCode.setText(defaultVehicleCode);
            tvVehicleType.setText(vehicle.getVehicle_type());
        }
    }

    /**
     * 选择起始城市
     */
    @OnClick(R.id.tv_startCity)
    public void choiceStartCity() {
        Router.newIntent(this)
                .to(ChooseCityActivity.class)
                .putString("title", "选择起始城市")
                .requestCode(CommonValue.CHOOSE_START_CITY)
                .fragmentLaunch();
    }

    /**
     * 选择终点城市
     */
    @OnClick(R.id.tv_endCity)
    public void choiceEndCity() {
        Router.newIntent(this)
                .to(ChooseCityActivity.class)
                .putString("title","选择终点城市")
                .requestCode(CommonValue.CHOOSE_END_CITY)
                .fragmentLaunch();
    }

    /**
     * 交换起始和终点城市
     */
    @OnClick(R.id.iv_change)
    public void changeCity() {
        //交换数据
        String tempName = mSellCapacity.getEndName();
        String temCode = mSellCapacity.getEndCode();
        mSellCapacity.setEndName(mSellCapacity.getStartName());
        mSellCapacity.setEndCode(mSellCapacity.getStartCode());
        mSellCapacity.setStartName(tempName);
        mSellCapacity.setStartCode(temCode);
        //交换数据后重新显示起始城市和终止城市
        tvStartCity.setText(mSellCapacity.getStartName());
        tvEndCity.setText(mSellCapacity.getEndName());
    }

    /**
     * 选择出发时间
     */
    @OnClick(R.id.tv_date)
    public void choiceStartDate() {
        final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_calendar, null, true);
        CommonCalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        final AlertDialog dialog = builder.show();
        //去掉Dialog两边的白边
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        calendarView.init(new CommonCalendarView.DatePickerController() {
            @Override
            public int getMaxYear() {
                return TimeUtils.getCurrentYear() + 1;
            }

            @Override
            public void onDayOfMonthSelected(int year, int month, int day) {
                if (TimeUtils.isPreDate(year, month, day)) {
                    ToastUtils.showShort("早于当天的日期不可选择！");
                } else {
                    mSellCapacity.setStartTime(year + "-" + month + "-" + day);
                }
            }

            @Override
            public void onDayOfMonthAndDataSelected(int year, int month, int day, List obj) {
                //没有数据源不用写
            }

            @Override
            public void showOtherFields(Object obj, View view, int gridItemYear, int gridItemMonth, int gridItemDay) {
                //没有其他字段，这里不用写
            }

            @Override
            public Map<String, List> getDataSource() {
                //数据源，没有不用写
                return null;
            }
        });
        ImageView closeIv = dialogView.findViewById(R.id.iv_close);
        TextView confirmBtn = (TextView) dialogView.findViewById(R.id.tv_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mSellCapacity.getStartTime())) {
                    ToastUtils.showShort("请选择日期");
                } else {
                    tvStartTime.setText(mSellCapacity.getStartTime());
                    dialog.dismiss();
                }
            }
        });
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /**
     * 选择车牌号
     */
    @OnClick(R.id.tv_vehicleChange)
    public void choiceVehicleNum() {
        getPresenter().getVehicleList();
    }

    /**
     * 点击提交按钮
     **/
    @OnClick(R.id.btn_commit)
    public void clickSubmit() {
        if(verifyDate()){
            getPresenter().sellCapacity(mSellCapacity);
        }
    }

    @Override
    public void showError(String msg) {

    }

    /**
     * 出售运力成功
     * */
    public void sellSuccess(){
        ToastUtils.showShort("运力出售成功！");
        Router.newIntent(getActivity()).to(SellCapacityRecordActivity.class).launch();
    }

    /**
     * 提交前验证数据
     * */
    private boolean verifyDate(){
        if(TextUtils.isEmpty(tvStartCity.getText().toString())){
            ToastUtils.showShort("请选择起始城市!");
            return false;
        }else if(TextUtils.isEmpty(tvEndCity.getText().toString())){
            ToastUtils.showShort("请选择终点城市!");
            return false;
        }else if(TextUtils.isEmpty(tvStartTime.getText().toString())){
            ToastUtils.showShort("请选择出发日期");
            return false;
        }else if(TextUtils.isEmpty(tvVehicleCode.getText().toString())){
            ToastUtils.showShort("请选择车辆！");
            return false;
        }else if(mSellCapacity.getVehicleAuthStatus()!=CommonValue.AUTHENTICATION_VEHICLE){
            ToastUtils.showShort("该车辆未通过认证请选择其他车辆");
            return false;
        }
        return true;
    }


    /**
     * 显示车牌号列表
     */
    public void showVehicleList(final List<ResponseVehicle> vehicleDatas) {
        View vehicleListView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_vehicle, null, true);
        AlertDialog.Builder vehicleListBuilder=new AlertDialog.Builder(getActivity());
        vehicleListBuilder.setView(vehicleListView);
        RecyclerView vehicleListRv = (RecyclerView) vehicleListView.findViewById(R.id.rv_vehicleList);
        vehicleListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        VehicleListAdapter vehicleListAdapter = new VehicleListAdapter(getContext(), vehicleDatas, vehicleListRv,defaultVehicleCode);
        vehicleListRv.setAdapter(vehicleListAdapter);

        final AlertDialog vehicleListDialog = vehicleListBuilder.show();
        //去掉Dialog两边的白边
        vehicleListDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        vehicleListDialog.getWindow().setGravity(Gravity.CENTER);

        TextView comfirmTv = (TextView) vehicleListView.findViewById(R.id.tv_confirm);
        ImageView closeIv = vehicleListView.findViewById(R.id.iv_close);
        comfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected=getPresenter().isSelected(vehicleDatas);//是否有选中
                ResponseVehicle selectedVehicle=getPresenter().getSelectedVehicle(vehicleDatas);
                if(!isSelected){
                    ToastUtils.showShort("请选取车辆！");
                }else{
                    mSellCapacity.setVehiclecode(selectedVehicle.getCode());
                    mSellCapacity.setVehicleType(selectedVehicle.getVehicle_type());
                    mSellCapacity.setVehicleTypeCode(selectedVehicle.getVehicle_type_code());
                    mSellCapacity.setVehicleId(selectedVehicle.getVehicle_id());
                    mSellCapacity.setSuport40(selectedVehicle.getSupport_40gp());
                    mSellCapacity.setVehicleAuthStatus(selectedVehicle.getAuth_status());
                    tvVehicleCode.setText(selectedVehicle.getCode());
                    tvVehicleType.setText(selectedVehicle.getVehicle_type());
                }
                vehicleListDialog.dismiss();
            }
        });
        closeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleListDialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            switch (requestCode){
                case CommonValue.CHOOSE_START_CITY:
                    CityUseHistory startCity= (CityUseHistory) data.getSerializableExtra("city");
                    mSellCapacity.setStartName(startCity.getName());
                    mSellCapacity.setStartCode(startCity.getCode());
                    tvStartCity.setText(startCity.getName());
                    break;
                case CommonValue.CHOOSE_END_CITY:
                    CityUseHistory endCity= (CityUseHistory) data.getSerializableExtra("city");
                    mSellCapacity.setEndName(endCity.getName());
                    mSellCapacity.setEndCode(endCity.getCode());
                    tvEndCity.setText(endCity.getName());
                    break;
            }
        }

    }
}
