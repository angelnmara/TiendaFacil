package com.lamarrulla.mitiendita.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbArticulo")
public class Articulo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String articuloNombre;
    private String articuloDescripcion;
    private int articuloCantidad;

    public Articulo(String articuloNombre, String articuloDescripcion, int articuloCantidad) {
        this.articuloNombre = articuloNombre;
        this.articuloDescripcion = articuloDescripcion;
        this.articuloCantidad = articuloCantidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getArticuloNombre() {
        return articuloNombre;
    }

    public String getArticuloDescripcion() {
        return articuloDescripcion;
    }

    public int getArticuloCantidad() {
        return articuloCantidad;
    }
}
