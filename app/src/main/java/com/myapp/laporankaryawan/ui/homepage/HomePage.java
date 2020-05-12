package com.myapp.laporankaryawan.ui.homepage;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.databinding.HomePageFragmentBinding;

public class HomePage extends Fragment {
    public static String TAG = "Home Page Fragment";

    private HomePageViewModel mViewModel;
    private HomePageFragmentBinding binding;

    public static HomePage newInstance() {
        return new HomePage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater,R.layout.home_page_fragment, container, false);

      return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        // TODO: Use the ViewModel
    }

}
