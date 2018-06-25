package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseContainerType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou on 2016/6/13.
 */
public class ContainerTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResponseContainerType> mList;

    public  Map<String,ResponseContainerType> mContainerTypeMap;

    public ContainerTypeAdapter(Context context, List<ResponseContainerType> list) {
        mContext = context;
        mList = list;
        mContainerTypeMap = new HashMap<>();
    }

    public Map<String, ResponseContainerType> getContainerTypeMap() {
        return mContainerTypeMap;
    }

    public void setContainerTypeMap(Map<String, ResponseContainerType> mContainerTypeMap) {
        this.mContainerTypeMap = mContainerTypeMap;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        final ResponseContainerType containerType = mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_containertype, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.type= (TextView) convertView.findViewById(R.id.tv_type);
        holder.choose= (CheckBox) convertView.findViewById(R.id.cb_choose);
        holder.type.setText(containerType.getName());
        final String routeId= String.valueOf(containerType.getId());
        holder.choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                //如果选中添加到map否则移除
                if(checked){
                    mContainerTypeMap.put(routeId,containerType);
                }else{
                    if(mContainerTypeMap.containsKey(routeId)){
                        mContainerTypeMap.remove(routeId);
                    }
                }
            }
        });
        if( mContainerTypeMap.containsKey(routeId)){
            holder.choose.setChecked(true);
        }else{
            holder.choose.setChecked(false);
        }

        return convertView;
    }

    public class ViewHolder {
        public TextView type;
        public CheckBox choose; //选中状态

    }
}
