package com.chuongvd.sample;

import com.chuongvd.support.adapterbinding.ItemSelectable;

/**
 * Created on 9/3/18
 *
 * @author Chuongvd
 */
public class SampleItem extends ItemSelectable {
    private String mName;
    private String mDescription;

    public SampleItem(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
