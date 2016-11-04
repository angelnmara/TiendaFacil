    package com.lamarrulla.www.tiendafacil.fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.MainActivity;
import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.dialogs.GenericDilog;
import com.lamarrulla.www.tiendafacil.listas.itemListDDL;
import com.lamarrulla.www.tiendafacil.listas.itemListMarca;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.article;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.ArticleColumns;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.marca;
import com.lamarrulla.www.tiendafacil.utils.ClickToSelectEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import com.lamarrulla.www.tiendafacil.adapters.AdapterDDLMarca;

import org.w3c.dom.Text;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

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

        private static final String TAG = "AltaArticuloFragment";

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static ImageView ImgProducto;
    public static byte[] ImgProductoByte;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean codigoExiste;

    // Objetos

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtDescripcion;
    private EditText txtPrecio;
    private EditText txtCosto;
    private EditText txtUnidades;
    private EditText txtMarca;

        private EditText ddlMarca;

        private boolean ArticleEliminado = false;

    //private ImageView ImgProducto;
    private CardView btn_accept;
        private CardView btn_eliminar;

        private TextView txtBtnAcept;

        private Cursor articulosCursor;


    //private OnFragmentInteractionListener mListener;


        ClickToSelectEditText<itemListDDL> ddl;

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

        txtMarca = (EditText) v.findViewById(R.id.txtMarca);
        //ddlMarca = (Spinner) v.findViewById(R.id.ddlMarca);

        //@InjectView(R.id.signup_text_input_job_category)

                ddlMarca = v.findViewById(R.id.email_sign_in_button);

        txtBtnAcept = (TextView) v.findViewById(R.id.txtBtnAcept);
        btn_eliminar = (CardView) v.findViewById(R.id.btn_eliminar);

        ImgProducto = (ImageView) v.findViewById(R.id.ImgProducto);

        btn_accept = (CardView) v.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);
        btn_eliminar.setOnClickListener(this);

        MainActivity.fab.setImageResource(R.drawable.ic_menu_camera);
        ImgProducto.setOnClickListener(this);

        /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(),
                R.layout.ddl_marca,
                getAllMarcas(),
                new String[]{marca._ID, marca.MARCA_NAME},
                new int[]{R.id.txtMarcaId, R.id.txtMarca});*/

        /*ArrayList<itemListDDL> item = new ArrayList();

        item.add(new itemListDDL(1, "hola"));
        item.add(new itemListDDL(2, "adios"));*/

        /*ArrayAdapter<itemListMarca> adapter = new AdapterDDLMarca(getContext(), 0);*/

        /*adapter.setDropDownViewResource(R.layout.ddl_marca);
        ddlMarca.setAdapter(adapter);*/

        fillarticulosCursor();

        if(articulosCursor.getCount()>0){
            codigoExiste = true;
            llenaPantalla(articulosCursor);
        }

        txtCodigo.setText(mParam1);

        List<itemListDDL> ilm = new ArrayList<>();

        ilm.add(new itemListDDL(1, ""));

        ddl.setItems(ilm);
        ddl.setOnItemSelectedListener(new ClickToSelectEditText.OnItemSelectedListener<itemListDDL>() {
                                          @Override
                                          public void onItemSelectedListener(itemListDDL item, int selectedIndex) {
                                              selectedIndex = 1;
                                          }
                                      });


        // Inflate the layout for this fragment
        return v;
    }

        private Cursor getAllMarcas() {
            String[] projection = new String[] { marca._ID, marca.MARCA_NAME};
            Cursor cursor = getContext().getContentResolver().query(TiendaFacilContract.marca.CONTENT_URI, projection, null, null, null);
            return cursor;
        }

        private void fillarticulosCursor() {
            String[] projection = new String[] { "_id", ArticleColumns.ARTICLE_NAME, ArticleColumns.ARTICLE_DESC, ArticleColumns.ARTICLE_PRECIO, ArticleColumns.ARTICLE_COSTO, ArticleColumns.ARTICLE_FOTO, ArticleColumns.ARTICLE_STOCK, ArticleColumns.ARTICLE_MARCA_ID };
            String selection = article.ARTICLE_CODE + " = ?";
            String[] selectionArgs = new String[] {mParam1};
            articulosCursor = getContext().getContentResolver().query(TiendaFacilContract.article.CONTENT_URI, projection, selection, selectionArgs, null);
        }

        private void llenaPantalla(Cursor articulosCursor) {
        if(articulosCursor.moveToFirst()){
            do{
                txtNombre.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_NAME)));
                txtUnidades.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_STOCK)));
                txtCosto.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_COSTO)));
                txtDescripcion.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_DESC)));
                txtPrecio.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_PRECIO)));
                txtMarca.setText(articulosCursor.getString(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_MARCA_ID)));
                byte[] btm = articulosCursor.getBlob(articulosCursor.getColumnIndex(ArticleColumns.ARTICLE_FOTO));
                if (btm != null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(btm, 0, btm.length);
                    ImgProducto.setImageBitmap(bitmap);
                }
                txtBtnAcept.setText(getResources().getString(R.string.edit));
                btn_eliminar.setVisibility(View.VISIBLE);
            }while (articulosCursor.moveToNext());
        }
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
                if(codigoExiste){
                    editArticle();
                }else{
                    saveArticle();
                }
                break;
            case R.id.btn_eliminar:
                Log.d(TAG, "Entra a eliminar registro");
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle(getString(R.string.msjEliminarArticulo));
                builder.setMessage(getString(R.string.msjDeseasElimArticulo));
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (EliminaArticle()){
                            Intent i = new Intent(getContext(), GenericDilog.class);
                            i.putExtra("isOk", true);
                            i.putExtra("message", getString(R.string.msjArticuloEliminadoOK));
                            startActivity(i);
                        }else{
                            Toast.makeText(getContext(), getString(R.string.msjErrorActRegistro), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.show();
                break;
            default:
                Toast.makeText(getContext(), "Opcion invalida", Toast.LENGTH_SHORT).show();
                break;
        }
    }

        private boolean EliminaArticle() {
            final ContentResolver resolver = getContext().getContentResolver();
            String where = article.ARTICLE_CODE + "=?";
            String[] idS = new String[]{mParam1};
            int ArticulosEliminados  = resolver.delete(TiendaFacilContract.article.CONTENT_URI, where, idS);
            if(ArticulosEliminados>0){
                ArticleEliminado = true;
                Log.d(TAG, "Registro se elimino correctamente");
            }

            return ArticleEliminado;

        }

        private void editArticle() {

            try{
                final ContentResolver resolver = getContext().getContentResolver();
                final ContentValues values = new ContentValues();

                values.put(ArticleColumns.ARTICLE_DESC, txtDescripcion.getText().toString());
                values.put(ArticleColumns.ARTICLE_NAME, txtNombre.getText().toString());
                values.put(ArticleColumns.ARTICLE_PRECIO, txtPrecio.getText().toString());
                values.put(ArticleColumns.ARTICLE_COSTO, txtCosto.getText().toString());
                values.put(ArticleColumns.ARTICLE_STOCK, txtUnidades.getText().toString());
                values.put(ArticleColumns.ARTICLE_MARCA_ID, txtMarca.getText().toString());
                values.put(ArticleColumns.ARTICLE_FOTO, ImgProductoByte);

                String selection =  ArticleColumns.ARTICLE_CODE + "= ?";
                String[] selectionArgs = new String[] {mParam1};

                Integer actualizados = resolver.update(TiendaFacilContract.article.CONTENT_URI, values, selection, selectionArgs);

                if(actualizados > 0){
                    Intent i = new Intent(getContext(), GenericDilog.class);
                    i.putExtra("isOk", true);
                    i.putExtra("message", "Se actualizo exitosamente " + actualizados + " regisro");
                    startActivity(i);
                }else{
                    Intent i = new Intent(getContext(), GenericDilog.class);
                    i.putExtra("message", "Existio un error al actualizar los registros");
                    startActivity(i);
                }
            } catch (Exception ex){
                Intent i = new Intent(getContext(), GenericDilog.class);
                i.putExtra("message", "Existio un error al actualizar los registros:" + ex.getMessage());
                startActivity(i);
            }

        }

        private void saveArticle() {

        try{
            final ContentResolver resolver = getContext().getContentResolver();
            final ContentValues values = new ContentValues();

            values.put(ArticleColumns.ARTICLE_CODE, mParam1);
            values.put(ArticleColumns.ARTICLE_DESC, txtDescripcion.getText().toString());
            values.put(ArticleColumns.ARTICLE_MARCA_ID, txtMarca.getText().toString());
            values.put(ArticleColumns.ARTICLE_NAME, txtNombre.getText().toString());
            values.put(ArticleColumns.ARTICLE_PRECIO, txtPrecio.getText().toString());
            values.put(ArticleColumns.ARTICLE_COSTO, txtCosto.getText().toString());
            values.put(ArticleColumns.ARTICLE_STOCK, txtUnidades.getText().toString());
            values.put(ArticleColumns.ARTICLE_FOTO, ImgProductoByte);

            final Uri newUri = resolver.insert(TiendaFacilContract.article.CONTENT_URI, values);

            Intent i = new Intent(getContext(), GenericDilog.class);
            String article = TiendaFacilContract.article.getArticleId(newUri).toString();
            i.putExtra("isOk", true);
            i.putExtra("message", "El registro se inserto correctamente con el id: " + article);
            startActivity(i);

            return;

        }catch (Exception ex){
            Intent i = new Intent(getContext(), GenericDilog.class);
            i.putExtra("message", "Existio un error al insertar el articulo");
            startActivity(i);
        }

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
