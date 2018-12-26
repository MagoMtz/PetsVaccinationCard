package com.mago.petsvaccinationcard.libs.di;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.adapter.VaccineAdapter;
import com.mago.petsvaccinationcard.db.AppDataBase;
import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.libs.GlideImageLoader;
import com.mago.petsvaccinationcard.libs.GreenRobotEventBus;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.libs.base.ImageLoader;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Module
public class LibsModule {
    private Activity activity;
    private PetDAO petDAO;
    private OwnerDAO ownerDAO;
    private VaccineDAO vaccineDAO;

    public LibsModule(Activity activity) {
        this.activity = activity;

        AppDataBase appDataBase = AppDataBase.getINSTANCE(activity);
        this.petDAO = appDataBase.petDAO();
        this.ownerDAO = appDataBase.ownerDAO();
        this.vaccineDAO = appDataBase.vaccineDAO();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager() {
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return activity.getApplicationContext();
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return activity;
    }

    @Provides
    @Singleton
    PetDAO providesPetDao() {
        return petDAO;
    }

    @Provides
    @Singleton
    OwnerDAO providesOwnerDAO() {
        return ownerDAO;
    }

    @Provides
    @Singleton
    VaccineDAO providesVaccineDAO() {
        return vaccineDAO;
    }

    @Provides
    @Singleton
    ListAdapter providesListAdapter(ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context) {
        return new ListAdapter(imageLoader, onItemClickListener, context);
    }

    @Provides
    @Singleton
    VaccineAdapter providesVaccineAdapter(ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context) {
        return new VaccineAdapter(imageLoader, onItemClickListener, context);
    }

    @Provides
    @Singleton
    ArrayList<Pet> providesEmptyList() {
        return new ArrayList<>();
    }
}
