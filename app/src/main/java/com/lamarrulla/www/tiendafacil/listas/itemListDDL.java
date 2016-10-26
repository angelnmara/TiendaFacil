package com.lamarrulla.www.tiendafacil.listas;

/**
 * Created by Qualtop on 25/10/2016.
 */

public class itemListDDL {

    private int id;
    private String value;

    public itemListDDL(int id, String value){
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
