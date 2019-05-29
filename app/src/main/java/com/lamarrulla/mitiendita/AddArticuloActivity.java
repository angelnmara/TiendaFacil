package com.lamarrulla.mitiendita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddArticuloActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "com.lamarrulla.mitiendita.EXTRA_NOMBRE";
    public static final String EXTRA_DESCRIPCION = "com.lamarrulla.mitiendita.EXTRA_DESCRIPCION";
    public static final String EXTRA_CANTIDAD = "com.lamarrulla.mitiendita.EXTRA_CANTIDAD";

    private EditText txtNombre;
    private EditText txtDescripcion;
    private NumberPicker pkCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_articulo);
        txtNombre = findViewById(R.id.etxtNombre);
        txtDescripcion = findViewById(R.id.etxtDescripcion);
        pkCantidad = findViewById(R.id.pkCantidad);
        pkCantidad.setMinValue(1);
        pkCantidad.setMaxValue(100);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Agregar Articulo");
    }

    private void saveArticulo(){
        String nombre = txtNombre.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        int cantidad = pkCantidad.getValue();

        if(nombre.trim().isEmpty() || descripcion.trim().isEmpty()){
            Toast.makeText(this, "El nombre y la descripcion no pueden ir vacios", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DESCRIPCION, descripcion);
        data.putExtra(EXTRA_NOMBRE, nombre);
        data.putExtra(EXTRA_CANTIDAD, cantidad);
        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_articulo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_articulo:
                saveArticulo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
