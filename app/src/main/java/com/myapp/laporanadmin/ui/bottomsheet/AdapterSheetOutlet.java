package com.myapp.laporanadmin.ui.bottomsheet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.R;
import com.myapp.databinding.ItemSheetOutletBinding;
import com.myapp.domain.realmobject.OutletObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterSheetOutlet extends RecyclerView.Adapter<AdapterSheetOutlet.MyViewHolder> {
    private List<OutletObject> responseRts = new ArrayList<>();
    private ItemClicked callback;

    public AdapterSheetOutlet(ItemClicked callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSheetOutletBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_sheet_outlet, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setData(responseRts.get(position));
        holder.binding.setCallback(callback);
    }

    public void setData(List<OutletObject> penduduk_models) {
        if (this.responseRts == null) {
            this.responseRts.addAll(penduduk_models);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterSheetOutlet.this.responseRts.size();
                }

                @Override
                public int getNewListSize() {
                    return responseRts.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterSheetOutlet.this.responseRts.get(oldItemPosition).getIdOutlet() == responseRts.get(newItemPosition).getIdOutlet();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    OutletObject lama = responseRts.get(oldItemPosition);
                    OutletObject baru = responseRts.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);

                }
            });
            this.responseRts = penduduk_models;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public int getItemCount() {
        return responseRts == null ? 0 : responseRts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemSheetOutletBinding binding;

        public MyViewHolder(ItemSheetOutletBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemClicked {
        void onClick(OutletObject kotaModel);
    }
}
