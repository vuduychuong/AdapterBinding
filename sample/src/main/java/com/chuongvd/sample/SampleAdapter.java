package com.chuongvd.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chuongvd.sample.databinding.ItemSampleBinding;
import com.chuongvd.support.adapterbinding.SelectableAdapter;
import com.chuongvd.support.adapterbinding.SelectableViewHolder;

/**
 * Created on 9/3/18
 *
 * @author Chuongvd
 */
public class SampleAdapter extends SelectableAdapter<SampleAdapter.ViewHolder, SampleItem> {

    public SampleAdapter(Context context, OnRecyclerItemListener<SampleItem> itemListener,
            boolean isSelectedMode) {
        super(context, itemListener, isSelectedMode);
    }

    @Override
    protected SampleAdapter.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(ItemSampleBinding.inflate(inflater, parent, false), mItemListener,
                isSelectedMode());
    }

    public class ViewHolder extends SelectableViewHolder<ItemSampleBinding, SampleItem> {

        public ViewHolder(ItemSampleBinding binding, OnRecyclerItemListener<SampleItem> listener,
                boolean isSelectedMode) {
            super(binding, listener, isSelectedMode);
        }

        @Override
        public void bindData(SampleItem sampleItem) {
            super.bindData(sampleItem);
            mBinding.setItem(sampleItem);
            mBinding.checkbox.setVisibility(isSelectedMode() ? View.VISIBLE : View.GONE);
            //            mBinding.checkbox.setChecked(sampleItem.isSelected());
        }
    }
}
