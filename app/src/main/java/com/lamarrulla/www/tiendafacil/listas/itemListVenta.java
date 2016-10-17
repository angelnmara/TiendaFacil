package com.lamarrulla.www.tiendafacil.listas;

/**
 * Created by Qualtop on 13/10/2016.
 */

public class itemListVenta {

    String venta_name;
    String venta_desc;
    Double venta_precio;
    String venta_marca;

    byte[] venta_foto;

    public itemListVenta(String venta_name, String venta_desc, Double venta_costo, String venta_marca, byte[] venta_foto){
        this.venta_name = venta_name;
        this.venta_desc = venta_desc;
        this.venta_precio = venta_costo;
        this.venta_marca = venta_marca;
        this.venta_foto = venta_foto;
    }

    public String getVenta_marca() {
        return venta_marca;
    }

    public void setVenta_marca(String venta_marca) {
        this.venta_marca = venta_marca;
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

    public Double getVenta_precio() {
        return venta_precio;
    }

    public void setVenta_precio(Double venta_precio) {
        this.venta_precio = venta_precio;
    }

    public byte[] getVenta_foto() {
        return venta_foto;
    }

    public void setVenta_foto(byte[] venta_foto) {
        this.venta_foto = venta_foto;
    }
}
