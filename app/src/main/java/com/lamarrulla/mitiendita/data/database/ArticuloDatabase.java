package com.lamarrulla.mitiendita.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.lamarrulla.mitiendita.data.dao.ArticuloDao;
import com.lamarrulla.mitiendita.data.model.Articulo;

@Database(entities = {Articulo.class}, version = 1, exportSchema = false)
public abstract class ArticuloDatabase extends RoomDatabase {
    private static ArticuloDatabase instance;
    public abstract ArticuloDao articuloDao();
    public static synchronized ArticuloDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ArticuloDatabase.class, "dbArticulo")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private ArticuloDao articuloDao;

        private PopulateDbAsyncTask(ArticuloDatabase db){
            articuloDao = db.articuloDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            articuloDao.insert(new Articulo("Articulo1", "Desc1", 1));
            articuloDao.insert(new Articulo("Articulo2", "Desc2", 2));
            articuloDao.insert(new Articulo("Articulo3", "Desc3", 3));
            return null;
        }
    }
}
