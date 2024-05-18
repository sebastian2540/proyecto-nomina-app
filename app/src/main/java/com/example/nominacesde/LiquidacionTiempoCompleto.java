package com.example.nominacesde;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class LiquidacionTiempoCompleto extends AppCompatActivity {
    TextView tv;
    Spinner listas;
    String[] datos = {"Seleccione Empleado","Dayana Hernandez", "Luisa Rojas Metaute", "Sebastian Villada", "Susana Villa"};
    Button btnPorDias, btnColillaEmpleado;
    ImageButton btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liquidacion_tiempo_completo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            tv = findViewById(R.id.fechaIngreso);
            listas = (Spinner)findViewById(R.id.lista_tiempo_completo);

            btnPorDias = (Button) findViewById(R.id.botonPorDias);
            btnPorDias.setOnClickListener(this::liquidacionPorDias);

            btnMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
            btnMenu.setOnClickListener(this::menu);

            btnColillaEmpleado = (Button) findViewById(R.id.liquidacionEmpleado);
            btnColillaEmpleado.setOnClickListener(this::colillaEmpleado);

            ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);
            listas.setAdapter(adaptador);
            listas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return insets;
        });
    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(LiquidacionTiempoCompleto.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/"  + year;
                tv.setText(fecha);
            }
        },dia,mes,ano);
        dpd.show();
    }

    public void liquidacionPorDias(View view) {
        Intent lqPorDias = new Intent(LiquidacionTiempoCompleto.this, LiquidacionPorDias.class);
        startActivity(lqPorDias);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion por dias", Toast.LENGTH_SHORT).show();
    }

    public void menu(View view) {
        Intent menu = new Intent(LiquidacionTiempoCompleto.this, Menu.class);
        startActivity(menu);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton menu", Toast.LENGTH_SHORT).show();
    }

    public void colillaEmpleado(View view) {
        Intent colillaNomimaEmpleado = new Intent(LiquidacionTiempoCompleto.this, PlanillaNomina.class);
        startActivity(colillaNomimaEmpleado);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion para empleado", Toast.LENGTH_SHORT).show();
    }
}