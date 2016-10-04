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
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.article;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.user;
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

            default:
                Log.d(TAG, "update no registrado");
        }

        getContext().getContentResolver().notifyChange(uri, null, false);

        return updateRows;
    }
}
