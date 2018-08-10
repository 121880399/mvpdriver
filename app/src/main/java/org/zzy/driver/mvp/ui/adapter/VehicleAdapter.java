package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.zzy.driver.R;
import org.zzy.driver.common.VehicleAuthStatusEnunm;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhou on 2018/6/21.
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {
    private static final int CURRENT_VEHICLE=1;//当前车辆
    private Context mContext;
    private List<ResponseVehicle> mVehicleDatas;
    private RecyclerView mRecyclerView;
    private int mSelectedPos;//上次选中的位置
    private boolean isClickManager;
    private int currentVehicleId;//当前选择车辆的id

    public VehicleAdapter(Context mContext, List<ResponseVehicle> mVehicleDatas,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mVehicleDatas = mVehicleDatas;
        mRecyclerView=recyclerView;
        isClickManager=false;
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View  convertView = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle, null, false);
        VehicleViewHolder viewHolder = new VehicleViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, final int position) {
        ResponseVehicle vehicle = mVehicleDatas.get(position);
        holder.tvVehicleCode.setText(vehicle.getCode());
        //当前车辆要显示标签，并且单选勾选住
        if(vehicle.getIsActivite()==CURRENT_VEHICLE) {
            mSelectedPos=position;
            holder.ivSelect.setSelected(true);
            holder.tvCurrentVehicle.setVisibility(View.VISIBLE);
        }else{
            holder.ivSelect.setSelected(false);
            holder.tvCurrentVehicle.setVisibility(View.GONE);
        }

        //点击管理，显示单选框，否则不显示
        if(isClickManager){
            holder.ivSelect.setVisibility(View.VISIBLE);
        }else{
            holder.ivSelect.setVisibility(View.GONE);
        }

        holder.tvVehicleStatus.setText(VehicleAuthStatusEnunm.getName(vehicle.getAuth_status()));
        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VehicleViewHolder viewHolder=(VehicleViewHolder)mRecyclerView.findViewHolderForLayoutPosition(mSelectedPos);
                if(viewHolder!=null){
                    //上一个Item的勾选恢复正常
                    viewHolder.ivSelect.setSelected(false);
                }else{
                    //一些极端情况，holder被缓存在Recycler的cacheView里，
                    //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
                    notifyItemChanged(mSelectedPos);
                }
                //上一个item的数据恢复
                mVehicleDatas.get(mSelectedPos).setSelected(false);
                //设置新item的勾选状态
                mVehicleDatas.get(position).setSelected(true);
                v.setSelected(true);
                mSelectedPos=position;
                //选择车辆时，把当前车辆的id给记录下来
                currentVehicleId=mVehicleDatas.get(position).getVehicle_id();
            }
        });
    }

    /**
     * 得到选中车辆id
     * */
    public int getCurrentVehicleId() {
        return currentVehicleId;
    }

    public void setVehicleDatas(List<ResponseVehicle> mVehicleDatas) {
        this.mVehicleDatas = mVehicleDatas;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return null == mVehicleDatas ? 0 : mVehicleDatas.size();
    }


    public void setClickManager(boolean clickManager) {
        isClickManager = clickManager;
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_Select)
        ImageView ivSelect;
        @BindView(R.id.tv_vehicleCode)
        TextView tvVehicleCode;
        @BindView(R.id.tv_currentVehicle)
        TextView tvCurrentVehicle;
        @BindView(R.id.tv_vehicleStatus)
        TextView tvVehicleStatus;


        public VehicleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
