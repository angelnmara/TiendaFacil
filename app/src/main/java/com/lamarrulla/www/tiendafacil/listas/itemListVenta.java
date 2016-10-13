package com.lamarrulla.www.tiendafacil.listas;

import java.sql.Blob;

/**
 * Created by Qualtop on 13/10/2016.
 */

public class itemListVenta {

    String venta_name;
    String venta_desc;
    Double venta_costo;
    byte[] venta_foto;

    public itemListVenta(String venta_name, String venta_desc, Double venta_costo, byte[] venta_foto){
        this.venta_name = venta_name;
        this.venta_desc = venta_desc;
        this.venta_costo = venta_costo;
        this.venta_foto = venta_foto;
    }

    public String getVenta_name() {
        return venta_name;
    }

    public void setVenta_name(String venta_name) {
        this.venta_name = venta_name;
    }

    public String getVenta_desc() {
        return venta_desc;
    }

    public void setVenta_desc(String venta_desc) {
        this.venta_desc = venta_desc;
    }

    public Double getVenta_costo() {
        return venta_costo;
    }

    public void setVenta_costo(Double venta_costo) {
        this.venta_costo = venta_costo;
    }

    public byte[] getVenta_foto() {
        return venta_foto;
    }

    public void setVenta_foto(byte[] venta_foto) {
        this.venta_foto = venta_foto;
    }
}
