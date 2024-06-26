package com.example.nominacesde;

import android.app.DatePickerDialog;
import java.time.format.DateTimeFormatter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.Locale;


public class LiquidacionTiempoCompleto extends AppCompatActivity {
    TextView tvFechaInicial, tvFechaFinal;
    Spinner listas;
    String[] datos = {"Seleccione Empleado","Dayana Hernandez", "Luisa Rojas Metaute", "Sebastian Villada", "Susana Villa"};
    Button btnPorDias, btnColillaEmpleado, btnColillaEmpleador, btnSeleccionarFechaInicial,getBtnSeleccionarFechaFinal;
    ImageButton btnMenu;
    private CheckBox checkboxAuxilioTransporte, checkBoxAuxlioTransporteNo;
    private EditText editTextAuxilioTransporte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liquidacion_tiempo_completo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            tvFechaInicial = findViewById(R.id.fechaIngreso);
            tvFechaFinal = findViewById(R.id.fechaFinal);

            btnPorDias = (Button) findViewById(R.id.botonPorDias);
            btnPorDias.setOnClickListener(this::liquidacionPorDias);

            btnMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
            btnMenu.setOnClickListener(this::menu);

            btnColillaEmpleado = (Button) findViewById(R.id.liquidacionEmpleado);
            btnColillaEmpleado.setOnClickListener(this::colillaEmpleado);

            btnColillaEmpleador = (Button) findViewById(R.id.liquidacionEmpleador);
            btnColillaEmpleador.setOnClickListener(this::colillaEmpleador);

            checkboxAuxilioTransporte = findViewById(R.id.checkboxAuxilioTransporte);
            checkBoxAuxlioTransporteNo = findViewById(R.id.checkboxAuxilioTransporteFalse);
            editTextAuxilioTransporte = findViewById(R.id.editTextAuxilioTransporte);

            listas = (Spinner)findViewById(R.id.lista_tiempo_completo);
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

            checkboxAuxilioTransporte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkboxAuxilioTransporte.isChecked()) {
                        if (checkBoxAuxlioTransporteNo.isChecked()) {
                            checkboxAuxilioTransporte.setChecked(false);
                        } else {
                            editTextAuxilioTransporte.setVisibility(View.VISIBLE);
                        }
                    } else {
                        editTextAuxilioTransporte.setVisibility(View.GONE);
                    }
                }
            });

            checkBoxAuxlioTransporteNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBoxAuxlioTransporteNo.isChecked()) {
                        if (checkboxAuxilioTransporte.isChecked()) {
                            checkBoxAuxlioTransporteNo.setChecked(false);
                        }
                    }
                }
            });
            return insets;
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirCalendario(View view) {

        LocalDate fechaInicial = LocalDate.of(2024, 1, 1);

        int ano = fechaInicial.getYear();
        int mes = fechaInicial.getMonthValue() - 1;
        int dia = fechaInicial.getDayOfMonth();

        Locale spanish = new Locale("es", "ES");
        Locale.setDefault(spanish);

        DatePickerDialog dpd = new DatePickerDialog(LiquidacionTiempoCompleto.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Crear una fecha con los valores seleccionados
                LocalDate fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth);

                // Formatear la fecha a cadena en formato "d/M/yyyy"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                String fechaFormateada = fechaSeleccionada.format(formatter);

                // Establecer la fecha en el TextView
                tvFechaInicial.setText(fechaFormateada);
            }
        }, ano, mes, dia);
        dpd.show();
    }
    //Codigo para organizar el calendario

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirCalendarioFechaFinal(View view) {

        LocalDate fechaInicial = LocalDate.of(2024, 1, 1);

        int ano = fechaInicial.getYear();
        int mes = fechaInicial.getMonthValue() - 1;
        int dia = fechaInicial.getDayOfMonth();

        Locale spanish = new Locale("es", "ES");
        Locale.setDefault(spanish);

        DatePickerDialog dpd = new DatePickerDialog(LiquidacionTiempoCompleto.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Crear una fecha con los valores seleccionados
                LocalDate fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth);

                // Formatear la fecha a cadena en formato "d/M/yyyy"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                String fechaFormateada = fechaSeleccionada.format(formatter);

                // Establecer la fecha en el TextView
                tvFechaFinal.setText(fechaFormateada);
            }
        }, ano, mes, dia);
        dpd.show();
    }

    public void liquidacionPorDias(View view) {
        Intent lqPorDias = new Intent(LiquidacionTiempoCompleto.this, LiquidacionPorDias.class);
        startActivity(lqPorDias);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion por dias", Toast.LENGTH_SHORT).show();
    }

    public void menu(View view) {
        Intent menu = new Intent(LiquidacionTiempoCompleto.this, Menu.class);
        startActivity(menu);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton menu", Toast.LENGTH_SHORT).show();
    }

    public void colillaEmpleado(View view) {
        Intent colillaNomimaEmpleado = new Intent(LiquidacionTiempoCompleto.this, PlanillaNomina.class);
        startActivity(colillaNomimaEmpleado);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion para empleado", Toast.LENGTH_SHORT).show();
    }

    public void colillaEmpleador(View view) {
        Intent colillaNomimaEmpleador = new Intent(LiquidacionTiempoCompleto.this, PlanillaLiquidacionEmpleador.class);
        startActivity(colillaNomimaEmpleador);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion para empleador", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}