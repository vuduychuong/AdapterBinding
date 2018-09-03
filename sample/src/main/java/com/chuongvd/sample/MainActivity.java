package com.chuongvd.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chuongvd.support.adapterbinding.AdapterBinding;

public class MainActivity extends AppCompatActivity
        implements AdapterBinding.OnRecyclerItemListener<SampleItem> {

    private SampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        mAdapter = new SampleAdapter(this, this, true);
        recyclerView.setAdapter(mAdapter);
        dumpData();
    }

    private void dumpData() {
        mAdapter.add(new SampleItem("Item 1", "des1"));
        mAdapter.add(new SampleItem("Item 2", "des2"));
        mAdapter.add(new SampleItem("Item 3", "des3"));
        mAdapter.add(new SampleItem("Item 4", "des4"));
    }

    @Override
    public void onItemClick(int position, SampleItem data) {
        // TODO: 9/3/18
    }
}
