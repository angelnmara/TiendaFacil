package com.lamarrulla.www.tiendafacil.cursores;

import android.content.Context;
import android.database.Cursor;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;

/**
 * Created by Qualtop on 25/10/2016.
 */

public class allCursors {

    public Cursor getCursor() {
        return mCursor;
    }

    public void setCursor(Cursor cursor) {
        mCursor = cursor;
    }

    public Cursor mCursor;

    String[] projectionArticle = new String[] { TiendaFacilContract.article._ID,
            TiendaFacilContract.article.ARTICLE_NAME,
            TiendaFacilContract.article.ARTICLE_DESC,
            TiendaFacilContract.article.ARTICLE_PRECIO,
            TiendaFacilContract.article.ARTICLE_COSTO,
            TiendaFacilContract.article.ARTICLE_FOTO,
            TiendaFacilContract.article.ARTICLE_STOCK,
            TiendaFacilContract.article.ARTICLE_MARCA_ID };

    public void getCursorArticles(Context context){
        mCursor = context.getContentResolver().query(TiendaFacilContract.article.CONTENT_URI, projectionArticle, null, null, null);
    }

}
