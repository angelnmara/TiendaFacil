package com.lamarrulla.www.tiendafacil.contents;

import android.database.Cursor;

import com.lamarrulla.www.tiendafacil.listas.itemListArticle;
import com.lamarrulla.www.tiendafacil.listas.itemListMarca;

import java.util.ArrayList;

/**
 * Created by Qualtop on 06/10/2016.
 */

public class genericContentCursor {
    public static ArrayList Item;
    private static int Count;

    public static void getData(Cursor cursor, String lista){
        Item = new ArrayList();

        if(cursor.moveToFirst()){
            do{
                switch (lista){
                    case "itemListArticle":
                        Item.add(new itemListArticle(cursor.getInt(cursor.getColumnIndex("article_id")),
                                cursor.getString(cursor.getColumnIndex("article_name")),
                                cursor.getString(cursor.getColumnIndex("article_desc")),
                                cursor.getDouble(cursor.getColumnIndex("article_precio")),
                                cursor.getDouble(cursor.getColumnIndex("article_costo")),
                                cursor.getBlob(cursor.getColumnIndex("article_foto")),
                                cursor.getInt(cursor.getColumnIndex("article_stock"))));
                        break;
                    case"itemListMarca":
                        Item.add(new itemListMarca(cursor.getInt(cursor.getColumnIndex("marca_id")),
                                cursor.getString(cursor.getColumnIndex("marca_code")),
                                cursor.getString(cursor.getColumnIndex("marca_name")),
                                cursor.getBlob(cursor.getColumnIndex("marca_imagen"))));
                        break;
                    default:
                        break;
                }
                /*if (cursor.getString(cursor.getColumnIndex("exchangeclub")) == null){
                    mExchangeclub = "";
                }
                else{
                    mExchangeclub = curUsuario.getString(curUsuario.getColumnIndex("exchangeclub"));
                };*/
            }while (cursor.moveToNext());
        }
    }

}
