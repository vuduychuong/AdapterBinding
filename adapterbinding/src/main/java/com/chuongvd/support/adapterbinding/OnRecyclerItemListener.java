package com.chuongvd.support.adapterbinding;

/**
 * Created by chuongvd on 2/20/19.
 */
public interface OnRecyclerItemListener<MODEL> {
    void onItemClick(int position, MODEL data);
}
