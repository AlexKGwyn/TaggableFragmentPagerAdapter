/*
 * Modified from FragmentPagerAdapter
 *
 *
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alexgwyn.taggablefragmentpageradapter;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public abstract class TaggableFragmentPagerAdapter extends PagerAdapter {
    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = false;

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    private HashMap<String, Integer> mTagArray = new HashMap<>();

    public TaggableFragmentPagerAdapter(FragmentManager fm) {
        mFragmentManager = fm;
    }

    /**
     * Create a fragment for the specified position
     *
     * @param position The position of the fragment to create
     * @return A created fragment
     */
    public abstract Fragment getItem(int position);

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @SuppressLint("CommitTransaction")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        // Do we already have this fragment?
        String name = getTag(position);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            if (DEBUG) Log.v(TAG, "Attaching item #" + getTag(position) + ": f=" + fragment);
            mCurTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            String tag = getTag(position);
            if (DEBUG) Log.v(TAG, "Adding item #" + tag + ": f=" + fragment);
            mTagArray.put(tag, position);
            mCurTransaction.add(container.getId(), fragment, tag);
        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Detaching item #" + getTag(position) + ": f=" + object
                + " v=" + ((Fragment) object).getView());
        if (position == POSITION_NONE) {
            mCurTransaction.remove((Fragment) object);
        } else {
            mCurTransaction.detach((Fragment) object);
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        String tag = ((Fragment) object).getTag();
        int currentPosition = findMatchingTag(tag);
        Integer oldPosition = mTagArray.get(tag);
        if (currentPosition == POSITION_NONE) {
            mTagArray.remove(tag);
            return POSITION_NONE;
        }
        if (oldPosition == null) {
            return currentPosition;
        }
        if (currentPosition == oldPosition) {
            return POSITION_UNCHANGED;
        }
        return currentPosition;
    }



    private int findMatchingTag(String tag) {
        if (tag != null) {
            for (int i = 0; i < getCount(); i++) {
                String positionTag = getTag(i);
                if (tag.equals(positionTag)) {
                    return i;
                }
            }
        }
        return POSITION_NONE;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    /**
     * Return a unique tag for the fragment at position.
     *
     * @param position The position of the fragment
     * @return The tag of the fragment. Every fragment should have a unique tag.
     */
    public abstract String getTag(int position);


    /**
     * Get's the fragment at the position.
     *
     * @param position The position of the fragment to get
     * @return The fragment or null if the fragment hasn't been created yet
     */
    public Fragment getFragmentAtPosition(int position) {
        return mFragmentManager.findFragmentByTag(getTag(position));
    }
}