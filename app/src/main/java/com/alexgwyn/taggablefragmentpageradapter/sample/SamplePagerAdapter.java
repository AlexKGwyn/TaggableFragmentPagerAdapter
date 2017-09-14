package com.alexgwyn.taggablefragmentpageradapter.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alexgwyn.taggablefragmentpageradapter.TaggableFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SamplePagerAdapter extends TaggableFragmentPagerAdapter {

    private List<SampleFragment> fragments = new ArrayList<>();

    public SamplePagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public String getTag(int position) {
        return String.valueOf(position);
    }

    public void add() {
        SampleFragment fragment = SampleFragment.newInstance("https://unsplash.it/200/300");
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    public void remove() {
        if (!fragments.isEmpty()) {
            SampleFragment lastFragment = fragments.get(fragments.size() - 1);
            fragments.remove(lastFragment);
            notifyDataSetChanged();
        }
    }
}
