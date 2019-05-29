package com.lamarrulla.www.tiendafacil.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.listas.itemListVenta;

import java.util.List;

/**
 * Created by root on 11/10/16.
 */

public class MyTiendaRVA extends RecyclerView.Adapter<MyTiendaRVA.ViewHolder> {

    private final List<itemListVenta> mItem;
    private final OnListTiendaAdapter mListener;

    public MyTiendaRVA(List<itemListVenta> items, OnListTiendaAdapter listener){
        mItem = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_tienda, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mItem.get(position);
        holder.txtProducto.setText(mItem.get(position).getVenta_name());
        holder.txtDescripcion.setText(mItem.get(position).getVenta_desc());
        holder.txtPrecio.setText(mItem.get(position).getVenta_precio().toString());
        holder.txtMarca.setText(mItem.get(position).getVenta_marca());
        byte[] btm = mItem.get(position).getVenta_foto();
        if(btm!= null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(btm, 0, btm.length);
            holder.imgIcon.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtId;
        public final TextView txtProducto;
        public final TextView txtDescripcion;
        public final TextView txtMarca;
        public final TextView txtPrecio;
        public ImageView imgIcon;
        public itemListVenta mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtProducto = (TextView) itemView.findViewById(R.id.txtProducto);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            txtMarca = (TextView) itemView.findViewById(R.id.txtMarca);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
        }
    }

    public interface OnListTiendaAdapter{
        void OnListTienda(String id);
    }

}
