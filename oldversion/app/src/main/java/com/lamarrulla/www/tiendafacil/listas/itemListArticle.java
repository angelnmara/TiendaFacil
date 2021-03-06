package com.lamarrulla.www.tiendafacil.listas;

import java.sql.Blob;

/**
 * Created by Qualtop on 06/10/2016.
 */

public class itemListArticle {
    Integer article_id;
    String article_name;
    String article_desc;
    Double article_precio;
    Double article_costo;
    byte[] article_foto;
    Integer article_stock;
    Integer article_marca_id;

    public itemListArticle(Integer article_id, String article_name, String article_desc, Double article_precio, Double article_costo, byte[] article_foto, Integer article_stock, Integer article_marca_id){
        this.article_id = article_id;
        this.article_name = article_name;
        this.article_desc = article_desc;
        this.article_precio = article_precio;
        this.article_costo = article_costo;
        this.article_foto = article_foto;
        this.article_stock = article_stock;
        this.article_marca_id = article_marca_id;
    }

    public Integer getArticle_marca_id() {
        return article_marca_id;
    }

    public void setArticle_marca_id(Integer article_marca_id) {
        this.article_marca_id = article_marca_id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getArticle_desc() {
        return article_desc;
    }

    public void setArticle_desc(String article_desc) {
        this.article_desc = article_desc;
    }

    public Double getArticle_precio() {
        return article_precio;
    }

    public void setArticle_precio(Double article_precio) {
        this.article_precio = article_precio;
    }

    public Double getArticle_costo() {
        return article_costo;
    }

    public void setArticle_costo(Double article_costo) {
        this.article_costo = article_costo;
    }

    public byte[] getArticle_foto() {
        return article_foto;
    }

    public void setArticle_foto(byte[] article_foto) {
        this.article_foto = article_foto;
    }

    public Integer getArticle_stock() {
        return article_stock;
    }

    public void setArticle_stock(Integer article_stock) {
        this.article_stock = article_stock;
    }
}
