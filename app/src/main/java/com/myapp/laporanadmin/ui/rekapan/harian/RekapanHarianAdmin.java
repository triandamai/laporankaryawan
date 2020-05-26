package com.myapp.laporanadmin.ui.rekapan.harian;

import android.Manifest;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.myapp.R;
import com.myapp.bottomsheet.SheetKaryawan;
import com.myapp.databinding.RekapanHarianAdminFragmentBinding;
import com.myapp.datepicker.DatePickerMonthAndYear;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.BaseAdminFragment;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.ExportListener;
import com.myapp.laporanadmin.callback.HalamanRekapanCallback;
import com.myapp.laporanadmin.callback.RekapanListener;
import com.myapp.laporanadmin.ui.detaillaporanharian.DetailHarian;
import com.myapp.laporanadmin.ui.rekapan.RekapanFactory;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RekapanHarianAdmin extends BaseAdminFragment {

    private RekapanHarianAdminViewModel mViewModel;
    private RekapanHarianAdminFragmentBinding binding;
    private SheetKaryawan sheetKaryawan;
    private DatePickerMonthAndYear datePickerMonthAndYear;
    private int bulan = 0;
    private int tahun = 0;
    private UserModel userModel;
    private AdapterRekapanHarianAdmin adapterRekapanHarianAdmin;
    private boolean AdaData = false;
    private List<LaporanHarianModel> laporanHarianModels = new ArrayList<>();

    public static RekapanHarianAdmin newInstance() {
        return new RekapanHarianAdmin();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.rekapan_harian_admin_fragment, container, false);
        setActionBar(binding.toolbar, "Rekap Harian", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new RekapanFactory(getContext())).get(RekapanHarianAdminViewModel.class);
        mViewModel.setRekapanListener(prosesrekap);
        mViewModel.setExportListener(exportListener);
        binding.setIsLoading(false);
        binding.setEvent(halamanRekapanCallback);
        adapterRekapanHarianAdmin = new AdapterRekapanHarianAdmin(adapterItemClicked);
        binding.rv.setAdapter(adapterRekapanHarianAdmin);

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

    @Override
    public void onResume() {
        super.onResume();
        try {
            binding.tvKaryawan.setText(userModel.getNamaUser());
            binding.tvTanggal.setText(bulan + " " + tahun);
        } catch (NullPointerException e) {

        }
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

            LaporanHarianRekapanRequestData l = new LaporanHarianRekapanRequestData();
            try {
                l.setIdUser(userModel.getIdUser());
                l.setBulanLaporanharian(bulan);
                l.setTahunLaporanharian(tahun);
                Log.e("sync", userModel.toString());
                binding.setIsLoading(true);
                mViewModel.setharianrekap(l);

            } catch (NullPointerException e) {
                Snackbar.make(binding.rv, "Tentukan Data Rekapan !", BaseTransientBottomBar.LENGTH_LONG).show();
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
            LaporanHarianModel obj = adapterRekapanHarianAdmin.getFromPosition(pos);
            Bundle bundle = new Bundle();
            bundle.putString("idlaporanharian", obj.getIdLaporanharian());
            bundle.putString("statuslaporanharian", obj.getStatusLaporanharian());
            DetailHarian detailHarian = new DetailHarian();
            detailHarian.setArguments(bundle);
            replaceFragment(detailHarian, null);
        }
    };
    private SheetKaryawan.BottomSheetListener listener = new SheetKaryawan.BottomSheetListener() {
        @Override
        public void onOptionClick(UserModel kotaModel) {
            sheetKaryawan.dismiss();
            binding.setKaryawan(kotaModel);
            userModel = kotaModel;
            Log.e("", userModel.toString());
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
            binding.setIsLoading(false);
            adapterRekapanHarianAdmin.setData(laporanHarianModels);
            RekapanHarianAdmin.this.laporanHarianModels = laporanHarianModels;
            RekapanHarianAdmin.this.AdaData = true;

        }

        @Override
        public void onSuccessBulanan(List<LaporanBulananModel> laporanHarianModels) {

        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            RekapanHarianAdmin.this.AdaData = false;
            adapterRekapanHarianAdmin.clearData();
            Snackbar.make(binding.rv, "Tidak Ada Data", Snackbar.LENGTH_INDEFINITE).show();
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            RekapanHarianAdmin.this.AdaData = false;
            adapterRekapanHarianAdmin.clearData();
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
                    Snackbar.make(binding.rv, "Tidak Ada Data!", Snackbar.LENGTH_LONG).show();
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
                            mViewModel.ExportHarian(laporanHarianModels, userModel.getNamaUser());
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
