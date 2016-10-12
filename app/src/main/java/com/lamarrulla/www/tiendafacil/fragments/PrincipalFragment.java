package com.lamarrulla.www.tiendafacil.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamarrulla.www.tiendafacil.R;
import com.lamarrulla.www.tiendafacil.utils.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrincipalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPager viewpager;
    private TabLayout tabPrincipal;

    //private OnFragmentInteractionListener mListener;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrincipalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrincipalFragment newInstance(String param1, String param2) {
        PrincipalFragment fragment = new PrincipalFragment();
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

        View v = inflater.inflate(R.layout.fragment_principal, container, false);
        viewpager = (ViewPager) v.findViewById(R.id.viewpagerTab);
        setupViewPager(viewpager);

        tabPrincipal = (TabLayout) v.findViewById(R.id.principalTab);
        tabPrincipal.setupWithViewPager(viewpager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        fnAgregaFragment();
    }

    private void fnAgregaFragment() {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.viewpagerTab) != null){
            String Tag1 = fm.findFragmentById(R.id.viewpagerTab).getTag();
            Fragment f0 = fm.findFragmentByTag(Tag1.substring(0, Tag1.length()-2) + ":0");
            Fragment f1 = fm.findFragmentByTag(Tag1);
            Fragment f2 = fm.findFragmentByTag("PrincipalFragment");

            fm.beginTransaction().remove(f0).commit();
            fm.beginTransaction().remove(f1).commit();
            fm.beginTransaction().remove(f2).commit();

            Fragment nf = PrincipalFragment.newInstance("", "");

            fm.beginTransaction().replace(R.id.lnlContent, nf, "PrincipalFragment").commit();

        }
    }

    private void setupViewPager(ViewPager viewpager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new TiendaFragment().newInstance("", ""), "TIENDA");
        adapter.addFragment(new AlmacenListFragment().newInstance("", ""), "ALMACEN");
        viewpager.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
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
