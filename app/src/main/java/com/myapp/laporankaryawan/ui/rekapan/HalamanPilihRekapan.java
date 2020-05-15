package com.myapp.laporankaryawan.ui.rekapan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.KaryawanModel;
import com.myapp.laporankaryawan.BaseFragment;
import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.callback.HalamanRekapanCallback;
import com.myapp.laporankaryawan.databinding.HalamanPilihRekapanFragmentBinding;
import com.myapp.laporankaryawan.ui.bottomsheet.SheetKaryawan;
import com.myapp.laporankaryawan.ui.datepicker.DatePickerMax;

import org.joda.time.DateTime;

public class HalamanPilihRekapan extends BaseFragment {

    private HalamanPilihRekapanViewModel mViewModel;
    private HalamanPilihRekapanFragmentBinding binding;
    private SheetKaryawan sheetKaryawan;
    private DatePickerMax datePickerMax;

    public static HalamanPilihRekapan newInstance() {
        return new HalamanPilihRekapan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.halaman_pilih_rekapan_fragment, container, false);
        setActionBar(binding.toolbar,"Data Rekapan","");
        setHasOptionsMenu(true);
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

    }

    private SheetKaryawan.BottomSheetListener listener = new SheetKaryawan.BottomSheetListener() {
        @Override
        public void onOptionClick(KaryawanModel kotaModel) {
            sheetKaryawan.dismiss();
            binding.setKaryawan(kotaModel);
        }
    };
    private DatePickerMax.DateListener dateListener = new DatePickerMax.DateListener() {
        @Override
        public void onDatePandaSet(int tahun, int bulan, int hari) {
            datePickerMax.dismiss();
            String monthName = DateTime.now().withMonthOfYear(bulan).toString("MMM");
            binding.setTanggal(monthName + " " + tahun);
        }
    };

}
