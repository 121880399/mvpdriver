package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
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
    private ArrayMap<String,Integer> alphbetIndex;

    public CityListAdapter(List<CitySortData> citySortDatas, Context mContext) {
        this.citySortDatas = citySortDatas;
        this.mContext = mContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alphbetIndex=new ArrayMap<>();
        }
    }

    public void setCitySortDatas(List<CitySortData> citySortDatas) {
        this.citySortDatas = citySortDatas;
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

        //如果位置是0，那么首先显示A
        if(position==0){
            viewHolder.tvCatalog.setVisibility(View.VISIBLE);
            viewHolder.tvCatalog.setText(citySortData.getSortLetters());
        }else{
            //如果前面城市的Ascii码跟当前城市的Ascii码一致，说明在同一个字母目录下
            //就不再显示字母目录，否则显示
            if(compare(getAscii(position-1),getAscii(position))){
                viewHolder.tvCatalog.setVisibility(View.GONE);
            }else{
                alphbetIndex.put(citySortData.getSortLetters(),position);
                viewHolder.tvCatalog.setVisibility(View.VISIBLE);
                viewHolder.tvCatalog.setText(citySortData.getSortLetters());
            }
        }

        viewHolder.tvCityName.setText(citySortData.getName());
        return convertView;
    }

    /**
     * 得到字母第一次出现的位置
     * */
    public int getInitialIndex(String letter){
        if(alphbetIndex.containsKey(letter)) {
            return alphbetIndex.get(letter);
        }else{
            return 0;
        }
    }

    /**
     * 比较前面一个城市的字母Ascii码跟当前城市的字母的Ascii码是否相等
     * */
    private boolean compare(int preAscii,int currentAscii){
        if(preAscii==currentAscii){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 得到字母的Ascii码
     * */
    private int getAscii(int position){
       return  citySortDatas.get(position).getSortLetters().charAt(0);
    }

    static class ViewHolder{
        TextView tvCatalog;
        TextView tvCityName;
    }
}
