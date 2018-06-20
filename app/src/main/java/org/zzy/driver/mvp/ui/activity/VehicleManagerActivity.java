package org.zzy.driver.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.presenter.VehicleManagerPresenter;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/5/11.
 */

public class VehicleManagerActivity extends BaseActivity<VehicleManagerPresenter> {

    @BindView(R.id.btn_bindVehicle)
    Button btnBindVehicle;
    @BindView(R.id.btn_deleteVehicle)
    Button btnDeleteVehicle;
    @BindView(R.id.ll_setvehicle)
    LinearLayout llSetvehicle;
    @BindView(R.id.sv_VehileList)
    SwipeRefreshLayout svVehileList;
    @BindView(R.id.btn_addVehicle)
    Button btnAddVehicle;
    private boolean isClickManager=false;//是否点击了管理按钮,默认位否

    @Override
    public VehicleManagerPresenter newPresenter() {
        return new VehicleManagerPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehiclemanager;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("车辆管理");
        getTopbar().setLeftImage(R.drawable.ic_newback);
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getTopbar().setRightText("管理");
        getTopbar().setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里的逻辑是点击管理以后，管理会变成完成，点击完成变成管理，两者切换
                isClickManager=!isClickManager;
                switchStatus();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getVehicleList();
    }

    /**
     * 切换管理和完成
     * 点击管理以后，每个item前面会显示单选按钮，并且显示设为当前车辆的按钮
     * 点击完成以后item前面的单线按钮消失，显示添加车辆按钮
     * */
    private  void switchStatus(){
        if(isClickManager){
            //点击管理，变成了完成
            getTopbar().setRightText("完成");
            llSetvehicle.setVisibility(View.VISIBLE);
            //如果是承运商则不能删除车辆，所以不显示删除按钮
            if(getPresenter().isCarrier(UserInfoUtils.getUserInfo().getUserType())){
                btnDeleteVehicle.setVisibility(View.GONE);
            }
            btnAddVehicle.setVisibility(View.GONE);
        }else{
            //点击完成，变管理
            getTopbar().setRightText("管理");
            btnAddVehicle.setVisibility(View.VISIBLE);
            llSetvehicle.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String msg) {

    }


    /**
     * 得到车辆数据以后，显示车辆数据
     * */
    public void showVehicleList(List<ResponseVehicle> vehicleList) {


    }
}
