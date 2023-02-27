package com.example.searchprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadRapport extends AppCompatActivity {
    EditText mTitleEt,mDescriptionEt;
    Button mSaveBtn;
    ProgressDialog pd;


    //firestore Instance
    FirebaseFirestore db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_rapport);
        //actionbar and its title
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Add Data");



        mTitleEt=findViewById(R.id.sujetRapport);
        mDescriptionEt=findViewById(R.id.DescRapport);

        mSaveBtn=findViewById(R.id.saveButton1);

        pd=new ProgressDialog(this);

        //firestrore
        db=FirebaseFirestore.getInstance();

        //click button to upload data
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input data
                String title=mTitleEt.getText().toString().trim();
                String description =mDescriptionEt.getText().toString().trim();
                //function call to upload data
                uploadData(title,description);

            }
        });


    }

    private void uploadData(String title, String description) {
        //set title of progress bar
        pd.setTitle("Ajouter les information");
        //show progress bar when user click save button
        pd.show();
        //random id for each data to be stored
        String id = UUID.randomUUID().toString();
        Map<String,Object> doc = new HashMap<>();
        doc.put("id",id);//id of data
        doc.put("title",title);
        doc.put("description",description);
        //add this data
        db.collection("Rapports").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully

                        pd.dismiss();
                        Toast.makeText(UploadRapport.this,"Uploaded...",Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called if there is any error while uploading
                        pd.dismiss();
                        //get and show error message
                        Toast.makeText(UploadRapport.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }



}