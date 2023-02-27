package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TousAnnonces extends AppCompatActivity {
TextView txtTousannonces;
TextView txtmesannances;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tous_annonces);
        txtTousannonces=findViewById(R.id.TousAnnonces1);
        txtmesannances=findViewById(R.id.MesAnnonces1);
        txtmesannances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TousAnnonces.this, ResponsableAnnonces.class);
                startActivity(intent);
            }
        });


    }
}