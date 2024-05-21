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


    Button btningresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btningresar = findViewById(R.id.continuar);

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continuar = new Intent(MainActivity.this, Login.class);
                startActivity(continuar);
                Toast.makeText(getApplicationContext(), "Ha presionado el boton continuar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
