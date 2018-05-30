package org.zzy.driver.interf;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 周正一 on 2016/9/3.
 */
public interface OnRecyclerItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T data);
}
