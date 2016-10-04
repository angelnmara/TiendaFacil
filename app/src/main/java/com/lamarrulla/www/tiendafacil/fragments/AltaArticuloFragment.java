package com.lamarrulla.www.tiendafacil.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.MainActivity;
import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.dialogs.GenericDilog;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilDatabase;
import com.lamarrulla.www.tiendafacil.utils.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AltaArticuloFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AltaArticuloFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaArticuloFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static final int REQUEST_IMAGE_CAPTURE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Objetos

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtDescripcion;
    private EditText txtPrecio;
    private EditText txtCosto;
    private EditText txtUnidades;
    private ImageView ImgProducto;
    private CardView btn_accept;

    //private OnFragmentInteractionListener mListener;

    public AltaArticuloFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AltaArticuloFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaArticuloFragment newInstance(String param1, String param2) {
        AltaArticuloFragment fragment = new AltaArticuloFragment();
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

        View v = inflater.inflate(R.layout.fragment_alta_articulo, container, false);

        txtCodigo = (EditText) v.findViewById(R.id.txtCodigo);
        txtNombre = (EditText) v.findViewById(R.id.txtNombre);
        txtDescripcion = (EditText) v.findViewById(R.id.txtDescripcion);
        txtPrecio = (EditText) v.findViewById(R.id.txtPrecio);
        txtCosto = (EditText) v.findViewById(R.id.txtCosto);
        txtUnidades = (EditText) v.findViewById(R.id.txtUnidades);

        ImgProducto = (ImageView) v.findViewById(R.id.ImgProducto);

        btn_accept = (CardView) v.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);

        txtCodigo.setText(mParam1);

        MainActivity.fab.setImageResource(R.drawable.ic_menu_camera);
        ImgProducto.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ImgProducto:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(getContext().getPackageManager()) != null){
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            break;
            case R.id.btn_accept:
                saveArticle();
                break;
            default:
                Toast.makeText(getContext(), "Opcion invalida", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void saveArticle() {
        final ContentResolver resolver = getContext().getContentResolver();
        final ContentValues values = new ContentValues();

        values.put(TiendaFacilContract.article.ARTICLE_DESC, txtDescripcion.getText().toString());
        values.put(TiendaFacilContract.article.ARTICLE_NAME, txtNombre.getText().toString());
        values.put(TiendaFacilContract.article.ARTICLE_PRECIO, txtPrecio.getText().toString());
        values.put(TiendaFacilContract.article.ARTICLE_COSTO, txtCosto.getText().toString());
        values.put(TiendaFacilContract.article.ARTICLE_STOCK, txtUnidades.getText().toString());

        final Uri newUri = resolver.insert(TiendaFacilContract.article.CONTENT_URI, values);

        Intent i = new Intent(getContext(), GenericDilog.class);
        i.putExtra("message", TiendaFacilContract.article.getArticleId(newUri).toString());
        startActivity(i);
        return;

    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
