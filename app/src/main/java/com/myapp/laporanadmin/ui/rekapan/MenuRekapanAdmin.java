package com.myapp.laporanadmin.ui.rekapan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.MenuRekapanAdminFragmentBinding;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.ui.rekapan.bulanan.RekapanBulananAdmin;
import com.myapp.laporanadmin.ui.rekapan.harian.RekapanHarianAdmin;

public class MenuRekapanAdmin extends BaseAdminFragment {

    private MenuRekapanAdminViewModel mViewModel;
    private MenuRekapanAdminFragmentBinding binding;

    public static MenuRekapanAdmin newInstance() {
        return new MenuRekapanAdmin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_rekapan_admin_fragment, container, false);
        setActionBar(binding.toolbar, "Data Rekapan", "");
        setHasOptionsMenu(true);
        binding.setEvent(new com.myapp.laporanadmin.callback.ListRekapan() {
            @Override
            public void onBulanan() {
                replaceFragment(RekapanBulananAdmin.newInstance(), null);
            }

            @Override
            public void onHarian() {
                replaceFragment(RekapanHarianAdmin.newInstance(), null);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MenuRekapanAdminViewModel.class);
        // TODO: Use the ViewModel
    }

}
