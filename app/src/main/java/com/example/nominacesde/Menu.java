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
import com.example.nominacesde.LiquidacionTiempoCompleto;


public class Menu extends AppCompatActivity implements View.OnClickListener{

   Button btnLiquidacion, btnEmpleado, btnArea, btnCerrarSesion, btnInstrucciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        btnEmpleado = (Button) findViewById(R.id.ButtonEmpleado);
        btnEmpleado.setOnClickListener(this::empleado);

//        btnArea = (Button) findViewById(R.id.ButtonArea);
//        btnArea.setOnClickListener(this::area);

        btnLiquidacion = (Button) findViewById(R.id.ButtonLiquidacion);
        btnLiquidacion.setOnClickListener(this);

        btnCerrarSesion = (Button) findViewById(R.id.ButtonCerrarSesion);
        btnCerrarSesion.setOnClickListener(this::cerrarSesion);

        btnInstrucciones = (Button) findViewById(R.id.ButtonInstrucciones);
        btnInstrucciones.setOnClickListener(this::instrucciones);
    }
    @Override
    public void onClick(View v) {
        Intent liquidacion = new Intent(Menu.this, LiquidacionTiempoCompleto.class);
        startActivity(liquidacion);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion", Toast.LENGTH_SHORT).show();
    }

    public void empleado(View view) {
        Intent empleados = new Intent(Menu.this, Empleado.class);
        startActivity(empleados);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton empleados", Toast.LENGTH_SHORT).show();
    }

//    public void area(View view) {
//        Intent areas = new Intent(Menu.this, Area.class);
//        startActivity(areas);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton area", Toast.LENGTH_SHORT).show();
//    }

    public void cerrarSesion(View view) {
        Intent cerrarSesion = new Intent(Menu.this, Login.class);
        startActivity(cerrarSesion);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton cerrar sesión", Toast.LENGTH_SHORT).show();
    }

    public void instrucciones(View view) {
        Intent instrucciones = new Intent(Menu.this, Instrucciones.class);
        startActivity(instrucciones);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton instrucciones", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}