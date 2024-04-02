package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView usuario, contrasena;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.InputUsuario);
        contrasena = findViewById(R.id.InputContrasena);
        ingresar = findViewById(R.id.ButtonIngresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerLogin verificar = new VerLogin(usuario.getText().toString(), contrasena.getText().toString());
                if (verificar.validacioncorreo() == 1){
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent seguir = new Intent(MainActivity.this, Menu.class);
                    startActivity(seguir);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario y Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}