package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;
import android.os.Environment;

import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.serialize.ResponseGetLaporanBulanan;
import com.myapp.laporanadmin.callback.ExportListener;
import com.myapp.laporanadmin.callback.RekapanListener;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class HalamanPilihRekapanBulananViewModel extends ViewModel {
    private Context context;
    private ApiService service;
    private RekapanListener listener;
    private ExportListener exportListener;
    private Realm realm;

    public HalamanPilihRekapanBulananViewModel(Context context) {
        this.context = context;
        this.service = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();

    }

    public void setRekapanListener(RekapanListener l) {
        this.listener = l;
    }

    public void setExportListener(ExportListener exportListener) {
        this.exportListener = exportListener;
    }

    public void setharianrekap(LaporanBulananRequestData data) {
        listener.onStart();
        service.getAllLaporanbulananRekap(data).enqueue(new Callback<ResponseGetLaporanBulanan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanBulanan> call, Response<ResponseGetLaporanBulanan> response) {
                if (cek(response.code(), context, "getData lap harian")) {

                    //  Log.e(TAG,response.toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData() != null) {
                            for (LaporanBulananModel item : response.body().getData()) {
                                LaporanBulananObject laporanBulananObject = new LaporanBulananObject();

                                laporanBulananObject.setIdLaporanbulanan(item.getIdLaporanbulanan());
                                laporanBulananObject.setCreatedAt(item.getCreatedAt());
                                laporanBulananObject.setFotoUser(item.getUser().getFotoUser());
                                laporanBulananObject.setIdUser(item.getIdUser());
                                laporanBulananObject.setIsiLaporanbulanan(item.getIsiLaporanbulanan());
                                laporanBulananObject.setLevelUser(item.getUser().getLevelUser());
                                laporanBulananObject.setNamaUser(item.getUser().getNamaUser());
                                laporanBulananObject.setNipUser(item.getUser().getNipUser());
                                laporanBulananObject.setStatusLaporanbulanan(item.getStatusLaporanbulanan());
                                laporanBulananObject.setUpdatedAt(item.getUpdatedAt());

                                realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(laporanBulananObject));

                            }

                            listener.onSuccessBulanan(response.body().getData());
                        } else {
                            listener.onFailed("Tidak Ada Data");
                        }
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanBulanan> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    private static String[] columns = {
            "NO",
            "NIP",
            "NAMA",
            "ISI LAPORAN",
            "STATUS",
            "TANGGAL"
    };

    public void ExportBulanan(List<LaporanBulananModel> list, String nama) {
        exportListener.onStart();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("REKAP DATA");

        Font headerfont = workbook.createFont();
        headerfont.setBold(true);
        headerfont.setFontHeightInPoints((short) 14);
        headerfont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerfont);

        Row hederRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = hederRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);

        }

        int rownum = 1;
        int no = 0;
        for (LaporanBulananModel item : list) {
            no++;
            Row row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(no);
            row.createCell(1).setCellValue(item.getUser().getIdUser());
            row.createCell(2).setCellValue(item.getUser().getNamaUser());
            row.createCell(3).setCellValue(item.getIsiLaporanbulanan());
            row.createCell(4).setCellValue(item.getStatusLaporanbulanan());
            row.createCell(5).setCellValue(item.getCreatedAt().substring(0, 10));
            rownum++;
        }

        FileOutputStream fileOutputStream = null;


        try {
            fileOutputStream = new FileOutputStream(
                    new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), "Laporan_Bulanan_" + nama + ".xlsx"));
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            workbook.close();
            exportListener.onSucces("File Tersimpan di Downloads/Laporan_Bulanan_" + nama + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
            exportListener.onError(e.getMessage());
        }
    }
}
