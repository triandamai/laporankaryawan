package com.myapp.laporanadmin.callback;

public interface AdapterItemClicked {
    void onClick(int pos);
    void onEdit(int pos);
    void onDelete(int pos);
    void onDetail(int pos);
}
