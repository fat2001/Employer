package com.example.projetgenieinformatique;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashbordDirecteur extends AppCompatActivity {
   private TextView annonce;
   private TextView activité;
    private TextView archive;
    private TextView rapport;
    private TextView profile;
    private TextView recherche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord_directeur);
        annonce=(TextView) findViewById(R.id.Annonces);
        activité=(TextView) findViewById(R.id.Activité);
        archive=(TextView) findViewById(R.id.Archive);
        rapport=(TextView) findViewById(R.id.rapport);
        profile=(TextView) findViewById(R.id.profile);
        recherche=(TextView) findViewById(R.id.recherche);





        annonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashbordDirecteur.this, Detail.class);
                startActivity(intent);
            }
        });
    }

}