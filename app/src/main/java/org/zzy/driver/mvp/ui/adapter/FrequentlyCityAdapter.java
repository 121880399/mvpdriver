package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.custom.CityUseHistory;

import java.util.List;

/**
 * @fuction 常用城市Adapter
 * Created by zhou on 2018/6/13.
 */

public class FrequentlyCityAdapter extends BaseAdapter {
    private List<CityUseHistory> cityUseHistoryList;
    private Context mContext;

    public FrequentlyCityAdapter(List<CityUseHistory> cityUseHistoryList, Context mContext) {
        this.cityUseHistoryList = cityUseHistoryList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return cityUseHistoryList==null ? 0 : cityUseHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityUseHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_frequently_city,null,false);
            viewHolder.tvCityName=(TextView) convertView.findViewById(R.id.tv_cityname);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        CityUseHistory cityUseHistory = cityUseHistoryList.get(position);
        viewHolder.tvCityName.setText(cityUseHistory.getName());
        return convertView;
    }

    static class ViewHolder{
        TextView tvCityName;
    }
}
