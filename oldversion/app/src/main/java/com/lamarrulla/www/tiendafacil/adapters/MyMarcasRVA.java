package com.lamarrulla.www.tiendafacil.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.ViewHolder.VHMarca;
import com.lamarrulla.www.tiendafacil.listas.itemListMarca;

import java.util.List;

/**
 * Created by Qualtop on 19/10/2016.
 */

public class MyMarcasRVA extends RecyclerView.Adapter<VHMarca> {

    private final List<itemListMarca> mValues;
    private final OnListFragmentMarca mListener;
    private final String TAG = "MyMarcasRVA";

    public MyMarcasRVA(List<itemListMarca> items, OnListFragmentMarca listener){
        mValues = items;
        mListener = listener;
    }

    @Override
    public VHMarca onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_marca, parent, false);
        VHMarca vhm = new VHMarca(v);
        return vhm;
    }

    @Override
    public void onBindViewHolder(final VHMarca holder, final int position) {
        try{
            holder.mItem = mValues.get(position);
            holder.txtMarcaId.setText(mValues.get(position).getMarca_id() + "");
            holder.txtMarca.setText(mValues.get(position).getMarca_name());
            holder.txtCodigoMarca.setText(mValues.get(position).getMarca_code());

            byte[] btm = mValues.get(position).getMarca_imagen();
            if (btm != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(btm, 0, btm.length);
                holder.imgMarca.setImageBitmap(bitmap);
            }

            holder.imgEdita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListEdit(mValues.get(position).getMarca_id());
                }
            });

            holder.imgElimina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onListElimina(mValues.get(position).getMarca_id());
                }
            });

        }catch (Exception ex){
            Log.d(TAG, "Error al agregar a la lista");
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnListFragmentMarca{
        void onListEdit(int id);
        void onListElimina(int id);
    }

}
