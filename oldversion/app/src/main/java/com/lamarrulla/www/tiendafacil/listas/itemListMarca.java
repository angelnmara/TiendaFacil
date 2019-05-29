package com.lamarrulla.www.tiendafacil.listas;

/**
 * Created by Qualtop on 19/10/2016.
 */

public class itemListMarca {

    int marca_id;
    String marca_code;
    String marca_name;
    byte[] marca_imagen;

    public itemListMarca(int marca_id, String marca_code, String marca_name, byte[] marca_imagen){
        this.marca_id = marca_id;
        this.marca_code = marca_code;
        this.marca_name = marca_name;
        this.marca_imagen = marca_imagen;
    }

    public int getMarca_id() {
        return marca_id;
    }

    public void setMarca_id(int marca_id) {
        this.marca_id = marca_id;
    }

    public String getMarca_code() {
        return marca_code;
    }

    public void setMarca_code(String marca_code) {
        this.marca_code = marca_code;
    }

    public String getMarca_name() {
        return marca_name;
    }

    public void setMarca_name(String marca_name) {
        this.marca_name = marca_name;
    }

    public byte[] getMarca_imagen() {
        return marca_imagen;
    }

    public void setMarca_imagen(byte[] marca_imagen) {
        this.marca_imagen = marca_imagen;
    }
}
