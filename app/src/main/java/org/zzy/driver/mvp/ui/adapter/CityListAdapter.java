package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.custom.CitySortData;
import org.zzy.driver.mvp.model.bean.response.ResponseCityList;

import java.util.List;

/**
 * Created by zhou on 2018/6/8.
 */

public class CityListAdapter extends BaseAdapter {

    private List<CitySortData> citySortDatas;
    private Context mContext;

    public CityListAdapter(List<CitySortData> citySortDatas, Context mContext) {
        this.citySortDatas = citySortDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return citySortDatas==null? 0:citySortDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return citySortDatas.get(position);
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_sortcity,null,false);
            viewHolder.tvCatalog=convertView.findViewById(R.id.tv_catalog);
            viewHolder.tvCityName=convertView.findViewById(R.id.tv_cityName);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        CitySortData citySortData = citySortDatas.get(position);

        return null;
    }

    static class ViewHolder{
        TextView tvCatalog;
        TextView tvCityName;
    }
}
