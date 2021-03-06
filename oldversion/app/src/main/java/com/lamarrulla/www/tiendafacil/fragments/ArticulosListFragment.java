package com.lamarrulla.www.tiendafacil.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.adapters.MyArticulosRVA;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract;
import com.lamarrulla.www.tiendafacil.contents.genericContentCursor;
import com.lamarrulla.www.tiendafacil.provider.TiendaFacilContract.article;

import com.lamarrulla.www.tiendafacil.cursores.allCursors;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ArticulosListFragment extends Fragment implements MyArticulosRVA.OnListFragmentInteractionListener{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    //private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticulosListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ArticulosListFragment newInstance(int columnCount) {
        ArticulosListFragment fragment = new ArticulosListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv_articulos, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            /*String[] projection = new String[] { article._ID,
                    article.ARTICLE_NAME,
                    article.ARTICLE_DESC,
                    article.ARTICLE_PRECIO,
                    article.ARTICLE_COSTO,
                    article.ARTICLE_FOTO,
                    article.ARTICLE_STOCK,
                    article.ARTICLE_MARCA_ID };

            Cursor ArticulosCursor =  getContext().getContentResolver().query(TiendaFacilContract.article.CONTENT_URI, projection, null, null, null);*/

            allCursors cur = new allCursors();
            cur.getCursorArticles(getContext());

            genericContentCursor.getData(cur.getCursor(), "itemListArticle");

            if (genericContentCursor.Item != null){
                recyclerView.setAdapter(new MyArticulosRVA(genericContentCursor.Item, ArticulosListFragment.this));
            }
        }
        return view;
    }


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    @Override
    public void onListFragmentInteraction(int id) {
        Toast.makeText(getContext(), "Pureba id: " + id, Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }*/
}
