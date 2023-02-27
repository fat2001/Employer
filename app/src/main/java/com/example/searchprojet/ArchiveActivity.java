package com.example.searchprojet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArchiveActivity extends AppCompatActivity {
    TextView txtMesRapprt,txtTousRapprt;
    FloatingActionButton Ajouter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        txtMesRapprt=findViewById(R.id.MesArchives);
        txtTousRapprt=findViewById(R.id.TousArchive);
        Ajouter=findViewById(R.id.ajouterRapport);

        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(ArchiveActivity.this,UploadRapport.class);
                startActivity(intent4);
            }
        });

        txtTousRapprt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ArchiveActivity.this, TousRapport.class);
                startActivity(intent);
            }
        });
    }
}
