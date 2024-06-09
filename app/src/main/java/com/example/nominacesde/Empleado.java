package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Empleado extends AppCompatActivity {

    ImageButton btnMenu;
//    Spinner listaAreas;
//    String[] datosLista = {"Área", "Auxiliar Administrativo", "Contabilidad", "Nómina", "Sistemas"};

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


        btnMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
        btnMenu.setOnClickListener(this::menu);

//        listaAreas = (Spinner) findViewById(R.id.lista_areas_empleado);
//        ArrayAdapter<String> adaptadorArea = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosLista);
//        listaAreas.setAdapter(adaptadorArea);
//
//        listaAreas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
    public void menu(View view) {
        Intent menu = new Intent(Empleado.this, Menu.class);
        startActivity(menu);
//        Toast.makeText(getApplicationContext(), "Ha presionado el boton menu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}