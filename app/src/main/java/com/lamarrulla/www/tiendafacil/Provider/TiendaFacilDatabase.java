package com.lamarrulla.www.tiendafacil.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.ArticleColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.UserColumns;

/**
 * Created by Qualtop on 02/09/2016.
 */
public class TiendaFacilDatabase extends SQLiteOpenHelper {

    public static final String TAG = "TiendaFacilDatabase";

    public static final String DATABASE_NAME = "TiendaFacilDB";

    private static final int DATABASE_VERSION = 7;

    static SQLiteDatabase dbUse;

    public TiendaFacilDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Crea base de datos");
        db.beginTransaction();
        try {
            createTables(db);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Log Entra a atualizar", " vieja" + oldVersion + " nueva" + newVersion);
        db.beginTransaction();
        try {
            dropTables(db);
            createTables(db);
            db.setTransactionSuccessful();
            Log.i("Log Termina a atualizar", " vieja" + oldVersion + " nueva" + newVersion);
        } finally {
            db.endTransaction();
        }
    }

    public static void dropTables(SQLiteDatabase db) {
        try {

            Log.d(TAG, "Elimina");

            db.beginTransaction();
            //db.delete(Tables.USER, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + Tables.USER);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + Tables.ARTICLE);
            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (Exception ex){
            Log.d(TAG, ex.getMessage());
        }
    }

    private static void createTables(SQLiteDatabase db){

        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("CREATE TABLE IF NOT EXISTS ").append(Tables.USER)
                .append("(").append(UserColumns._ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(UserColumns.MEMBER_NUMBER).append(" INTEGER,")
                .append(UserColumns.NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.AGE).append(" INTEGER,")
                .append(UserColumns.USER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.GENDER_ID).append(" INTEGER,")
                .append(UserColumns.GENDER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.HEIGHT).append(" REAL,")
                .append(UserColumns.WEIGHT).append(" REAL,")
                .append(UserColumns.REGISTRATION_DATE).append(" INTEGER,")
                .append(UserColumns.BIRTH_DATE).append(" INTEGER,")
                .append(UserColumns.MEMBER_TYPE)
                .append(" TEXT COLLATE NOCASE,")
                .append(" TEXT COLLATE NOCASE,").append(UserColumns.EMAIL)
                .append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_ID).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_FIRST_NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_LAST_NAME).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_BIRTHDAY).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_EMAIL).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_GENDER).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.USER_FB_LOCALE).append(" TEXT COLLATE NOCASE,")
                .append(UserColumns.LATITUD).append(" REAL,")
                .append(UserColumns.LONGITUD).append(" REAL)");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        strBuilder.append("CREATE TABLE IF NOT EXISTS ")
                .append(Tables.ARTICLE).append("(")
                .append(ArticleColumns.ARTICLE_ID)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(ArticleColumns.ARTICLE_CODE)
                .append(" TEXT COLLATE NOCASE,")
                .append(ArticleColumns.ARTICLE_NAME)
                .append(" TEXT COLLATE NOCASE,")
                .append(ArticleColumns.ARTICLE_DESC)
                .append(" TEXT COLLATE NOCASE,")
                .append(ArticleColumns.ARTICLE_PRECIO)
                .append(" INTEGER,")
                .append(ArticleColumns.ARTICLE_COSTO)
                .append(" INTEGER,")
                .append(ArticleColumns.ARTICLE_FOTO)
                .append(" BLOB,")
                .append(ArticleColumns.ARTICLE_STOCK)
                .append(" TEXT COLLATE NOCASE)");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);
    }

    interface Tables{
        static final String USER = "user";
        static final String ARTICLE = "article";
    }
}
