package com.lamarrulla.mitiendita;

import android.app.ActivityManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lamarrulla.mitiendita.adapter.ArticuloAdapter;
import com.lamarrulla.mitiendita.data.model.Articulo;
import com.lamarrulla.mitiendita.viewModel.ArticuloViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_ARTICULO_REQUEST = 1;
    private ArticuloViewModel articuloViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAddArticulo = findViewById(R.id.btnAddArticulo);
        btnAddArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddArticuloActivity.class);
                startActivityForResult(intent, ADD_ARTICULO_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewArticulo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ArticuloAdapter adapter = new ArticuloAdapter();
        recyclerView.setAdapter(adapter);

        articuloViewModel = ViewModelProviders.of(this).get(ArticuloViewModel.class);
        articuloViewModel.getAllArticulos().observe(this, new Observer<List<Articulo>>() {
            @Override
            public void onChanged(@Nullable List<Articulo> articulos) {
                //Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_LONG).show();
                adapter.setArticulos(articulos);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_ARTICULO_REQUEST && resultCode == RESULT_OK){
            String nombre = data.getStringExtra(AddArticuloActivity.EXTRA_NOMBRE);
            String descripcion = data.getStringExtra(AddArticuloActivity.EXTRA_DESCRIPCION);
            int cantidad = data.getIntExtra(AddArticuloActivity.EXTRA_CANTIDAD, 1);
            Articulo articulo = new Articulo(nombre, descripcion, cantidad);
            articuloViewModel.insert(articulo);
            Toast.makeText(this, "Articulo guardado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Articulo no guardado", Toast.LENGTH_SHORT).show();
        }
    }
}
