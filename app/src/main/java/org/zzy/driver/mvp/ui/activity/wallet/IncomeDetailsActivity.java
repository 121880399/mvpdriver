package org.zzy.driver.mvp.ui.activity.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zzy.quick.mvp.ui.BaseActivity;
import com.zzy.quick.utils.StringUtils;
import com.zzy.quick.utils.TimeUtils;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseIncome;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/6/19.
 */

public class IncomeDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_tradeId)
    TextView tvTradeId;
    @BindView(R.id.tv_balance)
    TextView tvBalance;

    @Override
    public Object newPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_incomedetails;
    }

    @Override
    public void initData() {
        getTopbar().setTitle("收入详情");
        getTopbar().setLeftImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ResponseIncome mIncomeDetails = getIntent().getParcelableExtra("incomeDetails");
        tvBalance.setText(StringUtils.formatMoney(String.valueOf(mIncomeDetails.getAfter_trade_amount())));
        tvDate.setText(TimeUtils.getCurrentFormateTime2OfDate(mIncomeDetails.getTrade_date()));
        tvIncome.setText(StringUtils.formatMoney(String.valueOf(mIncomeDetails.getTrade_amount())));
        tvTradeId.setText(mIncomeDetails.getTrade_order_no());
    }


}
