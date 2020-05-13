package com.myapp.laporankaryawan.ui.tambahuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.databinding.TambahUserFragmentBinding;

public class TambahUser extends BaseFragment {
    public static String TAG = "Tambah User Fragment";
    private TambahUserViewModel mViewModel;
    private TambahUserFragmentBinding binding;

    public static TambahUser newInstance() {
        return new TambahUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater,R.layout.tambah_user_fragment, container, false);
        setHasOptionsMenu(true);
        setActionBar(binding.toolbar,"Tambah Kota","");
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new TambahUserFactory(getContext()))
                .get(TambahUserViewModel.class);
        // TODO: Use the ViewModel
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.toolbarformnav,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
