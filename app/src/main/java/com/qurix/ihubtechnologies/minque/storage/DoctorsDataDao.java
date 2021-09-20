package com.qurix.ihubtechnologies.minque.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qurix.ihubtechnologies.minque.model.respose.DoctorsData;

import java.util.List;

@Dao
public interface DoctorsDataDao {
    @Insert
    void insertAll(List<DoctorsData> doctorsData);

    @Query("SELECT * FROM doctorsdata")
    List<DoctorsData> getAll();

    @Insert
    void insert(DoctorsData doctorsData);

    @Delete
    void delete(DoctorsData doctorsData);

    @Update
    void update(DoctorsData doctorsData);

    @Query("DELETE FROM DoctorsData")
    public void dropTable();

    @Query("SELECT * FROM doctorsdata WHERE doctorId =:doctorId")
    DoctorsData getSingleProduct(String doctorId);

    @Query("SELECT * FROM doctorsdata WHERE doctorName =:doctorName")
    DoctorsData getSingleProductbyName(String doctorName);



}
