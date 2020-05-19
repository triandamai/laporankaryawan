package com.myapp.laporanadmin.ui.rekapan;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.myapp.R;
import com.myapp.databinding.HalamanPilihRekapanBulananFragmentBinding;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.BaseFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.ExportListener;
import com.myapp.laporanadmin.callback.HalamanRekapanCallback;
import com.myapp.laporanadmin.callback.RekapanListener;
import com.myapp.laporanadmin.ui.bottomsheet.SheetKaryawan;
import com.myapp.laporanadmin.ui.datepicker.DatePickerMonthAndYear;
import com.myapp.laporanadmin.ui.detaillaporanbulanan.DetailBulanan;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HalamanPilihRekapanBulanan extends BaseFragment {

    private HalamanPilihRekapanBulananViewModel mViewModel;
    private HalamanPilihRekapanBulananFragmentBinding binding;
    private SheetKaryawan sheetKaryawan;
    private DatePickerMonthAndYear datePickerMonthAndYear;
    private int bulan = 0;
    private int tahun = 0;
    private UserModel userModel;
    private AdapterLaporanBulananRekapan adapterLaporanBulananRekapan;
    private boolean AdaData = false;
    private List<LaporanBulananModel> laporanHarianModels = new ArrayList<>();

    public static HalamanPilihRekapanBulanan newInstance() {
        return new HalamanPilihRekapanBulanan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.halaman_pilih_rekapan_bulanan_fragment, container, false);
        setActionBar(binding.toolbar, "Rekap Data Bulanan", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new HalamanPilihRekapanFactory(getContext())).get(HalamanPilihRekapanBulananViewModel.class);
        mViewModel.setRekapanListener(prosesrekap);
        mViewModel.setExportListener(exportListener);
        binding.setIsLoading(false);
        binding.setEvent(halamanRekapanCallback);
        adapterLaporanBulananRekapan = new AdapterLaporanBulananRekapan(adapterItemClicked);
        binding.rv.setAdapter(adapterLaporanBulananRekapan);
        setDefault();

        sheetKaryawan = new SheetKaryawan();
        datePickerMonthAndYear = new DatePickerMonthAndYear();

        datePickerMonthAndYear.setListener(dateSetListener);
        sheetKaryawan.setOnSheetListener(listener);

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

            LaporanBulananRequestData l = new LaporanBulananRequestData();
            try {
                l.setIdUser(userModel.getIdUser());
                l.setBulanLaporanbulanan(bulan);
                l.setTahunLaporanbulanan(tahun);
                Log.e("sync",userModel.toString());
                binding.setIsLoading(true);
                mViewModel.setharianrekap(l);

            }catch (NullPointerException e){
                Snackbar.make(binding.rv,"Tentukan Data Rekapan !", BaseTransientBottomBar.LENGTH_LONG).show();
            }

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
            LaporanBulananModel obj = adapterLaporanBulananRekapan.getFromPosition(pos);

            Bundle bundle = new Bundle();
            bundle.putString("idlaporanbulanan",obj.getIdLaporanbulanan());
            bundle.putString("statuslaporanbulanan",obj.getStatusLaporanbulanan());
            DetailBulanan bulanan = new DetailBulanan();
            bulanan.setArguments(bundle);
            replaceFragment(bulanan, null);
        }
    };
    private SheetKaryawan.BottomSheetListener listener = new SheetKaryawan.BottomSheetListener() {
        @Override
        public void onOptionClick(UserModel kotaModel) {
            sheetKaryawan.dismiss();
            binding.setKaryawan(kotaModel);
            userModel = kotaModel;
            Log.e("",userModel.toString());
        }
    };

    private ExportListener exportListener = new ExportListener() {
        @Override
        public void onStart() {
            showProgress("Meng-Export Rekapan " + userModel.getNamaUser());
        }

        @Override
        public void onSucces(String nama_file) {
            dismissProgress();
            dialogBerhasil(nama_file);
        }

        @Override
        public void onError(String message) {
            dismissProgress();
            dialogGagal(message);
        }
    };
    private RekapanListener prosesrekap = new RekapanListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccessHarian(List<LaporanHarianModel> laporanHarianModels) {

        }

        @Override
        public void onSuccessBulanan(List<LaporanBulananModel> laporanHarianModels) {
            binding.setIsLoading(false);
            adapterLaporanBulananRekapan.setData(laporanHarianModels);
            HalamanPilihRekapanBulanan.this.laporanHarianModels = laporanHarianModels;
            HalamanPilihRekapanBulanan.this.AdaData = true;
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            HalamanPilihRekapanBulanan.this.AdaData = false;
            adapterLaporanBulananRekapan.clearData();
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            HalamanPilihRekapanBulanan.this.AdaData = false;
            adapterLaporanBulananRekapan.clearData();
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menurekap, menu);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            bulan = month;
            String monthName = DateTime.now().withMonthOfYear(month).toString("MMM");
            binding.tvTanggal.setText(monthName + " " + year);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_export:
                if (AdaData) {
                    showProgress("Memproses..");
                    cekPermission();
                } else {
                    Snackbar.make(binding.rv,"Tidak Ada Data!",Snackbar.LENGTH_LONG).show();
                }
                return true;
            case R.id.menu_close:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cekPermission() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        dismissProgress();
                        if (report.areAllPermissionsGranted()) {
                            mViewModel.ExportBulanan(laporanHarianModels, userModel.getNamaUser());
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        dismissProgress();
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}
