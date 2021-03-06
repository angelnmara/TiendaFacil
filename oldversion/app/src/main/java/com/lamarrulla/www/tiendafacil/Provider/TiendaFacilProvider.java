package com.lamarrulla.www.tiendafacil.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.ArticleColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.UserColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.VentaColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.MarcaColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.VentaMarcaColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.article;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.user;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.venta;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.marca;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.venta_marca;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilDatabase.Tables;

import java.util.ArrayList;

/**
 * Created by Qualtop on 05/09/2016.
 */
public class TiendaFacilProvider extends ContentProvider {

    private static final String TAG = "TiendaFacilProvider";

    private static final int CODE_ALL_USERS = 1;
    private static final int CODE_SINGLE_USER = 2;

    private static final int CODE_ALL_ARTICLES = 3;
    private static final int CODE_SINGLE_ARTICLE = 4;

    private static final int CODE_ALL_VENTA = 5;
    private static final int CODE_SINGLE_VENTA = 6;

    private static final int CODE_ALL_MARCA = 7;
    private static final int CODE_SINGLE_MARCA = 8;

    private static final int CODE_ALL_VENTA_MARCA = 9;
    private static final int CODE_SINGLE_VENTA_MARCA = 10;

    private Context context;
    private static TiendaFacilDatabase mOpenHelper;
    private static final UriMatcher sUri = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final String authority = TiendaFacilContract.CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(authority, TiendaFacilContract.PATH_USER, CODE_ALL_USERS);
        matcher.addURI(authority, TiendaFacilContract.PATH_USER + "/#", CODE_SINGLE_USER);

        matcher.addURI(authority, TiendaFacilContract.PATH_ARTICLE, CODE_ALL_ARTICLES);
        matcher.addURI(authority, TiendaFacilContract.PATH_ARTICLE + "/#", CODE_SINGLE_ARTICLE);

        matcher.addURI(authority, TiendaFacilContract.PATH_VENTA, CODE_ALL_VENTA);
        matcher.addURI(authority, TiendaFacilContract.PATH_VENTA + "/#", CODE_SINGLE_VENTA);

        matcher.addURI(authority, TiendaFacilContract.PATH_MARCA, CODE_ALL_MARCA);
        matcher.addURI(authority, TiendaFacilContract.PATH_MARCA + "/#", CODE_SINGLE_MARCA);

        matcher.addURI(authority, TiendaFacilContract.PATH_VENTA_MARCA, CODE_ALL_VENTA_MARCA);
        matcher.addURI(authority, TiendaFacilContract.PATH_VENTA_MARCA + "/#", CODE_SINGLE_VENTA_MARCA);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        mOpenHelper = new TiendaFacilDatabase(context);
        return true;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        final int numOperations = operations.size();
        final ContentProviderResult[] results = new ContentProviderResult[numOperations];
        try {
            for(int i = 0; i < numOperations; i++){
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
        }catch (Exception ex){
            Log.d(TAG, "Error ContentPrpviderResult");
            db.endTransaction();
        }
        return results;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final String Id;
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final int match = sUri.match(uri);

        switch (match){
            case CODE_SINGLE_USER:
                Id = user.getUserId(uri);
                queryBuilder.appendWhere(UserColumns._ID + " = " + Id);

            case CODE_ALL_USERS:
                queryBuilder.setTables(Tables.USER);
                break;

            case CODE_SINGLE_ARTICLE:
                Id = article.getArticleId(uri);
                queryBuilder.appendWhere(ArticleColumns._ID + " = " + Id);

            case CODE_ALL_ARTICLES:
                queryBuilder.setTables(Tables.ARTICLE);
                break;

            case CODE_SINGLE_VENTA:
                Id = venta.getVentaId(uri);
                queryBuilder.appendWhere(VentaColumns._ID + "=" + Id);
                break;

            case CODE_ALL_VENTA:
                queryBuilder.setTables(Tables.VENTA);
                break;

            case CODE_SINGLE_MARCA:
                Id = marca.getMarcaId(uri);
                queryBuilder.appendWhere(MarcaColumns._ID + "=" + Id);
                break;

            case CODE_ALL_MARCA:
                queryBuilder.setTables(Tables.MARCA);
                break;

            case CODE_ALL_VENTA_MARCA:
                queryBuilder.setTables("venta inner join marca on venta.venta_marca_id = marca._id");
                break;

            case CODE_SINGLE_VENTA_MARCA:
                Id = venta_marca.getMarcaId(uri);
                queryBuilder.appendWhere(VentaMarcaColumns._ID + "=" + Id);
                break;

            default:
                Log.d(TAG, "Opcion no valida");
        }

        if (sortOrder == null){

            switch (match){
                case CODE_ALL_USERS:
                    sortOrder = user.DEFAULT_SORT;
                    break;

                case CODE_ALL_ARTICLES:
                    sortOrder = article.DEFAULT_SORT;
                    break;

                case CODE_ALL_VENTA:
                    sortOrder = venta.DEFAULT_SORT;
                    break;

                case CODE_ALL_MARCA:
                    sortOrder = marca.DEFAULT_SORT;
                    break;

                case CODE_ALL_VENTA_MARCA:
                    sortOrder = marca.DEFAULT_SORT;
                    break;
            }

        }else{
            Log.d(TAG, "Sin orden registrado");
        }

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d(TAG, "Termina el select");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUri.match(uri);
        switch (match){
            case CODE_ALL_USERS:
                return user.CONTENT_TYPE;

            case CODE_SINGLE_USER:
                return user.CONTENT_ITEM_TYPE;

            case CODE_ALL_ARTICLES:
                return article.CONTENT_TYPE;

            case CODE_SINGLE_ARTICLE:
                return article.CONTENT_ITEM_TYPE;

            case CODE_ALL_VENTA:
                return venta.CONTENT_TYPE;

            case CODE_SINGLE_VENTA:
                return venta.CONTENT_ITEM_TYPE;

            case CODE_ALL_MARCA:
                return marca.CONTENT_TYPE;

            case CODE_SINGLE_MARCA:
                return marca.CONTENT_ITEM_TYPE;

            default:
                Log.d(TAG, "getType no definido");
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri newUri = null;
        long rowId;
        final int match = sUri.match(uri);

        switch (match){
            case CODE_ALL_USERS:
                //TiendaFacilDatabase.dropTables(db);
                rowId = db.insert(Tables.USER, null, values);
                newUri = ContentUris.withAppendedId(user.CONTENT_URI, rowId);
                break;

            case CODE_ALL_ARTICLES:
                rowId = db.insert(Tables.ARTICLE, null, values);
                newUri = ContentUris.withAppendedId(article.CONTENT_URI, rowId);
                break;

            case CODE_ALL_VENTA:
                rowId = db.insert(Tables.VENTA, null, values);
                newUri = ContentUris.withAppendedId(venta.CONTENT_URI, rowId);
                break;

            case CODE_ALL_MARCA:
                rowId = db.insert(Tables.MARCA, null, values);
                newUri = ContentUris.withAppendedId(marca.CONTENT_URI, rowId);
                break;

            default:
                Log.d(TAG, "Tabla no existe");
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        int deleteRows = 0;
        final String id;

        switch (match){
            case CODE_SINGLE_USER:
                id = user.getUserId(uri);
                deleteRows = db.delete(Tables.USER, UserColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_USERS:
                deleteRows = db.delete(Tables.USER, selection, selectionArgs);
                break;

            case CODE_SINGLE_ARTICLE:
                id = article.getArticleId(uri);
                deleteRows = db.delete(Tables.ARTICLE, ArticleColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_ARTICLES:
                deleteRows = db.delete(Tables.ARTICLE, selection, selectionArgs);
                break;

            case CODE_SINGLE_VENTA:
                id = venta.getVentaId(uri);
                deleteRows = db.delete(Tables.VENTA, VentaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_VENTA:
                deleteRows = db.delete(Tables.VENTA, selection, selectionArgs);
                break;

            case CODE_SINGLE_MARCA:
                id = marca.getMarcaId(uri);
                deleteRows = db.delete(Tables.MARCA, MarcaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_MARCA:
                deleteRows = db.delete(Tables.MARCA, selection, selectionArgs);
                break;

            default:
                Log.d(TAG, "No existe tabla para borrar");
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUri.match(uri);
        final String id;
        int updateRows = 0;

        switch (match){
            case CODE_SINGLE_USER:
                id = user.getUserId(uri);
                updateRows = db.update(Tables.USER, values, UserColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_USERS:
                updateRows = db.update(Tables.USER, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_ARTICLE:
                id = article.getArticleId(uri);
                updateRows = db.update(Tables.ARTICLE, values, ArticleColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_ARTICLES:
                updateRows = db.update(Tables.ARTICLE, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_VENTA:
                id = venta.getVentaId(uri);
                updateRows = db.update(Tables.VENTA, values, VentaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_VENTA:
                updateRows = db.update(Tables.VENTA, values, selection, selectionArgs);
                break;

            case CODE_SINGLE_MARCA:
                id = marca.getMarcaId(uri);
                updateRows = db.update(Tables.MARCA, values, MarcaColumns._ID + "=?", new String[]{id});
                break;

            case CODE_ALL_MARCA:
                updateRows = db.update(Tables.MARCA, values, selection, selectionArgs);
                break;

            default:
                Log.d(TAG, "update no registrado");
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return updateRows;
    }
}
