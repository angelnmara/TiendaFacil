package com.lamarrulla.www.tiendafacil.listas;

/**
 * Created by Qualtop on 19/10/2016.
 */

public class itemListMenu {
    public final String id;
    public final String content;
    public final String details;

    public itemListMenu(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}
