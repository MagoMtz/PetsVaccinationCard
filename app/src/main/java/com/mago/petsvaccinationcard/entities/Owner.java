package com.mago.petsvaccinationcard.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.Gson;
import com.mago.petsvaccinationcard.db.VaccinationCardContract.OwnerData;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Entity(tableName = OwnerData.TABLE_NAME,
        indices = @Index(value = OwnerData.OWNER_ID))
public class Owner {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = OwnerData.OWNER_ID)
    private int ownerId;

    @ColumnInfo(name = OwnerData.OWNER_LAST_NAME)
    private String ownerLastName;

    @ColumnInfo(name = OwnerData.OWNER_FIRST_NAME)
    private String ownerFirstName;

    @ColumnInfo(name = OwnerData.OWNER_ADDRESS)
    private String ownerAddress;

    @ColumnInfo(name = OwnerData.OWNER_PHONE)
    private String ownerPhone;

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public Owner fromJSON(String json) {
        return new Gson().fromJson(json, Owner.class);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }
}
