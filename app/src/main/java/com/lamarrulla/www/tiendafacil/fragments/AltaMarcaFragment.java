package com.lamarrulla.www.tiendafacil.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.R;
/*import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;*/
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.marca;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AltaMarcaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AltaMarcaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaMarcaFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView btn_accept;
    private EditText txtMarca;
    private EditText txtCodigo;

    //private OnFragmentInteractionListener mListener;

    public AltaMarcaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AltaMarcaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AltaMarcaFragment newInstance(String param1, String param2) {
        AltaMarcaFragment fragment = new AltaMarcaFragment();
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

        View v = inflater.inflate(R.layout.fragment_alta_marca, container, false);
        btn_accept = (CardView) v.findViewById(R.id.btn_accept);
        txtMarca = (EditText) v.findViewById(R.id.txtMarca);
        txtCodigo = (EditText) v.findViewById(R.id.txtCodigo);

        btn_accept.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_accept:
                //Toast.makeText(getContext(), "Alta Marca", Toast.LENGTH_SHORT).show();
                saveMarca();
                break;
        }
    }

    private void saveMarca() {
        final ContentResolver resolver = getContext().getContentResolver();
        final ContentValues values = new ContentValues();

        values.put(marca.MARCA_NAME, txtMarca.getText().toString());
        values.put(marca.MARCA_CODE, txtCodigo.getText().toString());

        final Uri newUri = resolver.insert(marca.CONTENT_URI, values);

        Toast.makeText(getContext(), "Id de la marca insertada" + newUri, Toast.LENGTH_SHORT).show();

    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
