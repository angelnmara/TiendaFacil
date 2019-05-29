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
/*import com.lamarrulla.www.tiendafacil.fragments.ArticulosListFragment.OnListFragmentInteractionListener;*/
//import com.lamarrulla.www.tiendafacil.contents.genericContentJSON.DummyItem;
import com.lamarrulla.www.tiendafacil.listas.itemListArticle;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyArticulosRVA extends RecyclerView.Adapter<MyArticulosRVA.ViewHolder> {

    private final List<itemListArticle> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyArticulosRVA(List<itemListArticle> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_articulos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getArticle_id().toString());
        holder.mContentView.setText(mValues.get(position).getArticle_desc());
        holder.txtMarcaId.setText(String.valueOf(mValues.get(position).getArticle_marca_id()));
        byte[] btm = mValues.get(position).getArticle_foto();
        if (btm != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(btm, 0, btm.length);
            holder.ImgArticulos.setImageBitmap(bitmap);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mValues.get(position).getArticle_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(int id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView ImgArticulos;
        public final TextView txtMarcaId;
        //public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            ImgArticulos = (ImageView) view.findViewById(R.id.ImgArticulos);
            txtMarcaId = (TextView) view.findViewById(R.id.txtMarcaId);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
