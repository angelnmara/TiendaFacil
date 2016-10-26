package com.lamarrulla.www.tiendafacil.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.R;
/*import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;*/
import com.lamarrulla.www.tiendafacil.adapters.MyMarcasRVA;
import com.lamarrulla.www.tiendafacil.contents.genericContentCursor;
import com.lamarrulla.www.tiendafacil.dialogs.GenericDilog;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.marca;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.MarcaColumns;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AltaMarcaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AltaMarcaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AltaMarcaFragment extends Fragment implements View.OnClickListener, MyMarcasRVA.OnListFragmentMarca, MarcaColumns {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AltaMarcaFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView btn_accept;
    private EditText txtMarca;
    private TextInputLayout textinputMarca;
    private TextView txtBtnAcept;

    private EditText txtCodigo;
    private TextInputLayout textinputCodigo;

    private RecyclerView list;
    private RecyclerView.Adapter AdapterMarca;
    private RecyclerView.LayoutManager mLayoutManager;

    boolean edit = false;
    String MarcaEdit = "";
    int MarcaEditId = 0;

    View focusView = null;

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
        textinputMarca = (TextInputLayout) v.findViewById(R.id.textinputMarca);

        txtCodigo = (EditText) v.findViewById(R.id.txtCodigo);
        textinputCodigo = (TextInputLayout) v.findViewById(R.id.textinputCodigo);

        txtBtnAcept = (TextView) v.findViewById(R.id.txtBtnAcept);

        list = (RecyclerView) v.findViewById(R.id.list);

        cargaTablaMarca();

        btn_accept.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    private void cargaTablaMarca() {
        list.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(mLayoutManager);
        String[] projection = new String[] { MarcaColumns._ID, MarcaColumns.MARCA_CODE, MarcaColumns.MARCA_NAME, MarcaColumns.MARCA_IMAGEN };
        Cursor MarcaCursor =  getContext().getContentResolver().query(TiendaFacilContract.marca.CONTENT_URI, projection, null, null, null);
        genericContentCursor.getData(MarcaCursor, "itemListMarca");
        AdapterMarca = new MyMarcasRVA(genericContentCursor.Item, AltaMarcaFragment.this);
        list.setAdapter(AdapterMarca);
        txtCodigo.setText("");
        txtMarca.setText("");
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

        if (txtMarca.length()==0){
            textinputMarca.setError(getString(R.string.msjMarcaReq));
            requestFocus(txtMarca);
            return;
        }

        /*if(txtCodigo.length()==0){
            textinputCodigo.setError(getString(R.string.msjCodigoReq));
            requestFocus(txtCodigo);
            return;
        }*/

        if(edit == false){

            /*inserta*/

            values.put(MarcaColumns.MARCA_NAME, txtMarca.getText().toString());
            values.put(MarcaColumns.MARCA_CODE, txtCodigo.getText().toString());
            final Uri newUri = resolver.insert(marca.CONTENT_URI, values);
            Intent i = new Intent(getContext(), GenericDilog.class);
            String marca = TiendaFacilContract.article.getArticleId(newUri).toString();
            i.putExtra("message", "El registro se inserto correctamente con el id: " + marca);
            startActivity(i);

            cargaTablaMarca();

        }else{
            ConfirmEditMarca();
        }

        requestFocus(txtMarca);

    }

    private void ConfirmEditMarca() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Edita Marca");
        builder.setMessage("Desea editar la marca?");
        builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditMarca(MarcaEditId);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    /*@Override
    public void onListFragmentMar(int id) {

    }*/

    private void EditMarca(int id){
        final ContentResolver resolver = getContext().getContentResolver();
        final ContentValues values = new ContentValues();

        values.put(MarcaColumns.MARCA_NAME, txtMarca.getText().toString());
        values.put(MarcaColumns.MARCA_CODE, txtCodigo.getText().toString());

        String selection = "_id =?";
        String[] selectionArgs = new String[] {String.valueOf(id)};

        Integer actualizados = resolver.update(marca.CONTENT_URI, values, selection, selectionArgs);

        Intent i = new Intent(getContext(), GenericDilog.class);
        //i.putExtra("isOk", true);
        i.putExtra("message", String.format(getString(R.string.msjRegistroAct), String.valueOf(actualizados)));
        startActivity(i);

        cargaTablaMarca();

        edit = false;
        txtBtnAcept.setText(getString(R.string.accept));

    }

    private void deleteMarca(String id) {
        Log.d(TAG, "Elimina marca");
        final ContentResolver resolver = getContext().getContentResolver();
        String where = "_id =?";
        String[] idS = new String[]{id};
        int ArticulosEliminados  = resolver.delete(marca.CONTENT_URI, where, idS);

        Intent i = new Intent(getContext(), GenericDilog.class);
        //i.putExtra("isOk", true);
        i.putExtra("message", String.format(getString(R.string.msjRegistroElim), String.valueOf(ArticulosEliminados)));
        startActivity(i);

        cargaTablaMarca();

    }

    private void requestFocus(View view){
        if(view.requestFocus()){
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onListEdit(int id) {
        String[] projection = new String[] { MarcaColumns._ID, MarcaColumns.MARCA_CODE, MarcaColumns.MARCA_NAME, MarcaColumns.MARCA_IMAGEN };
        String where = MarcaColumns._ID + "=?";
        String[] vals = new String[]{String.valueOf(id)};
        Cursor MarcaCursor =  getContext().getContentResolver().query(TiendaFacilContract.marca.CONTENT_URI, projection, where, vals, null);

        if(MarcaCursor.moveToFirst()){
            do{
                MarcaEdit = MarcaCursor.getString(MarcaCursor.getColumnIndex(MarcaColumns.MARCA_NAME));
                MarcaEditId = MarcaCursor.getInt(MarcaCursor.getColumnIndex(MarcaColumns._ID));
                txtMarca.setText(MarcaEdit);
                txtCodigo.setText(MarcaCursor.getString(MarcaCursor.getColumnIndex(MarcaColumns.MARCA_CODE)));
            }while (MarcaCursor.moveToNext());
        }
        edit = true;
        txtBtnAcept.setText(getString(R.string.edit));
    }

    @Override
    public void onListElimina(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Elimina Marca");
        builder.setMessage("Desea eliminar la marca?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMarca(String.valueOf(id));
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();

        cargaTablaMarca();

    }
}
