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

public class PlanillaLiquidacionEmpleador extends AppCompatActivity {

    Button btnGuardarLiquidacion, btnCancelarLiquidacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planilla_liquidacion_empleador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnGuardarLiquidacion = (Button) findViewById(R.id.GuardarLiquidacion);
        btnGuardarLiquidacion.setOnClickListener(this::guardarLiquidacion);

        btnCancelarLiquidacion = (Button) findViewById(R.id.CancelarLiquidacion);
        btnCancelarLiquidacion.setOnClickListener(this::cancelarLiquidacion);
    }

    public void guardarLiquidacion(View v) {
        Intent guardarLiquidacion = new Intent(PlanillaLiquidacionEmpleador.this, LiquidacionPorDias.class);
        startActivity(guardarLiquidacion);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton guarar", Toast.LENGTH_SHORT).show();
    }

    public void cancelarLiquidacion(View v) {
        Intent cancelarLiquidacion = new Intent(PlanillaLiquidacionEmpleador.this, LiquidacionPorDias.class);
        startActivity(cancelarLiquidacion);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton cancelar", Toast.LENGTH_SHORT).show();
    }
}