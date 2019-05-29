package com.lamarrulla.mitiendita.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.lamarrulla.mitiendita.data.dao.ArticuloDao;
import com.lamarrulla.mitiendita.data.database.ArticuloDatabase;
import com.lamarrulla.mitiendita.data.model.Articulo;

import java.util.List;

public class ArticuloRepository {
    private ArticuloDao articuloDao;
    private LiveData<List<Articulo>> allArticulos;
    public ArticuloRepository(Application application){
        ArticuloDatabase database = ArticuloDatabase.getInstance(application);
        articuloDao = database.articuloDao();
        allArticulos = articuloDao.getAllArticulos();
    }
    public void insert(Articulo articulo){
        new InsertArticuloAsyncTask(articuloDao).execute(articulo);
    }
    public void update(Articulo articulo){
        new UpdateArticuloAsyncTask(articuloDao).execute(articulo);
    }
    public void delete(Articulo articulo){
        new DeleteArticuloAsyncTask(articuloDao).execute(articulo);
    }
    public void deleteAllArticulos(){
        new DeleteAllArticuloAsyncTask(articuloDao).execute();
    }
    public LiveData<List<Articulo>> getAllArticulos(){
        return allArticulos;
    }
    private static class InsertArticuloAsyncTask extends AsyncTask<Articulo, Void, Void>{
        private ArticuloDao articuloDao;
        private InsertArticuloAsyncTask(ArticuloDao articuloDao){
            this.articuloDao = articuloDao;
        }
        @Override
        protected Void doInBackground(Articulo... articulos) {
            articuloDao.insert(articulos[0]);
            return null;
        }
    }
    private static class UpdateArticuloAsyncTask extends AsyncTask<Articulo, Void, Void>{
        private ArticuloDao articuloDao;
        private UpdateArticuloAsyncTask(ArticuloDao articuloDao){
            this.articuloDao = articuloDao;
        }
        @Override
        protected Void doInBackground(Articulo... articulos) {
            articuloDao.update(articulos[0]);
            return null;
        }
    }
    private static class DeleteArticuloAsyncTask extends AsyncTask<Articulo, Void, Void>{
        private ArticuloDao articuloDao;
        private DeleteArticuloAsyncTask(ArticuloDao articuloDao){
            this.articuloDao = articuloDao;
        }
        @Override
        protected Void doInBackground(Articulo... articulos) {
            articuloDao.delete(articulos[0]);
            return null;
        }
    }
    private static class DeleteAllArticuloAsyncTask extends AsyncTask<Void, Void, Void>{
        private ArticuloDao articuloDao;
        private DeleteAllArticuloAsyncTask(ArticuloDao articuloDao){
            this.articuloDao = articuloDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            articuloDao.deleteAllArticulos();
            return null;
        }
    }
}
