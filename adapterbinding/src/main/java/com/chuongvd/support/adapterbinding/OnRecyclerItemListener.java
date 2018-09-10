package com.chuongvd.support.adapterbinding;
//
// Created by chuongvd on 9/10/18.
//
//

public interface OnRecyclerItemListener<MODEL> {
    void onItemClick(int position, MODEL data);
}
