package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RestaurarContrasena extends AppCompatActivity {

    ImageButton btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurar_contrasena);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnInicio = (ImageButton) findViewById(R.id.imageButtonInicio);
        btnInicio.setOnClickListener(this::inicioSesion);
    }

    public void inicioSesion(View view) {
        Intent inicio = new Intent(RestaurarContrasena.this, Login.class);
        startActivity(inicio);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton inicio", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}