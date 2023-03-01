package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ResponsableAnnonces extends AppCompatActivity {
    static RecyclerView recyclerView;
    static Anonce_adapter adapter;
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

        Connecxion con = new Connecxion();

        recyclerView = (RecyclerView) findViewById(R.id.receyclerannonce);
        ArrayList<Activite> list = new ArrayList<>();
        // list.add(new Activite("22/4/2023", "لنحترم قانون السير", "Casablanca"));
        //  list.add(new Activite("26/09/2023", "يجب استخدام كاميرات المراقبة من طرف الشرطة والدرك لتفادي الرشوة هكذا من تبثث في حقه مخالفة سيؤديها ومن حاول ارشاء الشرطة او الدرك سيحرر في حقه محضر الارتشاء", "Guelmim"));
        //  list.add(new Activite("03/12/2023", "وتنظم بالمؤسسات التعليمية بهذه المناسبة، أنشطة تربوية وفنية وثقافية ومسابقات، اعتبارا لأهمية توعية المتعلمات والمتعلمين بقواعد السلامة الطرقية، وترسيخ ثقافة احترامها لدى الناشئة، من أجل حياة آمنة خالية من المخاطر.", "Tiznit"));
        //Anonce_adapter adapter = new Anonce_adapter(, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL,false));
        // recyclerView.setAdapter(adapter);
        con.getallactivity(this);
        // con.getmesactivity(1,this);

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