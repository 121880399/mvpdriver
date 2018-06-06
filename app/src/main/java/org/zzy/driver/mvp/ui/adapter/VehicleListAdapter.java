package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzy.quick.utils.ButterKnifeUtil;

import org.zzy.driver.R;
import org.zzy.driver.interf.OnRecyclerViewItemClickListener;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.ui.fragment.SellCapacityFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhou on 2018/4/24.
 */

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.VehicleListHolder>{


    private Context mContext;
    private List<ResponseVehicle> vehicleList;
    private RecyclerView mRecyclerView;
    private int mSelectedPos;//上次选中的位置

    public VehicleListAdapter(Context mContext, List<ResponseVehicle> vehicleList,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.vehicleList = vehicleList;
        mRecyclerView=recyclerView;
    }

    @Override
    public VehicleListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_list, null, true);
        VehicleListHolder holder = new VehicleListHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(VehicleListHolder holder, final int position) {
        final ResponseVehicle vehicle = vehicleList.get(position);
        holder.tvVehicleCode.setText(vehicle.getCode());
        holder.tvVehicleType.setText(vehicle.getVehicle_type());
        holder.ivSelect.setSelected(vehicle.isSelected());
        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleListHolder vehicleListHolder = (VehicleListHolder) mRecyclerView.findViewHolderForLayoutPosition(mSelectedPos);
                if(vehicleListHolder!=null){//还在屏幕里
                    vehicleListHolder.ivSelect.setSelected(false);
                }else{
                    //一些极端情况，holder被缓存在Recycler的cacheView里，
                    //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
                    notifyItemChanged(mSelectedPos);
                }
                //上一个item的勾选状态恢复
                vehicleList.get(mSelectedPos).setSelected(false);
                //设置新item的勾选状态
                vehicleList.get(position).setSelected(true);
                vehicleListHolder.ivSelect.setSelected(true);
                mSelectedPos=position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return  null==vehicleList ? 0 : vehicleList.size();
    }



    static class VehicleListHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.tv_vehicleCode)
        TextView tvVehicleCode;
        @BindView(R.id.tv_vehicleType)
        TextView tvVehicleType;

        public VehicleListHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }


    }
}
