package com.example.searchprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {
    //download


    Button download;
    Bitmap bitmap;
    BitmapDrawable bitmapDrawable;

    TextView detailDesc, detailTitle;
    TextView detaildate;
    ImageView detailImage;
   /* FloatingActionButton deleteButton;
    String key = "";
    String imageUrl = "";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        download=findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmapDrawable=(BitmapDrawable) detailImage.getDrawable();
                bitmap=bitmapDrawable.getBitmap();
                FileOutputStream fileOutputStream=null;
                File sdCard= Environment.getExternalStorageDirectory();
                File Directory= new File (sdCard.getAbsolutePath()+ "/Download");
                Directory .mkdir();
                String filename=String.format("%d.jpg",System.currentTimeMillis());
                File outfile=new File(Directory,filename);
                Toast.makeText(DetailActivity.this, "l'image été enregistrer avec succées", Toast.LENGTH_SHORT).show();
                try{
                    fileOutputStream=new FileOutputStream(outfile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                    fileOutputStream.close();

                    Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outfile));
                    sendBroadcast(intent);

                }catch (FileNotFoundException e) {
                    e.printStackTrace();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });





        Activite e =(Activite) getIntent().getSerializableExtra("Activite");
        Log.d("ddsdsds",e.toString());
        detailDesc = findViewById(R.id.detailDesc);
        detaildate= findViewById(R.id.detaildate);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailTitle.setText(e.getSujet());
        detailDesc.setText(e.getDesc());
        detaildate.setText(e.getDate());
        // deleteButton=findViewById(R.id.deleteButton);
       /*
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");*/

        //      Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        //   }

       /* deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        });*/
    }
}