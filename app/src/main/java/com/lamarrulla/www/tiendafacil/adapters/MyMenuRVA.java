package com.lamarrulla.www.tiendafacil.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.listas.itemListMenu;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link itemListMenu} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMenuRVA extends RecyclerView.Adapter<MyMenuRVA.ViewHolder> {

    private final List<itemListMenu> mValues;
    private final OnListFragmentMenu mListener;

    public MyMenuRVA(List<itemListMenu> items, OnListFragmentMenu listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        switch (mValues.get(position).id){
            case "0":
                holder.imgIcon.setImageResource(R.drawable.ic_inicio);
                break;
            case "1":
                holder.imgIcon.setImageResource(R.drawable.ic_localizar_equipo);
                break;
            case "2":
                holder.imgIcon.setImageResource(R.drawable.ic_sincronizar);
                break;
            case "3":
                holder.imgIcon.setImageResource(R.drawable.ic_cerrarsesion);
                break;
            default:
                break;
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentM(mValues.get(position).id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public itemListMenu mItem;
        public ImageView imgIcon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface OnListFragmentMenu{
        void onListFragmentM(String id);
    }

}
