package com.mago.petsvaccinationcard.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.databinding.ContentAdapterPetBinding;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.libs.base.ImageLoader;

import java.util.ArrayList;

/**
 * Created by jorgemartinez on 24/12/18.
 */
public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.ViewHolder>{
    private ArrayList<Vaccine> vaccineList;

    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public VaccineAdapter(ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context) {
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ContentAdapterPetBinding view = ContentAdapterPetBinding.inflate(layoutInflater, viewGroup, false);
        return new VaccineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        bindVaccineView(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return vaccineList.size();
    }

    private void bindVaccineView(ViewHolder viewHolder, int position){
        Vaccine vaccine = vaccineList.get(position);

        String vaccineName = vaccine.getVaccineName();
        String implementationDate = vaccine.getVaccineDate();

        viewHolder.viewBinding.tvPetName.setText(vaccineName);
        viewHolder.viewBinding.tvOwnerName.setText(implementationDate);
        viewHolder.viewBinding.imgAdapter.setImageResource(R.drawable.ic_vaccine);

        viewHolder.setOnItemClickListener(vaccine, onItemClickListener, viewHolder.itemView, position);
    }

    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ContentAdapterPetBinding viewBinding;

        ViewHolder(ContentAdapterPetBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        void setOnItemClickListener(Vaccine vaccine, OnItemClickListener onItemClickListener, View view, int position) {
            viewBinding.getRoot().setOnClickListener(
                    v -> onItemClickListener.onItemClick(vaccine, view, position)
            );
        }
    }
}
