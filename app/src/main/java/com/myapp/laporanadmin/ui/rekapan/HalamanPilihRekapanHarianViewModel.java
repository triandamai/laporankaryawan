package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.response.ResponseGetLaporanHarian;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class HalamanPilihRekapanHarianViewModel extends ViewModel {
    private Context context;
    private ApiService service;
    private RekapanListener listener;
    private ExportListener exportListener;

    public HalamanPilihRekapanHarianViewModel(Context context) {
        this.context = context;
        this.service = LaporanRepository.getService(context);

    }

    public void setRekapanListener(RekapanListener l) {
        this.listener = l;
    }

    public void setExportListener(ExportListener exportListener) {
        this.exportListener = exportListener;
    }

    public void setharianrekap(LaporanHarianRekapanRequestData data) {
        listener.onStart();
        service.getAllLaporanharianRekapan(data).enqueue(new Callback<ResponseGetLaporanHarian>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanHarian> call, Response<ResponseGetLaporanHarian> response) {
                if (cek(response.code(), context, "getData lap harian")) {

                    //  Log.e(TAG,response.toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData() != null) {

                            listener.onSuccessHarian(response.body().getData());
                        } else {
                            listener.onFailed("Tidak Ada Data");
                        }
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanHarian> call, Throwable t) {
                Log.e("HEHE", "gagal ambil laporanharian" + t.toString());
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

    public void ExportHarian(List<LaporanHarianModel> list, String nama) {
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
        for (LaporanHarianModel item : list) {
            no++;
            Row row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(no);
            row.createCell(1).setCellValue(item.getUser().getIdUser());
            row.createCell(2).setCellValue(item.getUser().getNamaUser());
            row.createCell(3).setCellValue(item.getKeteranganLaporanharian());
            row.createCell(4).setCellValue(item.getStatusLaporanharian());
            row.createCell(5).setCellValue(item.getCreatedAt().substring(0, 10));
            rownum++;
        }

        FileOutputStream fileOutputStream = null;


        try {
            fileOutputStream = new FileOutputStream(
                    new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS), "Laporan_Harian_" + nama + ".xlsx"));
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            workbook.close();
            exportListener.onSucces("File Tersimpan di Downloads/Laporan_Harian_" + nama + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
            exportListener.onError(e.getMessage());
        }
    }

}
