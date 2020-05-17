package com.myapp.laporanadmin.ui.rekapan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.R;
import com.myapp.databinding.HalamanPilihRekapanFragmentBinding;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.HalamanRekapanCallback;
import com.myapp.laporanadmin.callback.RekapanListener;
import com.myapp.laporanadmin.ui.bottomsheet.SheetKaryawan;
import com.myapp.laporanadmin.ui.datepicker.DatePickerMonthAndYear;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.List;

public class HalamanPilihRekapan extends BaseFragment {

    private HalamanPilihRekapanViewModel mViewModel;
    private HalamanPilihRekapanFragmentBinding binding;
    private SheetKaryawan sheetKaryawan;
    private DatePickerMonthAndYear datePickerMonthAndYear;
    private int bulan = 0;
    private int tahun = 0;
    private String id_user = "";
    private AdapterLaporanHarianRekapan adapterLaporanHarianRekapan;
    private boolean AdaData = false;

    public static HalamanPilihRekapan newInstance() {
        return new HalamanPilihRekapan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.halaman_pilih_rekapan_fragment, container, false);
        setActionBar(binding.toolbar, "Data Rekapan", "");
        adapterLaporanHarianRekapan = new AdapterLaporanHarianRekapan(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanHarianRekapan);
        setHasOptionsMenu(true);
        setDefault();
        mViewModel = new ViewModelProvider(requireActivity(), new HalamanPilihRekapanFactory(getContext())).get(HalamanPilihRekapanViewModel.class);
        mViewModel.setRekapanListener(prosesrekap);
        sheetKaryawan = new SheetKaryawan();
        datePickerMonthAndYear = new DatePickerMonthAndYear();

        datePickerMonthAndYear.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                bulan = month;
                String monthName = DateTime.now().withMonthOfYear(month).toString("MMM");
                binding.tvTanggal.setText(monthName + " " + year);
            }
        });
        sheetKaryawan.setOnSheetListener(listener);

        binding.setEvent(halamanRekapanCallback);
        binding.setIsLoading(false);

        return binding.getRoot();
    }

    private void setDefault() {
        Calendar calendar = Calendar.getInstance();
        bulan = calendar.get(Calendar.MONTH) + 1;
        tahun = calendar.get(Calendar.YEAR);


    }


    private HalamanRekapanCallback halamanRekapanCallback = new HalamanRekapanCallback() {
        @Override
        public void onSelectKaryawan(View v) {
            sheetKaryawan.show(getActivity().getSupportFragmentManager(), "Pilih Karyawan");


        }

        @Override
        public void onSelectBulan(View v) {
            datePickerMonthAndYear.show(getActivity().getSupportFragmentManager(), "ambil tanggal");
        }

        @Override
        public void onSync(View v) {
            binding.setIsLoading(true);
            LaporanHarianRekapanRequestData l = new LaporanHarianRekapanRequestData();
            l.setIdUser(id_user);
            l.setBulanLaporanharian(bulan);
            l.setTahunLaporanharian(tahun);
            mViewModel.setharianrekap(l);
        }


    };


    private SheetKaryawan.BottomSheetListener listener = new SheetKaryawan.BottomSheetListener() {
        @Override
        public void onOptionClick(UserModel kotaModel) {
            sheetKaryawan.dismiss();
            binding.setKaryawan(kotaModel);

        }
    };

    private AdapterItemClicked adapterItemClicked = new AdapterItemClicked() {
        @Override
        public void onClick(int pos) {

        }

        @Override
        public void onEdit(int pos) {

        }

        @Override
        public void onDelete(int pos) {

        }

        @Override
        public void onDetail(int pos) {

        }
    };
    private RekapanListener prosesrekap = new RekapanListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(List<LaporanHarianModel> laporanHarianModels) {
            binding.setIsLoading(false);
            adapterLaporanHarianRekapan.setData(laporanHarianModels);
            adapterLaporanHarianRekapan.notifyDataSetChanged();
            HalamanPilihRekapan.this.AdaData = true;
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menurekap, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_export:
                if(AdaData){

                }else {
                    Toast.makeText(getContext(),"Setidaknya Pilih Karyawan",Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
