package com.lamarrulla.www.tiendafacil.fragments;

import android.content.Context;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lamarrulla.www.tiendafacil.R;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TiendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TiendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendaFragment extends Fragment implements View.OnClickListener, SurfaceHolder.Callback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TiendaFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SurfaceView SvFoto;
    private SurfaceHolder surfaceHolder;
    private CardView btn_accept;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tienda, container, false);
        SvFoto = (SurfaceView) v.findViewById(R.id.SvFoto);
        surfaceHolder = SvFoto.getHolder();
        surfaceHolder.addCallback(this);
        btn_accept = (CardView) v.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_accept:
                    start_camera();
                break;
            default:
                Toast.makeText(getContext(), getResources().getString(R.string.opcionInvalida), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void start_camera() {
        try {
            camera = Camera.open();
        }catch (RuntimeException ex){
            Log.e(TAG, "init_camera: " + ex);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();
        param.setPreviewFrameRate(20);
        param.setPreviewSize(176,144);
        camera.setDisplayOrientation(90);
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            Log.e(TAG, "init_camera: " + e);
            return;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}
