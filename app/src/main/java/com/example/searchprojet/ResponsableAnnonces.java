package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResponsableAnnonces extends AppCompatActivity {
 TextView txtMesAnnonces;
 TextView txtTousAnnonces;
 LinearLayout ln;
 FloatingActionButton Ajouter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsable_annonces);
        txtMesAnnonces = findViewById(R.id.MesAnnonces);
        txtTousAnnonces = findViewById(R.id.TousAnnonces);
        Ajouter=findViewById(R.id.ajouter);

        txtTousAnnonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResponsableAnnonces.this, TousAnnonces.class);
                startActivity(intent);
            }
        });
        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2=new Intent(ResponsableAnnonces.this,UploadActivity.class);
                startActivity(int2);
            }
        });

    }


}