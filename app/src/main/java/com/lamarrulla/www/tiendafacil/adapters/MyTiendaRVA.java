package com.lamarrulla.www.tiendafacil.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.listas.itemListArticle;

import java.util.List;

/**
 * Created by root on 11/10/16.
 */

public class MyTiendaRVA extends RecyclerView.Adapter<MyTiendaRVA.ViewHolder> {

    private final List<itemListArticle> mItem;
    private final OnListTiendaAdapter mListener;

    public MyTiendaRVA(List<itemListArticle> items, OnListTiendaAdapter listener){
        mItem = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_tienda, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mItem.get(position);
        holder.mIdView.setText(mItem.get(position).getArticle_name());
        holder.mContentView.setText(mItem.get(position).getArticle_desc());
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public itemListArticle mItem;
        public ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mIdView = (TextView) itemView.findViewById(R.id.id);
            mContentView = (TextView) itemView.findViewById(R.id.content);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }

    public interface OnListTiendaAdapter{
        void OnListTienda(String id);
    }

}
