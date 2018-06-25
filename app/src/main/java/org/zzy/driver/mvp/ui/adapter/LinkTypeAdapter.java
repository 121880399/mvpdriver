package org.zzy.driver.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.zzy.driver.R;
import org.zzy.driver.mvp.model.bean.response.ResponseLinkType;

import java.util.List;

/**
 * Created by zhou on 2016/6/1.
 *拖车形式
 */
public class LinkTypeAdapter extends BaseAdapter {
    private List<ResponseLinkType> mList;
    private Context mContext;

    /**
     * 构造函数
     */
    public LinkTypeAdapter(Context context, List<ResponseLinkType> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_linktype, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.type= (TextView) convertView.findViewById(R.id.tv_type);
        ResponseLinkType trailerType=mList.get(position);
        holder.type.setText(trailerType.getName());
        return convertView;
    }

    public class ViewHolder {
        public TextView type;

    }

}
