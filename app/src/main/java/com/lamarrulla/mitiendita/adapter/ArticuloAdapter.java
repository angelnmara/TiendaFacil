package com.lamarrulla.mitiendita.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lamarrulla.mitiendita.R;
import com.lamarrulla.mitiendita.data.model.Articulo;

import java.util.ArrayList;
import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloHolder> {

    private List<Articulo> articulos = new ArrayList<>();

    @NonNull
    @Override
    public ArticuloHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.articulo_item, viewGroup, false);
        return new ArticuloHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloHolder articuloHolder, int i) {
        Articulo currentArticulo = articulos.get(i);
        articuloHolder.txtDescripcion.setText(currentArticulo.getArticuloDescripcion());
        articuloHolder.txtNombre.setText(currentArticulo.getArticuloNombre());
        articuloHolder.txtCantidad.setText(String.valueOf(currentArticulo.getArticuloCantidad()));
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public void setArticulos(List<Articulo> articulos){
        this.articulos = articulos;
        notifyDataSetChanged();
    }

    class ArticuloHolder extends RecyclerView.ViewHolder{
        private TextView txtCantidad;
        private TextView txtNombre;
        private TextView txtDescripcion;


        public ArticuloHolder(@NonNull View itemView) {
            super(itemView);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}
