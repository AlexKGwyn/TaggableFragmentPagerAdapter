package com.alexgwyn.taggablefragmentpageradapter.sample;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SampleActivity extends AppCompatActivity {

    SamplePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewPager viewPager = findViewById(R.id.view_pager);

        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.plus_minus);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        adapter.add();
                        return true;
                    case R.id.remove:
                        adapter.remove();
                        return true;
                }
                return false;
            }
        });

        adapter = new SamplePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
