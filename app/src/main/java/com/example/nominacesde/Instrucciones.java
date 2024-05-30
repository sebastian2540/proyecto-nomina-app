package com.example.nominacesde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Instrucciones extends AppCompatActivity {
    ImageButton btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instrucciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btnMenu= findViewById(R.id.imageButtonMenu);
            btnMenu.setOnClickListener(this::menu);
            return insets;
        });
    }
    public void menu(View view) {
        Intent menu = new Intent(Instrucciones.this, Menu.class);
        startActivity(menu);
        Toast.makeText(getApplicationContext(), "Ha presionado el boton menu", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }
}