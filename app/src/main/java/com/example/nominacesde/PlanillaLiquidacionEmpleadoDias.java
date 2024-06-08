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

public class PlanillaLiquidacionEmpleadoDias extends AppCompatActivity {
    Button btnGuardarLiquidacion, btnCancelarLiquidacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planilla_liquidacion_empleado_dias);

        btnGuardarLiquidacion = (Button) findViewById(R.id.GuardarLiquidacion);
        btnGuardarLiquidacion.setOnClickListener((View.OnClickListener) this);

        btnCancelarLiquidacion = (Button) findViewById(R.id.CancelarLiquidacion);
        btnCancelarLiquidacion.setOnClickListener(this::cancelarLiquidacionDias);

    }
    public void onClick(View v) {
        Intent guardarLiquidacion = new Intent(PlanillaLiquidacionEmpleadoDias.this, LiquidacionPorDias.class);
        startActivity(guardarLiquidacion);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton guarar", Toast.LENGTH_SHORT).show();
    }
    public void cancelarLiquidacionDias(View v) {
        Intent cancelarLiquidacionDias = new Intent(PlanillaLiquidacionEmpleadoDias.this, LiquidacionPorDias.class);
        startActivity(cancelarLiquidacionDias);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton cancelar", Toast.LENGTH_SHORT).show();
    }
}