package com.chuongvd.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.chuongvd.sample.databinding.ItemSampleBinding;
import com.chuongvd.support.adapterbinding.OnRecyclerItemListener;
import com.chuongvd.support.adapterbinding.StateParameters;
import com.chuongvd.support.adapterbinding.StatefulAdapterBinding;

/**
 * Created on 9/3/18
 *
 * @author Chuongvd
 */
public class SampleAdapter extends StatefulAdapterBinding<SampleAdapter.ViewHolder, SampleItem> {

    public SampleAdapter(Context context, OnRecyclerItemListener<SampleItem> itemListener,
            boolean isSelectedMode) {
        super(context, StateParameters.builder().itemViewWillBeProvided().build(), itemListener,
                isSelectedMode);
    }

    @Override
    protected SampleAdapter.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new ViewHolder(ItemSampleBinding.inflate(inflater, parent, false), mItemListener);
    }

    public class ViewHolder extends
            com.chuongvd.support.adapterbinding.ViewHolderBinding<ItemSampleBinding, SampleItem> {

        public ViewHolder(ItemSampleBinding binding, OnRecyclerItemListener<SampleItem> listener) {
            super(binding, listener);
        }

        @Override
        public void bindData(SampleItem sampleItem) {
            super.bindData(sampleItem);
            mBinding.setItem(sampleItem);
            mBinding.setSelectMode(selectedMode);
        }
    }
}
