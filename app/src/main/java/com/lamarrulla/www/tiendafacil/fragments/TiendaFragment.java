package com.lamarrulla.www.tiendafacil.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.adapters.MyTiendaRVA;
import com.lamarrulla.www.tiendafacil.listas.itemListVenta;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.venta;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.venta_marca;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TiendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TiendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendaFragment extends Fragment implements View.OnClickListener, MyTiendaRVA.OnListTiendaAdapter {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TiendaFragment";

    public static ArrayList Item;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CompoundBarcodeView bcv;
    private CardView btn_accept;
    private CardView btn_Limpiar;
    private RecyclerView list;
    private BeepManager mBeepManager;
    private TextView txtTotal;
    private TextView txtNoArticulos;
    private double total;
    private int noArticulos;

    final Handler mHandler = new Handler();

    Camera camera;

    //private OnFragmentInteractionListener mListener;

    public TiendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TiendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TiendaFragment newInstance(String param1, String param2) {
        TiendaFragment fragment = new TiendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tienda, container, false);
        bcv = (CompoundBarcodeView) v.findViewById(R.id.bcv);
        bcv.setStatusText("Escanea un codigo en el recuadro");
        bcv.decodeSingle(callback);
        mBeepManager = new BeepManager(getActivity());
        btn_accept = (CardView) v.findViewById(R.id.btn_accept);
        btn_Limpiar = (CardView) v.findViewById(R.id.btn_Limpiar);
        btn_accept.setOnClickListener(this);
        btn_Limpiar.setOnClickListener(this);
        list = (RecyclerView) v.findViewById(R.id.list);
        txtNoArticulos = (TextView) v.findViewById(R.id.txtNoArticulos);
        txtTotal = (TextView) v.findViewById(R.id.txtTotal);

        return v;
    }

    @Override
    public void onResume() {
        bcv.resume();
        llenalista();
        llenaRVA();
        super.onResume();
    }

    @Override
    public void onPause() {
        bcv.pause();
        super.onPause();
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if(result.getText() != null){
                bcv.setStatusText("");
                mBeepManager.playBeepSoundAndVibrate();

                String[] projection = new String[] { "article_id", "article_code", "article_name", "article_desc", "article_precio", "article_costo", "article_foto", "article_stock, article_marca_id" };
                String selection = "article_code = ?";
                String[] selectionArgs = new String[] {result.toString()};
                Cursor articulosCursor = getContext().getContentResolver().query(TiendaFacilContract.article.CONTENT_URI, projection, selection, selectionArgs, null);

                /*if(Item==null){
                    Item = new ArrayList();
                }*/

                if(articulosCursor.getCount()>0){
                    saveVenta(articulosCursor);
                    llenalista();
                    llenaRVA();
                }else{
                    Toast.makeText(getContext(), "articulo no encontrado", Toast.LENGTH_LONG).show();
                }
            }

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bcv.setStatusText("Escanea un codigo en el recuadro");
                    bcv.decodeSingle(callback);
                }
            }, 1000);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    private void saveVenta(Cursor articulosCursor) {
        try{
            final ContentResolver resolver = getContext().getContentResolver();
            final ContentValues values = new ContentValues();
            if(articulosCursor.moveToFirst()){
                do{
                    values.put(venta.VENTA_CODE, articulosCursor.getString(articulosCursor.getColumnIndex("article_code")));
                    values.put(venta.VENTA_PRECIO, articulosCursor.getDouble(articulosCursor.getColumnIndex("article_precio")));
                    values.put(venta.VENTA_DESC, articulosCursor.getString(articulosCursor.getColumnIndex("article_desc")));
                    values.put(venta.VENTA_FOTO, articulosCursor.getBlob(articulosCursor.getColumnIndex("article_foto")));
                    values.put(venta.VENTA_NAME, articulosCursor.getString(articulosCursor.getColumnIndex("article_name")));
                    values.put(venta.VENTA_MARCA_ID, articulosCursor.getInt(articulosCursor.getColumnIndex("article_marca_id")));
                }while (articulosCursor.moveToNext());
                resolver.insert(venta.CONTENT_URI, values);
            }
        }catch (Exception ex){
            Toast.makeText(getContext(), getResources().getString(R.string.msjArticuloNoInsertado), Toast.LENGTH_SHORT).show();
        }

    }

    private void llenaRVA() {
        if(Item!=null){
            list.setLayoutManager(new LinearLayoutManager(getContext()));
            list.setAdapter(new MyTiendaRVA(Item, TiendaFragment.this));
            list.setItemAnimator(new DefaultItemAnimator());
            txtTotal.setText(getResources().getString(R.string.total_) + " " + total);
            txtNoArticulos.setText(getResources().getString(R.string.no_articulos_) + " " + noArticulos);
        }
    }

    private void llenalista() {
        String[] projectionVenta = new String[] { "venta_name", "venta_desc", "venta_precio", "venta_foto, venta_marca_id"};
        Cursor ventaCursor = getContext().getContentResolver().query(venta_marca.CONTENT_URI, projectionVenta, null, null, null);
        if(ventaCursor.getCount()>0){
            Item = new ArrayList();
            total = 0;
            if(ventaCursor.moveToFirst()){
                do{
                    Item.add(new itemListVenta(
                            ventaCursor.getString(ventaCursor.getColumnIndex("venta_name")),
                            ventaCursor.getString(ventaCursor.getColumnIndex("venta_desc")),
                            ventaCursor.getDouble(ventaCursor.getColumnIndex("venta_precio")),
                            "Marca",
                            //ventaCursor.getString(ventaCursor.getColumnIndex("venta_marca")),
                            ventaCursor.getBlob(ventaCursor.getColumnIndex("venta_foto"))));
                    total += ventaCursor.getDouble(ventaCursor.getColumnIndex("venta_precio"));
                }while (ventaCursor.moveToNext());
            }
            noArticulos = ventaCursor.getCount();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_accept:
                bcv.setStatusText("Escanea un codigo en el recuadro");
                bcv.decodeSingle(callback);
                break;
            case R.id.btn_Limpiar:
                final ContentResolver resolver = getContext().getContentResolver();
                resolver.delete(venta.CONTENT_URI,null, null);
                txtNoArticulos.setText(getResources().getString(R.string.no_articulos_));
                txtTotal.setText(getResources().getString(R.string.total_));
                Item = new ArrayList();
                llenaRVA();
                break;
            default:
                Toast.makeText(getContext(), getResources().getString(R.string.MsjOpcionInvalida), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void OnListTienda(String id) {

    }
}
