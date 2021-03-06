package com.moringaschool.myrestaurants.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRestaurantsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mRestaurants;
    private String[] mCusisines;

    public MyRestaurantsArrayAdapter(Context mContext, int resource, String[] mRestaurants, String[] mCusisines){
        super(mContext, resource);
        this.mContext = mContext;
        this.mCusisines = mCusisines;
        this.mRestaurants = mRestaurants;
    }
    @Override
    public  Object getItem(int position){
        String restaurant = mRestaurants[position];
        String cuisine =mCusisines[position];
        return String.format("%s \nServes great:%s", restaurant, cuisine);
    }
    @Override
    public int getCount(){
        return mRestaurants.length;
    }

}
