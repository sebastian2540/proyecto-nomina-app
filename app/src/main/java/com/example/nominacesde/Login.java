package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView usuario, contrasena;
    Button ingresar, restablecerContrasena, btnRegistrarse;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        usuario = findViewById(R.id.InputUsuario);

        contrasena = findViewById(R.id.InputContrasena);

        ingresar = findViewById(R.id.ButtonIngresar);

        restablecerContrasena = (Button) findViewById(R.id.ButtonOlvidasteContrasena);
        restablecerContrasena.setOnClickListener(this::resturarContrasena);

        btnRegistrarse = (Button) findViewById(R.id.ButtonRegistrarse);
        btnRegistrarse.setOnClickListener(this::registrarse);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                VerLogin verificar = new VerLogin(usuario.getText().toString(), contrasena.getText().toString());
                if (verificar.validacionUsuario() == 1) {
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent seguir = new Intent(Login.this, Menu.class);
                    startActivity(seguir);
                } else {
                    Toast.makeText(Login.this, "Usuario y Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
                 */


                String email_empleado = usuario.getText().toString().trim();
                String contrasena_empleado = contrasena.getText().toString().trim();

                if(email_empleado.isEmpty() && contrasena_empleado.isEmpty()){
                        Toast.makeText(Login.this, "Ingresar los datos obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email_empleado,contrasena_empleado);
                }


            }
        });
    }

    public void loginUser(String email_empleado, String contrasena_empleado){
        mAuth.signInWithEmailAndPassword(email_empleado, contrasena_empleado).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, Menu.class));
                    Toast.makeText(Login.this,"BIENVENIDO",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void resturarContrasena(View view) {
        Intent restaurar = new Intent(Login.this, RestaurarContrasena.class);
        startActivity(restaurar);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton restaurar contraseña", Toast.LENGTH_SHORT).show();
    }

    public void registrarse(View view) {
        Intent registrarse = new Intent(Login.this, Registrarse.class);
        startActivity(registrarse);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton registrarse", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}