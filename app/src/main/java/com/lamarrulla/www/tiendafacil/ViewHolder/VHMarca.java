package com.lamarrulla.www.tiendafacil.ViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.listas.itemListMarca;

/**
 * Created by Qualtop on 19/10/2016.
 */

public class VHMarca extends RecyclerView.ViewHolder {

    public itemListMarca mItem;
    public final View mView;
    public final TextView txtMarcaId;
    public final TextView txtMarca;
    public final TextView txtCodigoMarca;
    public final ImageView imgMarca;
    public final ImageView imgEdita;
    public final ImageView imgElimina;

    public VHMarca(View itemView) {
        super(itemView);
        mView = itemView;
        txtMarcaId = (TextView) itemView.findViewById(R.id.txtMarcaId);
        txtMarca = (TextView) itemView.findViewById(R.id.txtMarca);
        txtCodigoMarca = (TextView) itemView.findViewById(R.id.txtCodigoMarca);
        imgMarca = (ImageView) itemView.findViewById(R.id.imgMarca);
        imgEdita = (ImageView) itemView.findViewById(R.id.imgEdita);
        imgElimina = (ImageView) itemView.findViewById(R.id.imgElimina);
    }
}
