package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicleType;

import java.util.List;

/**
 * Created by 周正一 on 2016/6/1.
 *车辆类型
 */
public class VehicleTypeAdapter extends BaseAdapter {
    private List<ResponseVehicleType> mList;
    private Context mContext;

    /**
     * 构造函数
     */
    public VehicleTypeAdapter(Context context, List<ResponseVehicleType> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList==null ? 0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_vehicletype, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.type= (TextView) convertView.findViewById(R.id.tv_type);
        ResponseVehicleType carType=mList.get(position);
        holder.type.setText(carType.getName());
        return convertView;
    }

    public class ViewHolder {
        public TextView type;
    }

}
