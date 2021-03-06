package com.lamarrulla.www.tiendafacil.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.ArticleColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.UserColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.TipoDato;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.VentaColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.MarcaColumns;

/**
 * Created by Qualtop on 02/09/2016.
 */
public class TiendaFacilDatabase extends SQLiteOpenHelper {

    public static final String TAG = "TiendaFacilDatabase";

    public static final String DATABASE_NAME = "TiendaFacilDB";

    private static final int DATABASE_VERSION = 19;

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

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + Tables.VENTA);
            db.setTransactionSuccessful();
            db.endTransaction();

            db.beginTransaction();
            //db.delete(Tables.ARTICLE, null, null);
            db.execSQL("DROP TABLE IF EXISTS " + Tables.MARCA);
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
                .append(ArticleColumns._ID)
                .append(TipoDato.INT_KEY)
                .append(ArticleColumns.ARTICLE_CODE)
                .append(TipoDato.TEXT_)
                .append(ArticleColumns.ARTICLE_NAME)
                .append(TipoDato.TEXT_)
                .append(ArticleColumns.ARTICLE_DESC)
                .append(TipoDato.TEXT_)
                .append(ArticleColumns.ARTICLE_PRECIO)
                .append(TipoDato.DOUBLE_)
                .append(ArticleColumns.ARTICLE_COSTO)
                .append(TipoDato.DOUBLE_)
                .append(ArticleColumns.ARTICLE_FOTO)
                .append(TipoDato.BLOB_)
                .append(ArticleColumns.ARTICLE_STOCK)
                .append(TipoDato.TEXT_)
                .append(ArticleColumns.ARTICLE_MARCA_ID)
                .append(TipoDato.INT)
                .append(" NOT NULL, FOREIGN KEY(article_marca_id) REFERENCES marca(_id)")
                .append(")");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        strBuilder.append(TipoDato.CREATE_TABLE)
                .append(Tables.MARCA)
                .append("(")
                .append(MarcaColumns._ID)
                .append(TipoDato.INT_KEY)
                .append(MarcaColumns.MARCA_CODE)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_IMAGEN)
                .append(TipoDato.BLOB_)
                .append(MarcaColumns.MARCA_NAME)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_OTRO1)
                .append(TipoDato.TEXT_)
                .append(MarcaColumns.MARCA_OTRO2)
                .append(TipoDato.TEXT)
                .append(")");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        strBuilder.append(TipoDato.CREATE_TABLE)
                .append(Tables.VENTA)
                .append("(")
                .append(VentaColumns._ID)
                .append(TipoDato.INT_KEY)
                .append(VentaColumns.VENTA_CODE)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_NAME)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_DESC)
                .append(TipoDato.TEXT_)
                .append(VentaColumns.VENTA_MARCA_ID)
                .append(TipoDato.INT_)
                .append(VentaColumns.VENTA_PRECIO)
                .append(TipoDato.DOUBLE_)
                .append(VentaColumns.VENTA_FOTO)
                .append(TipoDato.BLOB)
                .append(")");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

        strBuilder.append("PRAGMA foreign_keys = ON;");

        db.execSQL(strBuilder.toString());
        strBuilder.setLength(0);

    }

    interface Tables{
        static final String USER = "user";
        static final String ARTICLE = "article";
        static final String VENTA = "venta";
        static final String MARCA = "marca";
        static final String VENTA_MARCA = "venta_marca";
    }
}
