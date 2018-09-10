package com.chuongvd.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chuongvd.support.adapterbinding.OnRecyclerItemListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRecyclerItemListener<SampleItem> {

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
        List<SampleItem> list = new ArrayList<>();
        list.add(new SampleItem("Item 1", "des1"));
        list.add(new SampleItem("Item 2", "des2"));
        list.add(new SampleItem("Item 3", "des3"));
        list.add(new SampleItem("Item 4", "des4"));
        mAdapter.submitList(list);
    }

    @Override
    public void onItemClick(int position, SampleItem data) {
        dumpData2();
    }

    private void dumpData2() {
        List<SampleItem> list = new ArrayList<>();
        list.add(new SampleItem("Item 3", "des3"));
        list.add(new SampleItem("Item 4", "des4"));
        list.add(new SampleItem("Item 5", "des5"));
        list.add(new SampleItem("Item 6", "des6"));
        list.add(new SampleItem("Item 7", "des7"));
        mAdapter.submitList(list);
    }
}
