package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.nominacesde.LiquidacionTiempoCompleto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


public class Menu extends AppCompatActivity implements View.OnClickListener{
   Button btnLiquidacion, btnEmpleado, btnArea, btnCerrarSesion, btnInstrucciones;
   private TextView textViewUsuario;
   private FirebaseAuth mAuth;
   private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

//        // Inicializar Firebase Auth y Firestore
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//
//        // Inicializar el TextView
//        textViewUsuario = findViewById(R.id.textViewUsuarioFire);

        // Obtener el usuario actual
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            // Obtener datos del usuario desde Cloud Firestore
//            getUserData(currentUser.getUid());
//        } else {
//            textViewUsuario.setText("No hay usuario con sesión activa");
//        }

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

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

//    private void getUserData(String userId) {
//        DocumentReference userRef = db.collection("tbl_empleados").document(userId);
//        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        // Obtener los datos del usuario
//                        String userName = document.getString("nombre_empleado");
//                        String userEmail = document.getString("email_empleado");
//                        if (userName != null && userEmail != null) {
//                            textViewUsuario.setText("Nombre: " + userName + "\nEmail: " + userEmail);
//                        } else {
//                            textViewUsuario.setText("Los campos 'nombre_empleado' y/o 'email_empleado' no existen en el documento");
//                        }
//                    } else {
//                        textViewUsuario.setText("No se encontraron datos para el usuario");
//                    }
//                } else {
//                    textViewUsuario.setText("Error al obtener los datos del usuario: " + task.getException());
//                }
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        Intent liquidacion = new Intent(Menu.this, LiquidacionTiempoCompleto.class);
        startActivity(liquidacion);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton liquidacion", Toast.LENGTH_SHORT).show();
    }

    public void empleado(View view) {
        Intent empleados = new Intent(Menu.this, Empleado.class);
        startActivity(empleados);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton empleados", Toast.LENGTH_SHORT).show();
    }

//    public void area(View view) {
//        Intent areas = new Intent(Menu.this, Area.class);
//        startActivity(areas);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton area", Toast.LENGTH_SHORT).show();
//    }

    public void cerrarSesion(View view) {
        Intent cerrarSesion = new Intent(Menu.this, Login.class);
        startActivity(cerrarSesion);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton cerrar sesión", Toast.LENGTH_SHORT).show();
    }

    public void instrucciones(View view) {
        Intent instrucciones = new Intent(Menu.this, Instrucciones.class);
        startActivity(instrucciones);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton instrucciones", Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?");

        // Botón de confirmación
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción a realizar si el usuario confirma
                performPositiveAction();
            }
        });

        // Botón de cancelación
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción a realizar si el usuario cancela
                dialog.dismiss();
            }
        });

        // Crear y mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performPositiveAction() {
        // Acción que se realiza si el usuario confirma
        // Por ejemplo, cerrar la actividad
        Intent cerrarSesion = new Intent(Menu.this, Login.class);
        startActivity(cerrarSesion);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
    /*
    activiy_main.xml - RecyclerViewUsuario.xml
    MainActivity.java - RecyclerViewUsuario.java

    item_usuario.xml

    activity_edit_user.xml - EditarUsuario
    EditUserActivity.java - EditarUsuario

    Usuario.java
    UsuarioAdapter.java
     */
}