package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RestaurarContrasena extends AppCompatActivity {

    ImageButton btnInicio;

    TextView email, confirEmail;
    Button btnEnviarRecuperacion;
    FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.InputEmail);
        confirEmail = findViewById(R.id.InputConfirmarEmail);

        btnInicio = (ImageButton) findViewById(R.id.imageButtonInicio);
        btnInicio.setOnClickListener(this::inicioSesion);

        btnEnviarRecuperacion = findViewById(R.id.ButtonEnviar);

        btnEnviarRecuperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyEmails();
                String email_empleado = email.getText().toString();

                if(email_empleado.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Debe ingresar un email", Toast.LENGTH_SHORT).show();
                } else {
                    sendResetEmail(email_empleado);
                }
            }
        });
    }

    public void sendResetEmail(String email_empleado) {
        mAuth.sendPasswordResetEmail(email_empleado).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Se ha enviado un email para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RestaurarContrasena.this, Login.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Error al enviar el email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verifyEmails(){
        String emailEmpleado = email.getText().toString();
        String emailConfirmar = confirEmail.getText().toString();

        if(TextUtils.isEmpty(emailEmpleado) || TextUtils.isEmpty(emailConfirmar)){
            Toast.makeText(getApplicationContext(), "Debe ingresar un email y confirmarlo", Toast.LENGTH_SHORT).show();
        } else if (!emailEmpleado.equals(confirEmail)) {
            Toast.makeText(getApplicationContext(), "Los correos electrónico no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Correo electrónico confirmado correctamente", Toast.LENGTH_SHORT).show();
        }
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