package com.chuongvd.support.adapterbinding;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.chuongvd.support.adapterbinding.databinding.ItemNoDataBinding;
import java.util.ArrayList;
import java.util.List;

public abstract class AdapterBinding<VIEWHOLDER extends ViewHolderBinding, MODEL>
        extends RecyclerView.Adapter<ViewHolderBinding> {

    public static final int TYPE_NO_DATA = 0;
    public static final int TYPE_NORMAL = 1;
    public String EMPTY_STRING = "";
    private Context mContext;
    public List<MODEL> mList;
    private LayoutInflater mLayoutInflater;
    protected OnRecyclerItemListener<MODEL> mItemListener;
    private boolean isFirst = true;
    private boolean enableShowNoData = false;

    protected abstract VIEWHOLDER getViewHolder(ViewGroup parent, LayoutInflater inflater);

    public AdapterBinding(Context context, List<MODEL> list) {
        mContext = context;
        mList = list;
    }

    public AdapterBinding(List<MODEL> list) {
        mList = list;
    }

    public AdapterBinding(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public AdapterBinding(Context context,
            OnRecyclerItemListener<MODEL> itemListener) {
        mContext = context;
        mList = new ArrayList<>();
        this.mItemListener = itemListener;
    }

    public void setEnableShowNoData(boolean enableShowNoData) {
        this.enableShowNoData = enableShowNoData;
    }

    public void add(MODEL MODEL) {
        getData().add(MODEL);
        notifyItemChanged(getData().size() - 1);
        isFirst = false;
    }

    public void add(List<MODEL> data) {
        int size = getData().size();
        getData().addAll(data);
        if (size == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemInserted(size);
        }
        isFirst = false;
    }

    public void addPreviousEnd(List<MODEL> data) {
        if (data.isEmpty()) return;
        if (getData().isEmpty()) {
            getData().addAll(data);
            notifyDataSetChanged();
        } else {
            int size = getData().size();
            getData().addAll(data);
            notifyItemRangeInserted(size - 1, data.size());
        }
        isFirst = false;
    }

    public void setData(List<MODEL> data) {
        if (data == null) return;
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
        isFirst = false;
    }

    public synchronized void remove(MODEL MODEL) {
        int position = -1;
        for (int i = 0; i < getData().size(); i++) {
            if (!MODEL.equals(getData().get(i))) {
                continue;
            }
            position = i;
            break;
        }

        if (position == -1) return;
        removePosition(position);
        isFirst = false;
    }

    public synchronized void removePosition(int position) {
        if (getData().size() < position) return;
        getData().remove(position);
        notifyItemChanged(position);
        isFirst = false;
    }

    public boolean isEmpty() {
        return mList.size() == 0;
    }

    public void setItemListener(OnRecyclerItemListener<MODEL> listener) {
        mItemListener = listener;
        notifyDataSetChanged();
    }

    public synchronized void remove(List<MODEL> data) {
        getData().removeAll(data);
        notifyDataSetChanged();
        isFirst = false;
    }

    public synchronized void removeAll() {
        getData().clear();
        notifyDataSetChanged();
        isFirst = false;
    }

    public synchronized void refreshData(List<MODEL> data) {
        getData().clear();
        getData().addAll(data);
        notifyDataSetChanged();
        isFirst = false;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public List<MODEL> getData() {
        return mList == null ? mList = new ArrayList<>() : mList;
    }

    protected LayoutInflater getLayoutInflater(Context context) {
        mContext = context;
        return mLayoutInflater == null ? mLayoutInflater = LayoutInflater.from(context)
                : mLayoutInflater;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolderBinding onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderBinding viewHolderBinding;
        if (viewType == TYPE_NO_DATA) {
            viewHolderBinding =
                    getNoDataViewHolder(parent, getLayoutInflater(parent.getContext()));
        } else {
            viewHolderBinding = getViewHolder(parent, getLayoutInflater(parent.getContext()));
        }
        return viewHolderBinding;
    }

    public ViewHolderBinding getNoDataViewHolder(ViewGroup parent, LayoutInflater inflater) {
        return new NoDataViewHolder(ItemNoDataBinding.inflate(inflater, parent, false));
    }

    public void setEmptyString(String emptyString) {
        EMPTY_STRING = emptyString;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolderBinding holder, int position) {
        if (getData().size() > 0) {
            holder.bindData(mList.get(position));
        } else {
            if (!isFirst && enableShowNoData) {
                holder.bindData(EMPTY_STRING);
            }
            if (isFirst) {
                isFirst = false;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getData().size() == 0) {
            return TYPE_NO_DATA;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (getData().size() == 0) {
            return 1;
        }
        return getData().size();
    }

    public void softUpdateListData(List<MODEL> newData) {
        if (newData == null || newData.isEmpty()) {
            mList.clear();
            return;
        }
        int newSize = newData.size();
        int oldSize = mList.size();
        int minSize = Math.min(oldSize, newSize);
        int maxSize = Math.max(oldSize, newSize);
        for (int i = 0; i < minSize; i++) {
            MODEL data = newData.get(i);
            if (!data.equals(mList.get(i))) {
                mList.remove(i);
                mList.add(i, data);
                notifyItemChanged(i);
            }
        }
        for (int i = minSize; i < maxSize; i++) {
            if (newSize > oldSize) {
                mList.add(newData.get(i));
                notifyItemRemoved(i);
            } else {
                mList.remove(newSize);
                notifyItemRemoved(newSize);
            }
        }
    }

    class NoDataViewHolder extends ViewHolderBinding<ItemNoDataBinding, String> {

        NoDataViewHolder(ItemNoDataBinding binding) {
            super(binding);
        }

        @Override
        public void bindData(String model) {
            if (TextUtils.isEmpty(model)) {
                model = getContext().getString(R.string.no_data);
            }
            mBinding.setText(model);
        }
    }

    public interface OnRecyclerItemListener<MODEL> {
        void onItemClick(int position, MODEL data);
    }
}
