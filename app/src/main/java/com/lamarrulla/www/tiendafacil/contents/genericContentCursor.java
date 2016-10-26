package com.lamarrulla.www.tiendafacil.contents;

import android.database.Cursor;

import com.lamarrulla.www.tiendafacil.listas.itemListArticle;
import com.lamarrulla.www.tiendafacil.listas.itemListMarca;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.ArticleColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.MarcaColumns;

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
                        Item.add(new itemListArticle(cursor.getInt(cursor.getColumnIndex(ArticleColumns._ID)),
                                cursor.getString(cursor.getColumnIndex(ArticleColumns.ARTICLE_NAME)),
                                cursor.getString(cursor.getColumnIndex(ArticleColumns.ARTICLE_DESC)),
                                cursor.getDouble(cursor.getColumnIndex(ArticleColumns.ARTICLE_PRECIO)),
                                cursor.getDouble(cursor.getColumnIndex(ArticleColumns.ARTICLE_COSTO)),
                                cursor.getBlob(cursor.getColumnIndex(ArticleColumns.ARTICLE_FOTO)),
                                cursor.getInt(cursor.getColumnIndex(ArticleColumns.ARTICLE_COSTO)),
                                cursor.getInt(cursor.getColumnIndex(ArticleColumns.ARTICLE_MARCA_ID))));
                        break;
                    case"itemListMarca":
                        Item.add(new itemListMarca(cursor.getInt(cursor.getColumnIndex(MarcaColumns._ID)),
                                cursor.getString(cursor.getColumnIndex(MarcaColumns.MARCA_CODE)),
                                cursor.getString(cursor.getColumnIndex(MarcaColumns.MARCA_NAME)),
                                cursor.getBlob(cursor.getColumnIndex(MarcaColumns.MARCA_IMAGEN))));
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
