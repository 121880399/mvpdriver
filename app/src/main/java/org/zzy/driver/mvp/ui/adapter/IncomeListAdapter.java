package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzy.quick.utils.ButterKnifeUtil;
import com.zzy.quick.utils.StringUtils;
import com.zzy.quick.utils.TimeUtils;

import org.zzy.driver.R;
import org.zzy.driver.interf.OnRecyclerViewItemClickListener;
import org.zzy.driver.mvp.model.bean.response.ResponseIncome;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhou on 2018/6/19.
 */

public class IncomeListAdapter extends RecyclerView.Adapter<IncomeListAdapter.IncomeListHolder> implements View.OnClickListener {

    private Context mContext;

    private List<ResponseIncome> mIncomeDatas;

    private OnRecyclerViewItemClickListener mOnItemClickListener=null;

    public IncomeListAdapter(Context mContext, List<ResponseIncome> mIncomeDatas) {
        this.mContext = mContext;
        this.mIncomeDatas = mIncomeDatas;
    }

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public IncomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_incomelist, null, true);
        itemView.setOnClickListener(this);
        IncomeListHolder holder=new IncomeListHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(IncomeListHolder holder, int position) {
        ResponseIncome responseIncome = mIncomeDatas.get(position);
        holder.tvDate.setText(TimeUtils.getCurrentFormateTime2OfDate(responseIncome.getTrade_date()));
        holder.tvPrice.setText(StringUtils.formatMoney(String.valueOf(responseIncome.getTrade_amount())));
        holder.itemView.setTag(responseIncome);
    }

    @Override
    public int getItemCount() {
        return null== mIncomeDatas ? 0 : mIncomeDatas.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(v,(ResponseIncome)v.getTag());
        }
    }

    static class IncomeListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_price)
        TextView tvPrice;


        public IncomeListHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
