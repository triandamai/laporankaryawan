package com.myapp.laporanadmin.ui.dataoutlet;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.myapp.R;
import com.myapp.databinding.DataOutletFragmentBinding;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.ui.datakota.DataKotaFactory;
import com.myapp.laporanadmin.ui.tambahkota.TambahKota;
import com.myapp.laporanadmin.ui.tambahoutlet.TambahOutlet;

import java.util.List;

public class DataOutlet extends BaseFragment {
    public static String TAG = "Data Outlet";
    private DataOutletViewModel mViewModel;
    private DataOutletFragmentBinding binding;
    private AdapterDataOutlet adapterDataOutlet;

    public static DataOutlet newInstance() {
        return new DataOutlet();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_outlet_fragment, container, false);
        binding.setListener(refreshListener);
        setActionBar(binding.toolbar,"Data Outlet","");
        setHasOptionsMenu(true);
        binding.setIsLoading(true);
        adapterDataOutlet = new AdapterDataOutlet(adapterItemClicked);
        binding.rv.setAdapter(adapterDataOutlet);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(),new DataOutletFactory(getContext())).get(DataOutletViewModel.class);
        mViewModel.init();
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        observe(mViewModel);
    }

    private void observe(DataOutletViewModel mViewModel) {
        mViewModel.getOutletData().observe(getViewLifecycleOwner(), outletObjects -> {
            binding.setIsLoading(false);
            if(outletObjects != null){

                adapterDataOutlet.setData(outletObjects);
                adapterDataOutlet.notifyDataSetChanged();
            }
        });
    }
    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {
            OutletObject outletObject = adapterDataOutlet.getFromPosition(pos);
            OutletModel outletModel = new OutletModel();
            outletModel.setIdKota(outletObject.getIdKota());
            String aksi = getContext().getString(R.string.AKSI_UBAH);
            replaceFragment(TambahOutlet.newInstance(aksi,outletModel),null);
        }

        @Override
        public void onDetail(int pos) {

        }
    };
    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mViewModel.fetchFromApi();
        }
    };
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.toolbardata,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_tambah:
                replaceFragment(TambahOutlet.newInstance(),null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
