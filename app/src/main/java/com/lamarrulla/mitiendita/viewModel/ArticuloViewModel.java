package com.lamarrulla.mitiendita.viewModel;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.lamarrulla.mitiendita.data.model.Articulo;
import com.lamarrulla.mitiendita.data.repository.ArticuloRepository;

import java.util.List;

public class ArticuloViewModel extends AndroidViewModel {
    private ArticuloRepository repository;
    private LiveData<List<Articulo>> allArticulos;

    public ArticuloViewModel(@NonNull Application application) {
        super(application);
        repository = new ArticuloRepository(application);
        allArticulos = repository.getAllArticulos();
    }

    public void insert(Articulo articulo){
        repository.insert(articulo);
    }
    public void update(Articulo articulo){
        repository.update(articulo);
    }
    public void delete(Articulo articulo){
        repository.delete(articulo);
    }
    public void deleteAllArticulos(){
        repository.deleteAllArticulos();
    }
    public LiveData<List<Articulo>> getAllArticulos(){
        return allArticulos;
    }
}
