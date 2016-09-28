package com.lamarrulla.www.tiendafacil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.lamarrulla.www.tiendafacil.adapters.MyMenuRecyclerViewAdapter;
import com.lamarrulla.www.tiendafacil.contents.MenuContent;
import com.lamarrulla.www.tiendafacil.fragments.AlmacenFragment;
import com.lamarrulla.www.tiendafacil.fragments.AltaArticuloFragment;
import com.lamarrulla.www.tiendafacil.fragments.PrincipalFragment;
import com.lamarrulla.www.tiendafacil.fragments.TiendaFragment;
import com.lamarrulla.www.tiendafacil.utils.ViewPagerAdapter;

import static com.lamarrulla.www.tiendafacil.R.id.recyclerView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MyMenuRecyclerViewAdapter.OnListFragmentMenu {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FragmentManager gfm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gfm = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            /*Toolbar toolbarL = (Toolbar) findViewById(R.id.toolbarL);
            toolbarL.setTitle("angelnmara@hotmail.com");*/

            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

            /*final Typeface tfa = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");*/
            //final Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Thin.ttf");

            /*collapsingToolbarLayout.setCollapsedTitleTypeface(tfa);*/
            /*collapsingToolbarLayout.setExpandedTitleTypeface(tf);*/

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(new MyMenuRecyclerViewAdapter(MenuContent.ITEMS, MainActivity.this));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PrincipalFragment pf = PrincipalFragment.newInstance("", "");
        gfm.beginTransaction().replace(R.id.lnlContent, pf, "PrincipalFragment").addToBackStack("PrincipalFragment").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_alta) {

        } else if (id == R.id.nav_baja) {

        } else if (id == R.id.nav_salir) {

        }

        /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentM(String id) {
        switch (id){
            case "0":
                limpiaFragments();
                Fragment PF = PrincipalFragment.newInstance("", "");
                gfm.beginTransaction().replace(R.id.lnlContent, PF, "PrincipalFragment").commit();
                break;
            case "1":
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.initiateScan();
                Fragment AAF = AltaArticuloFragment.newInstance("", "");
                gfm.beginTransaction().replace(R.id.lnlContent, AAF, "AltaArticuloFragment").addToBackStack("AltaArticuloFragment").commit();
                break;
            default:
                Toast.makeText(this, "Opcion invalida", Toast.LENGTH_SHORT).show();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void limpiaFragments() {
        for(int i = 0; i < gfm.getBackStackEntryCount(); ++i) {
            gfm.popBackStack();
        }
    }
}
