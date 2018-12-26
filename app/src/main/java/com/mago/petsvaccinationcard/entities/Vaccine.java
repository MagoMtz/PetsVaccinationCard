package com.mago.petsvaccinationcard.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.Gson;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import static com.mago.petsvaccinationcard.db.VaccinationCardContract.VaccineData;
import static com.mago.petsvaccinationcard.db.VaccinationCardContract.PetData;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Entity(tableName = VaccineData.TABLE_NAME,
        indices = {@Index(value = PetData.PET_ID, unique = true),
                    @Index(value = VaccineData.VACCINE_ID, unique = true)},
        foreignKeys = @ForeignKey(entity = Pet.class,
                                    parentColumns = PetData.PET_ID,
                                    childColumns = PetData.PET_ID,
                                    onDelete = CASCADE,
                                    onUpdate = CASCADE))
public class Vaccine {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = VaccineData.VACCINE_ID)
    private int vaccineId;

    @ColumnInfo(name = VaccineData.VACCINE_NAME)
    private String vaccineName;

    @ColumnInfo(name = VaccineData.VACCINE_DATE)
    private String vaccineDate;

    @ColumnInfo(name = VaccineData.VACCINE_TICKET)
    private String vaccineTicket;

    @ColumnInfo(name = VaccineData.VACCINE_NEXT_APPOINTMENT)
    private String vaccineNextAppointment;

    @ColumnInfo(name = PetData.PET_ID)
    private int petId;

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public Vaccine fromJSON(String json) {
        return new Gson().fromJson(json, Vaccine.class);
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(String vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public String getVaccineTicket() {
        return vaccineTicket;
    }

    public void setVaccineTicket(String vaccineTicket) {
        this.vaccineTicket = vaccineTicket;
    }

    public String getVaccineNextAppointment() {
        return vaccineNextAppointment;
    }

    public void setVaccineNextAppointment(String vaccineNextAppointment) {
        this.vaccineNextAppointment = vaccineNextAppointment;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
