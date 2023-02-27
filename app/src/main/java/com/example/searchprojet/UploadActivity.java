package com.example.searchprojet;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class UploadActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private EditText edittextDate,editTextTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog mTimePickerDialog;

    ImageView uploadImage;
    Button saveButton;
    EditText uploadTopic, uploadDesc, uploadLieu;
    String imageURL;
    Uri uri;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);



        // spinner
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner1=findViewById(R.id.service);

// type
        String [] Types ={"choisir une type","Activité","Réunion","Séminaire","Formation"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Types);
        spinner.setAdapter(arrayAdapter);
//service
        String [] Services ={"choisir une Division","DAI","DAS","DAEC","DUE","DAR","RNP","RSU","DE","DCL"};
        ArrayAdapter<String>arrayAdapter1=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Services);
        spinner1.setAdapter(arrayAdapter1);

        //date
        edittextDate=findViewById(R.id.editTextDate);
        java.util.Calendar calendar= java.util.Calendar.getInstance();
        int year=calendar.get(java.util.Calendar.YEAR);
        int month=calendar.get(java.util.Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog=new DatePickerDialog(this,this,year,month,day);
        datePickerDialog.setCancelable(true);
        edittextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });






            uploadImage = findViewById(R.id.uploadImage);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadTopic = findViewById(R.id.uploadsujet);
        uploadLieu = findViewById(R.id.uploadLieu);
        saveButton = findViewById(R.id.saveButton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UploadActivity.this,DetailActivity.class);
                startActivity(intent);

            }
        });
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
               saveData();
            }
        });
    }
    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void uploadData(){
        String sujet = uploadTopic.getText().toString();
        String desc = uploadDesc.getText().toString();
        String lieu = uploadLieu.getText().toString();
        DataClass dataClass = new DataClass(sujet, desc, lieu, imageURL);
        Activite act1 = new Activite(edittextDate.getText().toString(),editTextTime.getText().toString(),sujet,lieu,desc,imageURL);
        Log.d("sssss",act1.toString());
        Connecxion connecxion = new Connecxion();
        connecxion.addActivite(14,act1);


       FirebaseDatabase.getInstance().getReference("Mes activites").child("Activité info")
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

}



    //date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String date = dayOfMonth + "/" + month + "/" + year;
        edittextDate.setText(date);

        //time
        editTextTime = findViewById(R.id.editTextTime);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePickerDialog = new TimePickerDialog(this, this, hour, minute, false);
        mTimePickerDialog.setCancelable(true);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePickerDialog.show();
            }
        });
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time=hourOfDay + ":" +minute;
        editTextTime.setText(time);
    }
    }