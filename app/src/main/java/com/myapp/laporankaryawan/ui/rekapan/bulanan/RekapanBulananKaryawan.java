package com.myapp.laporankaryawan.ui.rekapan.bulanan;

import android.Manifest;
import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.myapp.data.persistensi.MyUser;
import com.myapp.databinding.RekapanBulananKaryawanFragmentBinding;
import com.myapp.datepicker.DatePickerMonthAndYear;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.laporanadmin.callback.AdapterItemClicked;
import com.myapp.laporanadmin.callback.ExportListener;
import com.myapp.laporanadmin.callback.HalamanRekapanCallback;
import com.myapp.laporanadmin.callback.RekapanListener;
import com.myapp.laporanadmin.ui.rekapan.bulanan.AdapterRekapanBulananAdmin;
import com.myapp.laporankaryawan.BaseKaryawanFragment;
import com.myapp.laporankaryawan.ui.detaillaporanbulanankaryawan.DetailBulananKaryawan;
import com.myapp.laporankaryawan.ui.rekapan.RekapanKaryawanFactory;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RekapanBulananKaryawan extends BaseKaryawanFragment {

    private RekapanBulananKaryawanViewModel mViewModel;
    private RekapanBulananKaryawanFragmentBinding binding;
    private DatePickerMonthAndYear datePickerMonthAndYear;
    private int bulan = 0;
    private int tahun = 0;
    private AdapterRekapanBulananAdmin adapterRekapanBulananAdmin;
    private boolean AdaData = false;
    private List<LaporanBulananModel> laporanHarianModels = new ArrayList<>();

    public static RekapanBulananKaryawan newInstance() {
        return new RekapanBulananKaryawan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.rekapan_bulanan_karyawan_fragment, container, false);
        setActionBar(binding.toolbar, "Rekap Data Bulanan", "");
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(requireActivity(), new RekapanKaryawanFactory(getContext())).get(RekapanBulananKaryawanViewModel.class);
        mViewModel.setRekapanListener(prosesrekap);
        mViewModel.setExportListener(exportListener);
        binding.setIsLoading(false);
        binding.setEvent(halamanRekapanCallback);
        adapterRekapanBulananAdmin = new AdapterRekapanBulananAdmin(adapterItemClicked);
        binding.rv.setAdapter(adapterRekapanBulananAdmin);
        setDefault();

        datePickerMonthAndYear = new DatePickerMonthAndYear();
        datePickerMonthAndYear.setListener(dateSetListener);

        return binding.getRoot();
    }


    private void setDefault() {
        Calendar calendar = Calendar.getInstance();
        bulan = calendar.get(Calendar.MONTH) + 1;
        tahun = calendar.get(Calendar.YEAR);
        binding.setKaryawan(MyUser.getInstance(getContext()).getUser());
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            binding.tvKaryawan.setText(MyUser.getInstance(getContext()).getUser().getNamaUser());
            binding.tvTanggal.setText(bulan + " " + tahun);
        } catch (NullPointerException e) {

        }
    }

    private HalamanRekapanCallback halamanRekapanCallback = new HalamanRekapanCallback() {
        @Override
        public void onSelectKaryawan(View v) {

        }

        @Override
        public void onSelectBulan(View v) {
            datePickerMonthAndYear.show(getActivity().getSupportFragmentManager(), "ambil tanggal");
        }

        @Override
        public void onSync(View v) {

            LaporanBulananRequestData l = new LaporanBulananRequestData();
            try {
                l.setIdUser(MyUser.getInstance(getContext()).getUser().getIdUser());
                l.setBulanLaporanbulanan(bulan);
                l.setTahunLaporanbulanan(tahun);

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
            LaporanBulananModel obj = adapterRekapanBulananAdmin.getFromPosition(pos);

            Bundle bundle = new Bundle();
            bundle.putString("idlaporanbulanan", obj.getIdLaporanbulanan());
            bundle.putString("statuslaporanbulanan", obj.getStatusLaporanbulanan());
            DetailBulananKaryawan bulanan = new DetailBulananKaryawan();
            bulanan.setArguments(bundle);
            replaceFragment(bulanan, null);
        }
    };

    private ExportListener exportListener = new ExportListener() {
        @Override
        public void onStart() {
            showProgress("Meng-Export Rekapan " + MyUser.getInstance(getContext()).getUser().getNamaUser());
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
            adapterRekapanBulananAdmin.setData(laporanHarianModels);
            RekapanBulananKaryawan.this.laporanHarianModels = laporanHarianModels;
            RekapanBulananKaryawan.this.AdaData = true;
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            RekapanBulananKaryawan.this.AdaData = false;
            adapterRekapanBulananAdmin.clearData();
            Snackbar.make(binding.rv, "Tidak Ada Data", Snackbar.LENGTH_INDEFINITE).show();
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            RekapanBulananKaryawan.this.AdaData = false;
            adapterRekapanBulananAdmin.clearData();
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
                            mViewModel.ExportBulanan(laporanHarianModels, MyUser.getInstance(getContext()).getUser().getNamaUser());
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
