package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity_annonce extends AppCompatActivity {
 static RecyclerView recyclerView;
static Anonce_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Connecxion con = new Connecxion();
        setContentView(R.layout.activity_annonce);
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





    }
}