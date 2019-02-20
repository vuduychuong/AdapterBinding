# TODO
```StatefulAdapterBinding```:
  - Add header & footer item
  - Add swipe behavior

# Adapter Binding
Library to support build Adapter very fast and easy.

# Features
* Integrate Adapter Selectable
* Integrate Adapter Binding view.

# How to use?

## Gradle
In build.gradle project level, add following this code:
```
repositories {
    maven {
        url  "https://dl.bintray.com/chuongvd/maven" 
    }
}
```
In build.grade app level, add codes below:
```
dependencies {
  compile 'com.chuongvd.support:adapterbinding:0.1.6'
}
```

## Integrate Adapter Binding 

```
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

```
# Contributions
Feel free to create issues / pull requests.

# License
```
AdapterBinding library for Android
Copyright (c) 2018 chuongvd (http://github.com/vuduychuong).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
