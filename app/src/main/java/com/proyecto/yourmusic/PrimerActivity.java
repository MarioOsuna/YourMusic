package com.proyecto.yourmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrimerActivity extends AppCompatActivity {
    TextView textViewRegistrar, textViewSaltar,textViewUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        textViewRegistrar = findViewById(R.id.textViewRegistrar);
        textViewSaltar = findViewById(R.id.textViewSaltar);
        textViewUsuario = findViewById(R.id.textViewRegistrarUsuario);
        textViewUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textViewRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrimerActivity.this, RegistrarGrupoActivity.class);

                startActivity(i);
            }
        });

        textViewSaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
