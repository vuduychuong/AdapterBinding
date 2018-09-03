package com.chuongvd.support.adapterbinding;

import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/3/18
 *
 * @author Chuongvd
 */
public abstract class SelectableAdapter<VIEWHOLDER extends SelectableViewHolder, MODEL extends ItemSelectable>
        extends AdapterBinding<VIEWHOLDER, MODEL>
        implements SelectableViewHolder.OnItemChangeStateListener {

    protected boolean mIsSelectedMode;

    public SelectableAdapter(Context context, OnRecyclerItemListener<MODEL> itemListener,
            boolean isSelectedMode) {
        super(context, itemListener);
        mIsSelectedMode = isSelectedMode;
    }

    @Override
    public ViewHolderBinding onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderBinding viewHolderBinding;
        if (viewType == TYPE_NO_DATA) {
            viewHolderBinding = getNoDataViewHolder(parent, getLayoutInflater(parent.getContext()));
        } else {
            viewHolderBinding = getViewHolder(parent, getLayoutInflater(parent.getContext()));
            if (viewHolderBinding instanceof SelectableViewHolder) {
                ((SelectableViewHolder) viewHolderBinding).setOnItemChangeStateListener(this);
            }
        }
        return viewHolderBinding;
    }

    public boolean isSelectedMode() {
        return mIsSelectedMode;
    }

    public void setSelectedMode(boolean selectedMode) {
        mIsSelectedMode = selectedMode;
        removeSelectedState();
        notifyDataSetChanged();
    }

    public void removeSelectedState() {
        int size = mList.size();
        for (int i = 0; i < size; i++) {
            mList.get(i).setSelected(false);
            notifyItemChanged(i);
        }
    }

    public void removeItem(int position) {
        if (position < 0 || position >= mList.size()) return;
        mList.remove(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        int result = 0;
        for (MODEL model : mList) {
            if (model.isSelected()) result++;
        }
        return result;
    }

    public List<MODEL> getSelectedItems() {
        List<MODEL> list = new ArrayList<>();
        for (MODEL model : mList) {
            if (model.isSelected()) list.add(model);
        }
        return list;
    }

    @Override
    public void onItemChangeState(int position) {
        notifyItemChanged(position);
    }
}
