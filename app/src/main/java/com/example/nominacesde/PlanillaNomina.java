package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlanillaNomina extends AppCompatActivity implements View.OnClickListener {

    Button btnGuardarLiquidacion, btnCancelarLiquidacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planilla_nomina);

        btnGuardarLiquidacion = (Button) findViewById(R.id.GuardarLiquidacion);
        btnGuardarLiquidacion.setOnClickListener(this);

        btnCancelarLiquidacion = (Button) findViewById(R.id.CancelarLiquidacion);
        btnCancelarLiquidacion.setOnClickListener(this::cancelarLiquidacion);
    }

    @Override
    public void onClick(View v) {
        Intent guardarLiquidacion = new Intent(PlanillaNomina.this, LiquidacionTiempoCompleto.class);
        startActivity(guardarLiquidacion);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton guarar", Toast.LENGTH_SHORT).show();
    }

    public void cancelarLiquidacion(View v) {
        Intent cancelarLiquidacion = new Intent(PlanillaNomina.this, LiquidacionTiempoCompleto.class);
        startActivity(cancelarLiquidacion);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton cancelar", Toast.LENGTH_SHORT).show();
    }

}