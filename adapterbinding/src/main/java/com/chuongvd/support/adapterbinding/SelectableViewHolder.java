package com.chuongvd.support.adapterbinding;

import android.databinding.ViewDataBinding;
import com.chuongvd.support.adapterbinding.AdapterBinding.OnRecyclerItemListener;

/**
 * Created on 9/3/18
 *
 * @author Chuongvd
 */
public class SelectableViewHolder<BINDINGVIEW extends ViewDataBinding, MODEL extends ItemSelectable>
        extends ViewHolderBinding<BINDINGVIEW, MODEL> {

    protected boolean mIsSelectedMode;
    private OnItemChangeStateListener mOnItemChangeStateListener;

    public SelectableViewHolder(BINDINGVIEW binding, OnRecyclerItemListener<MODEL> listener,
            boolean isSelectedMode) {
        super(binding, listener);
        mIsSelectedMode = isSelectedMode;
    }

    @Override
    public void bindData(MODEL model) {
        data = model;
        mBinding.getRoot().setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(getAdapterPosition(), model);
            }
            if (isSelectedMode()) {
                model.setSelected(!model.isSelected());
                if (mOnItemChangeStateListener == null) return;
                mOnItemChangeStateListener.onItemChangeState(getAdapterPosition());
            }
        });
    }

    public boolean isSelectedMode() {
        return mIsSelectedMode;
    }

    public void setSelectedMode(boolean selectedMode) {
        mIsSelectedMode = selectedMode;
    }

    public void setOnItemChangeStateListener(OnItemChangeStateListener listener) {
        mOnItemChangeStateListener = listener;
    }

    public interface OnItemChangeStateListener {
        void onItemChangeState(int position);
    }
}
