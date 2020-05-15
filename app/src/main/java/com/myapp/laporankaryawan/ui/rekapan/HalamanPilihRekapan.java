package com.myapp.laporankaryawan.ui.rekapan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.KaryawanModel;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.AdapterItemClicked;
import com.myapp.laporankaryawan.callback.HalamanRekapanCallback;
import com.myapp.laporankaryawan.databinding.HalamanPilihRekapanFragmentBinding;
import com.myapp.laporankaryawan.ui.bottomsheet.SheetKaryawan;
import com.myapp.laporankaryawan.ui.datepicker.DatePickerMax;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;

public class HalamanPilihRekapan extends BaseFragment {

    private HalamanPilihRekapanViewModel mViewModel;
    private HalamanPilihRekapanFragmentBinding binding;
    private SheetKaryawan sheetKaryawan;
    private DatePickerMax datePickerMax;
    private int bulan = 0;
    private int tahun = 0;
    private String id_user = "";
    private AdapterLaporanHarianRekapan adapterLaporanHarianRekapan;

    public static HalamanPilihRekapan newInstance() {
        return new HalamanPilihRekapan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.halaman_pilih_rekapan_fragment, container, false);
        setActionBar(binding.toolbar,"Data Rekapan","");
        adapterLaporanHarianRekapan = new AdapterLaporanHarianRekapan(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanHarianRekapan);
        setHasOptionsMenu(true);
        setDefault();
        mViewModel = new ViewModelProvider(requireActivity(), new HalamanPilihRekapanFactory(getContext())).get(HalamanPilihRekapanViewModel.class);
        sheetKaryawan = new SheetKaryawan();
        datePickerMax = new DatePickerMax();
        datePickerMax.setDateListener(dateListener);
        sheetKaryawan.setOnSheetListener(listener);
        observe(mViewModel);
        binding.setEvent(halamanRekapanCallback);
        binding.setIsLoading(false);

        return binding.getRoot();
    }

    private void setDefault() {
        Calendar calendar = Calendar.getInstance();
        bulan = calendar.get(Calendar.MONTH)+1;
        tahun = calendar.get(Calendar.YEAR);


    }


    private HalamanRekapanCallback halamanRekapanCallback = new HalamanRekapanCallback() {
        @Override
        public void onSelectKaryawan(View v) {
            sheetKaryawan.show(getActivity().getSupportFragmentManager(), "Pilih Karyawan");
            binding.setIsLoading(true);

        }

        @Override
        public void onSelectBulan(View v) {
            datePickerMax.show(getActivity().getSupportFragmentManager(), "ambil tanggal");
        }

        @Override
        public void onSelectTahun(View v) {

        }
    };

    private void observe(HalamanPilihRekapanViewModel mViewModel) {
        mViewModel.getHarianObject().observe(getViewLifecycleOwner(), new Observer<List<LaporanHarianModel>>() {
            @Override
            public void onChanged(List<LaporanHarianModel> laporanHarianModels) {
                Log.e("hehe",laporanHarianModels.toString());
                if(laporanHarianModels != null){
                    binding.setIsLoading(false);
                    adapterLaporanHarianRekapan.setData(laporanHarianModels);
                    adapterLaporanHarianRekapan.notifyDataSetChanged();
                }else {
                    binding.setIsLoading(false);
                }
            }
        });
    }

    private SheetKaryawan.BottomSheetListener listener = new SheetKaryawan.BottomSheetListener() {
        @Override
        public void onOptionClick(KaryawanModel kotaModel) {
            sheetKaryawan.dismiss();
            binding.setKaryawan(kotaModel);
            LaporanHarianRekapanRequestData l = new LaporanHarianRekapanRequestData();
            l.setIdUser(kotaModel.getIdUser());
            l.setBulanLaporanharian(bulan);
            l.setTahunLaporanharian(tahun);
            mViewModel.setharianrekap(l);
        }
    };
    private DatePickerMax.DateListener dateListener = new DatePickerMax.DateListener() {
        @Override
        public void onDatePandaSet(int tahun, int bulan, int hari) {
            datePickerMax.dismiss();
            String monthName = DateTime.now().withMonthOfYear(bulan).toString("MMM");
            binding.setTanggal(monthName + " " + tahun);
            HalamanPilihRekapan.this.bulan = bulan;
            HalamanPilihRekapan.this.tahun = tahun;

            LaporanHarianRekapanRequestData l = new LaporanHarianRekapanRequestData();
            l.setIdUser(id_user);
            l.setBulanLaporanharian(bulan);
            l.setTahunLaporanharian(tahun);
            mViewModel.setharianrekap(l);
        }
    };
    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onDetail(int pos) {

        }
    };

}
