package com.mago.petsvaccinationcard.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.databinding.ContentAdapterPetBinding;
import com.mago.petsvaccinationcard.db.AppDataBase;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.libs.base.ImageLoader;
import com.mago.petsvaccinationcard.util.Flags;
import com.mago.petsvaccinationcard.util.StringValidator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Pet> petList;
    private ArrayList<Owner> ownerList;
    private Flags.ListType listType;

    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public ListAdapter(ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context) {
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (listType) {
            case PET:
                return createPetViewHolder(viewGroup);
            case OWNER:
                return createOwnerViewHolder(viewGroup);
            default:
                return createPetViewHolder(viewGroup);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        switch (listType){
            case PET:
                bindPetView(viewHolder, i);
                break;
            case OWNER:
                bindOwnerView(viewHolder, i);
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (listType){
            case PET:
                return petList.size();
            case OWNER:
                return ownerList.size();
            default:
                return 0;
        }
    }

    private void bindPetView(ViewHolder viewHolder, int position) {
        Pet pet = petList.get(position);

        Owner owner = AppDataBase.getINSTANCE(context).ownerDAO().ownerById(pet.getOwnerId());

        String petName = pet.getPetName();
        String ownerName = owner.getOwnerFirstName() + " " +owner.getOwnerLastName();

        //imageLoader.load(viewHolder.viewBinding.imgAdapter, petURLPhoto);

        viewHolder.viewBinding.tvPetName.setText(petName);
        viewHolder.viewBinding.tvOwnerName.setText(ownerName);
        viewHolder.viewBinding.imgAdapter.setImageResource(R.drawable.ic_collar);

        viewHolder.setOnItemClickListener(pet, onItemClickListener, viewHolder.itemView, position);

    }

    private void bindOwnerView(ViewHolder viewHolder, int position){
        Owner owner = ownerList.get(position);

        String ownerName = owner.getOwnerFirstName() + " " + owner.getOwnerLastName();

        //imageLoader.load(viewHolder.viewBinding.imgAdapter, Uri.parse("android.resource://"+context.getPackageName()+"/drawable/ic_account").toString());

        viewHolder.viewBinding.tvOwnerName.setText(ownerName);
        viewHolder.viewBinding.imgAdapter.setImageResource(R.drawable.ic_account);

        viewHolder.setOnItemClickListener(owner, onItemClickListener, viewHolder.itemView, position);

        if (petList == null)
            return;

        Pet pet = petList.get(0);
        if (pet != null)
            if (pet.getOwnerId() == owner.getOwnerId())
                viewHolder.itemView.setSelected(true);
    }

    private ViewHolder createPetViewHolder(ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ContentAdapterPetBinding view = ContentAdapterPetBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(view);
    }

    private ViewHolder createOwnerViewHolder(ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ContentAdapterPetBinding view = ContentAdapterPetBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(view);
    }

    public void setListType(Flags.ListType listType) {
        this.listType = listType;
    }

    public void setPetList(ArrayList<Pet> petList) {
        this.petList = petList;
        notifyDataSetChanged();
    }

    public void setOwnerList(ArrayList<Owner> ownerList) {
        this.ownerList = ownerList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ContentAdapterPetBinding viewBinding;

        ViewHolder(ContentAdapterPetBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        void setOnItemClickListener(Pet pet, OnItemClickListener onItemClickListener, View view, int position) {
            viewBinding.getRoot().setOnClickListener(
                    v -> onItemClickListener.onItemClick(pet, view, position)
            );
        }

        void setOnItemClickListener(Owner owner, OnItemClickListener onItemClickListener, View view, int position) {
            viewBinding.getRoot().setOnClickListener(
                    v -> onItemClickListener.onItemClick(owner, view, position)
            );
        }

    }



}
