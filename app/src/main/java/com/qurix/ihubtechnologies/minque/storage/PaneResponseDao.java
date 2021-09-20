package com.qurix.ihubtechnologies.minque.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.qurix.ihubtechnologies.minque.model.respose.PaneResponse;

import java.util.List;

@Dao
public interface PaneResponseDao {

    @Insert
    void insertAll(List<PaneResponse> paneResponses);

    @Insert
    void insert(PaneResponse paneResponse);

    @Query("DELETE FROM PaneResponse")
    public void dropTable();

    @Query("SELECT * FROM PaneResponse")
    List<PaneResponse> getAll();


}
