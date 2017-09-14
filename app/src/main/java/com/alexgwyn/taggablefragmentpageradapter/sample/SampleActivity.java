package com.alexgwyn.taggablefragmentpageradapter.sample;

import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

public class SampleActivity extends AppCompatActivity {

    ViewGroup root;
    SamplePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        root = findViewById(R.id.root);
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
                        showCount();
                        return true;
                    case R.id.remove:
                        adapter.remove();
                        showCount();
                        return true;
                }
                return false;
            }
        });

        adapter = new SamplePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void showCount() {
        Snackbar.make(root, adapter.getCount() + " items in view pager", Snackbar.LENGTH_SHORT)
                .show();
    }
}
