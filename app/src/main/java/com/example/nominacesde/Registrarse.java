package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    TextView email, contrasena;
    ImageButton btnInicio;
    Button btnEnviarRegistro;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.InputEmail);
        contrasena = findViewById(R.id.InputContrasena);

        btnInicio = (ImageButton) findViewById(R.id.imageButtonInicio);
        btnInicio.setOnClickListener(this::inicioSesion);

        btnEnviarRegistro = findViewById(R.id.ButtonEnviar);

        btnEnviarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_empleado = email.getText().toString();
                String contrasena_empleado = contrasena.getText().toString();

                if(email_empleado.isEmpty() && contrasena_empleado.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Los datos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                } else {
                    createUsers(email_empleado, contrasena_empleado);
                }
            }
        });
    }

    public void createUsers(String email_empleado, String contrasena_empleado){
        mAuth.createUserWithEmailAndPassword(email_empleado, contrasena_empleado).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registrarse.this, Login.class));
                }  else {
                    Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void inicioSesion(View view) {
        Intent inicio = new Intent(Registrarse.this, Login.class);
        startActivity(inicio);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton inicio", Toast.LENGTH_SHORT).show();
    }
}