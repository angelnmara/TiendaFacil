package com.lamarrulla.mitiendita.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.lamarrulla.mitiendita.data.model.Articulo;

import java.util.List;

@Dao
public interface ArticuloDao {
    @Insert
    void insert(Articulo articulo);
    @Update
    void update(Articulo articulo);
    @Delete
    void delete(Articulo articulo);
    @Query("DELETE FROM tbArticulo")
    void deleteAllArticulos();
    @Query("SELECT * FROM tbArticulo")
    LiveData<List<Articulo>> getAllArticulos();
}
