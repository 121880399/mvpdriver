package org.zzy.driver.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zzy.quick.mvp.ui.BaseActivity;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseLinkType;
import org.zzy.driver.mvp.ui.adapter.LinkTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车辆管理-拖挂形式界面
 * Created by zhou on 2018/6/25.
 */

public class LinkTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.lv_linkType)
    ListView lvLinkType;
    private LinkTypeAdapter linkTypeAdapter;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_linktype;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("拖挂形式");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<ResponseLinkType> linkTypeDatas = (List<ResponseLinkType>) getIntent().getSerializableExtra("linkType");
        linkTypeAdapter = new LinkTypeAdapter(this, linkTypeDatas);
        lvLinkType.setAdapter(linkTypeAdapter);
        lvLinkType.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ResponseLinkType trailerType= (ResponseLinkType) linkTypeAdapter.getItem(position);
        Intent intent=getIntent();
        intent.putExtra("name", trailerType.getName());
        intent.putExtra("code", trailerType.getCode());
        setResult(RESULT_OK,intent);
        finish();
    }
}
