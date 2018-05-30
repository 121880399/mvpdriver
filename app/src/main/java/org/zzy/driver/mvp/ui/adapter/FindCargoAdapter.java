package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zzy.quick.utils.ButterKnifeUtil;
import com.zzy.quick.utils.StringUtils;
import com.zzy.quick.utils.TimeUtils;

import org.zzy.driver.R;
import org.zzy.driver.interf.OnRecyclerItemClickListener;
import org.zzy.driver.mvp.model.bean.response.ResponseOrder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhou on 2018/5/29.
 */

public class FindCargoAdapter extends RecyclerView.Adapter<FindCargoAdapter.FindCargoHolder> implements View.OnClickListener {


    private List<ResponseOrder> mOrderList;
    private Context mContext;

    private OnBtnClickListener mStatusBtnClickListener;
    private OnRecyclerItemClickListener mOnItemClickListener;

    private ViewGroup mParent;

    public FindCargoAdapter(List<ResponseOrder> mOrderList, Context mContext) {
        this.mOrderList = mOrderList;
        this.mContext = mContext;
    }

    public void setmStatusBtnClickListener(OnBtnClickListener mStatusBtnClickListener) {
        this.mStatusBtnClickListener = mStatusBtnClickListener;
    }

    public void setmOnItemClickListener(OnRecyclerItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOrderList(List<ResponseOrder> mOrderList) {
        this.mOrderList = mOrderList;
    }

    @Override
    public FindCargoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_find_cargo, parent, false);
        FindCargoHolder holder = new FindCargoHolder(view);
        mParent=parent;
        return holder;
    }

    @Override
    public void onBindViewHolder(FindCargoHolder holder, int position) {
        ResponseOrder responseOrder = mOrderList.get(position);
        holder.tvStartTime.setText(StringUtils.strCompound(TimeUtils.getCurrentFormateTime2OfDate(responseOrder.getStartTime()), "出发"));
        holder.tvStartCity.setText(responseOrder.getStart_region());
        holder.tvEndCity.setText(responseOrder.getEnd_region());
        holder.tvGoodsName.setText(responseOrder.getGoods_name());
        holder.tvContainerType.setText(responseOrder.getContainerType());
        holder.tvPrice.setText(StringUtils.formatMoney(responseOrder.getPrice()));
        holder.tvEstimateTime.setText(TimeUtils.getArriveDays(responseOrder.getStartTime(),responseOrder.getEndTime()));
        holder.tvStatusbtn.setOnClickListener(this);
        holder.tvStatusbtn.setTag(responseOrder);
        holder.itemView.setTag(responseOrder);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mOrderList==null?0:mOrderList.size();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_statusbtn){
            if(mStatusBtnClickListener!=null){
                mStatusBtnClickListener.onBtnClick((ResponseOrder)v.getTag());
            }
        }else{
            if(mOnItemClickListener!=null){
                mOnItemClickListener.onItemClick(mParent,v,v.getTag());
            }
        }

    }


    static class FindCargoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_startTime)
        TextView tvStartTime;
        @BindView(R.id.tv_estimateTime)
        TextView tvEstimateTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_statusbtn)
        Button tvStatusbtn;
        @BindView(R.id.tv_startCity)
        TextView tvStartCity;
        @BindView(R.id.tv_endCity)
        TextView tvEndCity;
        @BindView(R.id.tv_goodsName)
        TextView tvGoodsName;
        @BindView(R.id.tv_containerType)
        TextView tvContainerType;

        public FindCargoHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }

    public interface OnBtnClickListener{
        void onBtnClick(ResponseOrder order);
    }
}
