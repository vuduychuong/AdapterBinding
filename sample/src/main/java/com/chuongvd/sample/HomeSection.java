package com.chuongvd.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.chuongvd.sample.databinding.ItemHeaderBinding;
import com.chuongvd.sample.databinding.ItemSampleBinding;
import com.chuongvd.support.adapterbinding.ViewHolderBinding;
import com.chuongvd.support.adapterbinding.sectionedrecyclerviewadapter.Section;
import com.chuongvd.support.adapterbinding.sectionedrecyclerviewadapter.SectionParameters;

/**
 * Created by chuongvd on 11/26/18.
 */
public class HomeSection extends Section<SampleItem> {

    public HomeSection(Context context) {
        super(context, SectionParameters.builder()
                .headerViewWillBeProvided()
                .itemViewWillBeProvided()
                .build());
    }

    @Override
    public int getContentItemsTotal() {
        return mList.size();
    }

    @Override
    public ViewHolderBinding getItemViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new HomeViewHolder(ItemSampleBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindItemViewHolder(ViewHolderBinding holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public ViewHolderBinding getHeaderViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new HeaderViewHolder(ItemHeaderBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolderBinding holder) {
        holder.bindData("Title sample");
    }

    public class HomeViewHolder extends ViewHolderBinding<ItemSampleBinding, SampleItem> {

        public HomeViewHolder(ItemSampleBinding binding) {
            super(binding);
        }

        @Override
        public void bindData(SampleItem sampleItem) {
            super.bindData(sampleItem);
            mBinding.setItem(sampleItem);
        }
    }

    private class HeaderViewHolder extends ViewHolderBinding<ItemHeaderBinding, String> {
        public HeaderViewHolder(ItemHeaderBinding binding) {
            super(binding);
        }

        @Override
        public void bindData(String s) {
            super.bindData(s);
            mBinding.setTitle(s);
        }
    }
}
