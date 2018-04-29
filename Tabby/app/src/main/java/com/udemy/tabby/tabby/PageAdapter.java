package com.udemy.tabby.tabby;

import android.support.v4.app.*;
import android.support.v4.app.ListFragment;

import static com.udemy.tabby.tabby.MainActivity.PERSON_FORM_FRAGMENT;
import static com.udemy.tabby.tabby.MainActivity.PERSON_LIST_FRAGMENT;

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
            case PERSON_FORM_FRAGMENT:
                return new FormFragment();
            case PERSON_LIST_FRAGMENT:
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
