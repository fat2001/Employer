package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TousRapport extends AppCompatActivity {
TextView txtTousRapport,txtMesRapport;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tous_rapport);
        txtTousRapport=findViewById(R.id.TousRapports);
        txtMesRapport=findViewById(R.id.MesRapports);

        txtMesRapport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(TousRapport.this,ArchiveActivity.class);
                startActivity(intent3);
            }
        });
    }
}