package com.udemy.tabby.tabby;

import android.support.v4.app.*;
import android.support.v4.app.ListFragment;

/**
 * Created by INSPIRON on 25/4/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FormFragment();
            case 1:
                return new ListsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numberOfTabs;
    }
}
