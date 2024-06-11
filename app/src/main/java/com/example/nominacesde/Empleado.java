package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Empleado extends AppCompatActivity {

    private TextInputEditText editTextIdentificacion, editTextNombre, editTextApellido, editTextEmail, editTextCargo, editTextTipoContrato, editTextSalario, editTextIdEmpleado, editTextContrasena;
    private Button buttonGuardar, btnCancelar;
    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("tbl_empleados").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            String TAG = null;
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
        editTextIdentificacion = findViewById(R.id.input_identificacion_empleado);
        editTextNombre = findViewById(R.id.input_nombre_empleado);
        editTextApellido = findViewById(R.id.input_apellido_empleado);
        editTextEmail = findViewById(R.id.input_email_empleado);
        editTextCargo = findViewById(R.id.input_cargo_empleado);
        editTextTipoContrato = findViewById(R.id.input_tipo_contrato_empleado_empleado);
        editTextSalario = findViewById(R.id.input_salario_empleado);
        editTextIdEmpleado = findViewById(R.id.input_id_empledo);
        editTextContrasena = findViewById(R.id.input_contrasena_empleado);
        buttonGuardar = findViewById(R.id.GuardarEmpleado);

        // Agregar TextWatcher a editTextIdentificacion para copiar el texto a editTextIdEmpleado
        editTextIdentificacion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextIdEmpleado.setText(s.toString());
                editTextContrasena.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identificacion = editTextIdentificacion.getText().toString();
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String email = editTextEmail.getText().toString();
                String cargo = editTextCargo.getText().toString();
                String tipoContrato = editTextTipoContrato.getText().toString();
                String salarioFloat = editTextSalario.getText().toString();
                String idEmpleado = editTextIdEmpleado.getText().toString();
                String contrasena = editTextContrasena.getText().toString();

                float salario = Float.parseFloat(salarioFloat);

                if(identificacion.isEmpty()){
                    Toast.makeText(Empleado.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("tbl_empleados").whereEqualTo("id_empleado", identificacion).get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if(!querySnapshot.isEmpty()) {
                            Toast.makeText(Empleado.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        } else {
                            Map<String, Object> user = new HashMap<>();
                            user.put("id_empleado", identificacion);
                            user.put("nombre_empleado", nombre);
                            user.put("apellido_empleado", apellido);
                            user.put("email_empleado", email);
                            user.put("cargo_empleado", cargo);
                            user.put("tipo_contrato", tipoContrato);
                            user.put("salario_empleado", salario);
                            user.put("usuario", idEmpleado);
                            user.put("contrasena", contrasena);

                            db.collection("tbl_empleados")
                                    .add(user)
                                    .addOnSuccessListener(documentReference -> {
                                        Toast.makeText(Empleado.this, "Usuario guardado con éxito", Toast.LENGTH_SHORT).show();
                                        // Limpiar los campos después de guardar
                                        editTextIdentificacion.setText("");
                                        editTextNombre.setText("");
                                        editTextApellido.setText("");
                                        editTextEmail.setText("");
                                        editTextCargo.setText("");
                                        editTextTipoContrato.setText("");
                                        editTextSalario.setText("");
                                        editTextIdEmpleado.setText("");
                                        editTextContrasena.setText("");
                                    })
                                    .addOnFailureListener(e -> Toast.makeText(Empleado.this, "Error al guardar usuario", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        Toast.makeText(Empleado.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
        btnMenu.setOnClickListener(this::menu);

        btnCancelar = (Button) findViewById(R.id.CancelarEmpleado);
        btnCancelar.setOnClickListener(this::cancelarEmpleado);

    }
    public void menu(View view) {
        Intent menu = new Intent(Empleado.this, Menu.class);
        startActivity(menu);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton menu", Toast.LENGTH_SHORT).show();
    }

    public void cancelarEmpleado(View v) {
        Intent cancelarEmpleado = new Intent(Empleado.this, Empleado.class);
        // Limpiar los campos después de cancelar
        editTextIdentificacion.setText("");
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextEmail.setText("");
        editTextCargo.setText("");
        editTextTipoContrato.setText("");
        editTextSalario.setText("");
        editTextIdEmpleado.setText("");
        editTextContrasena.setText("");
        startActivity(cancelarEmpleado);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton cancelar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}