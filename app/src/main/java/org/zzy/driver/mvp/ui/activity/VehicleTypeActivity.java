package org.zzy.driver.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicleType;
import org.zzy.driver.mvp.ui.adapter.VehicleTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择车辆类型界面
 * Created by zhou on 2018/6/25.
 */

public class VehicleTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.lv_vehicleType)
    ListView lvVehicleType;

    private VehicleTypeAdapter mVehicleTypeAdapter;
    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicletype;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("车辆类型");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<ResponseVehicleType> vehicleTypeData = (List<ResponseVehicleType>) getIntent().getSerializableExtra("vehicleType");
        mVehicleTypeAdapter = new VehicleTypeAdapter(this, vehicleTypeData);
        lvVehicleType.setAdapter(mVehicleTypeAdapter);
        lvVehicleType.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResponseVehicleType vehicleType= (ResponseVehicleType) mVehicleTypeAdapter.getItem(position);
        Intent intent=getIntent();
        intent.putExtra("name",vehicleType.getName());
        intent.putExtra("code", vehicleType.getCode());
        setResult(RESULT_OK,intent);
        finish();
    }
}
