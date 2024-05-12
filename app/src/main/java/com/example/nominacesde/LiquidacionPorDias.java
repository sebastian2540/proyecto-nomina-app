package com.example.nominacesde;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class LiquidacionPorDias extends AppCompatActivity {
    TextView tv;
    Button btnTiempoCompleto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liquidacion_por_dias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btnTiempoCompleto = (Button) findViewById(R.id.botonTiempoCompleto);
            btnTiempoCompleto.setOnClickListener(this::liquidacionTiempoCompleto);

            tv = findViewById(R.id.fechaIngreso);
            return insets;
        });
    }
    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(LiquidacionPorDias.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/"  + year;
                tv.setText(fecha);
            }
        },dia,mes,ano);
        dpd.show();
    }

    public void liquidacionTiempoCompleto(View view) {
        Intent lqTiempoCompleto = new Intent(LiquidacionPorDias.this, LiquidacionTiempoCompleto.class);
        startActivity(lqTiempoCompleto);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidación tiempo completo", Toast.LENGTH_SHORT).show();
    }
}