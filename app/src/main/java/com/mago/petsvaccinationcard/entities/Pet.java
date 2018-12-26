package com.mago.petsvaccinationcard.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import com.google.gson.Gson;
import com.mago.petsvaccinationcard.db.VaccinationCardContract.PetData;
import com.mago.petsvaccinationcard.db.VaccinationCardContract.OwnerData;



/**
 * Created by jorgemartinez on 17/12/18.
 */
@Entity(tableName = PetData.TABLE_NAME,
        indices = {@Index(value = PetData.PET_ID, unique = true),
                    @Index(value = OwnerData.OWNER_ID)},
        foreignKeys = @ForeignKey(entity = Owner.class,
                                    parentColumns = OwnerData.OWNER_ID,
                                    childColumns = OwnerData.OWNER_ID,
                                    onDelete = CASCADE,
                                    onUpdate = CASCADE))
public class Pet {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PetData.PET_ID)
    private int petId;

    @ColumnInfo(name = PetData.PET_KIND)
    private String petKind;

    @ColumnInfo(name = PetData.PET_SEX)
    private String petSex;

    @ColumnInfo(name = PetData.PET_NAME)
    private String petName;

    @ColumnInfo(name = PetData.PET_BIRTHDAY)
    private String petBirthday;

    @ColumnInfo(name = PetData.PET_BREED)
    private String petBreed;

    @ColumnInfo(name = PetData.PET_COLOR)
    private String petColor;

    @ColumnInfo(name = PetData.PET_ID_NUMBER)
    private String petIdNumber;

    @ColumnInfo(name = PetData.PET_PARTICULAR_SIGNS)
    private String petParticularSigns;

    @ColumnInfo(name = PetData.PET_MICRO_CHIP)
    private char petMicroChip;

    @ColumnInfo(name = PetData.PET_TATTOO)
    private char petTattoo;

    @ColumnInfo(name = PetData.PET_PHOTO)
    private String petPhoto;

    @ColumnInfo(name = OwnerData.OWNER_ID)
    private int ownerId;

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public Pet fromJSON(String json) {
        return new Gson().fromJson(json, Pet.class);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBirthday() {
        return petBirthday;
    }

    public void setPetBirthday(String petBirthday) {
        this.petBirthday = petBirthday;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetIdNumber() {
        return petIdNumber;
    }

    public void setPetIdNumber(String petIdNumber) {
        this.petIdNumber = petIdNumber;
    }

    public String getPetParticularSigns() {
        return petParticularSigns;
    }

    public void setPetParticularSigns(String petParticularSigns) {
        this.petParticularSigns = petParticularSigns;
    }

    public char getPetMicroChip() {
        return petMicroChip;
    }

    public void setPetMicroChip(char petMicroChip) {
        this.petMicroChip = petMicroChip;
    }

    public char getPetTattoo() {
        return petTattoo;
    }

    public void setPetTattoo(char petTattoo) {
        this.petTattoo = petTattoo;
    }

    public String getPetPhoto() {
        return petPhoto;
    }

    public void setPetPhoto(String petPhoto) {
        this.petPhoto = petPhoto;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
