package com.chuongvd.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chuongvd.support.adapterbinding.OnRecyclerItemListener;
import com.chuongvd.support.adapterbinding.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements OnRecyclerItemListener<SampleItem> {

    //    private SampleAdapter mAdapter;

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        //        mAdapter = new SampleAdapter(this, this, true);

        sectionAdapter = new SectionedRecyclerViewAdapter(this);

        recyclerView.setAdapter(sectionAdapter);
        dumpData();
    }

    private void dumpData() {
        List<SampleItem> itemList = new ArrayList<>();
        itemList.add(new SampleItem("Item 1", "des1"));
        itemList.add(new SampleItem("Item 2", "des2"));
        itemList.add(new SampleItem("Item 3", "des3"));
        itemList.add(new SampleItem("Item 4", "des4"));
        HomeSection section1 = new HomeSection(this);
        HomeSection section2 = new HomeSection(this);
        sectionAdapter.addSection(section1);
        sectionAdapter.addSection(section2);
        section1.updateBindableData(itemList);
    }

    @Override
    public void onItemClick(int position, SampleItem data) {
        // TODO: 9/3/18
    }
}
