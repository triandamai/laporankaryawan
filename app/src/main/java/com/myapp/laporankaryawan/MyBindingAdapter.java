package com.myapp.laporankaryawan;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.todkars.shimmer.ShimmerRecyclerView;

public class MyBindingAdapter {
    @BindingAdapter("showShimmer")
    public static void shimmer(ShimmerRecyclerView v,boolean isShow){
        if(isShow){
            v.showShimmer();
        }else {
            v.hideShimmer();
        }
    }
    @BindingAdapter("viewGone")
    public static void viewgone(View v, boolean isShow){
        v.setVisibility(isShow ? View.VISIBLE:View.GONE);
    }
}
