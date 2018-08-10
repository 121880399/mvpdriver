package org.zzy.driver.mvp.ui.activity.wallet;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.router.Router;

import org.zzy.driver.R;
import org.zzy.driver.interf.OnRecyclerViewItemClickListener;
import org.zzy.driver.mvp.model.bean.response.ResponseIncome;
import org.zzy.driver.mvp.presenter.IncomeListPresenter;
import org.zzy.driver.mvp.ui.adapter.IncomeListAdapter;
import org.zzy.driver.view.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhou on 2018/6/15.
 */

public class IncomeListActivity extends BaseActivity<IncomeListPresenter> {

    @BindView(R.id.rv_income)
    RecyclerView rvIncome;

    @Override
    public IncomeListPresenter newPresenter() {
        return new IncomeListPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_incomelist;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("收入明细");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void initAdapter(List<ResponseIncome> mIncomeDatas) {
        IncomeListAdapter adapter = new IncomeListAdapter(this, mIncomeDatas);
        rvIncome.setAdapter(adapter);
        rvIncome.setLayoutManager(new LinearLayoutManager(this));
        rvIncome.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 15,
                ContextCompat.getColor(this, R.color.color_999)));
        adapter.setmOnItemClickListener(new OnRecyclerViewItemClickListener<ResponseIncome>() {

            @Override
            public void onItemClick(View view, ResponseIncome data) {
                Router.newIntent(IncomeListActivity.this)
                        .to(IncomeDetailsActivity.class)
                        .putParcelable("incomeDetails",data)
                        .launch();
            }
        });
    }
}
