package org.zzy.driver.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.ToastUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseContainerType;
import org.zzy.driver.mvp.ui.adapter.ContainerTypeAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车辆管理-箱型箱类
 * Created by zhou on 2018/6/25.
 */

public class ContainerTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.lv_containerType)
    ListView lvContainerType;

    private ContainerTypeAdapter mContainerTypeAdapter;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_container;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("箱型箱类");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getTopbar().setRightText("确定");
        getTopbar().setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContainerTypeAdapter!=null) {
                    if (mContainerTypeAdapter.getContainerTypeMap().size() == 0) {
                        ToastUtils.showShort("请选择箱型箱类");
                        return;
                    }
                    Intent intent = getIntent();
                    intent.putExtra("containerTypeList", (Serializable) mContainerTypeAdapter.getContainerTypeMap());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        List<ResponseContainerType> containerTypeDatas= (List<ResponseContainerType>) getIntent().getSerializableExtra("containerType");
        mContainerTypeAdapter=new ContainerTypeAdapter(this,containerTypeDatas);
        lvContainerType.setAdapter(mContainerTypeAdapter);
        lvContainerType.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv_containerType) {
            ContainerTypeAdapter.ViewHolder viewHolder = (ContainerTypeAdapter.ViewHolder) view.getTag();
            viewHolder.choose.toggle();
        }
    }
}
