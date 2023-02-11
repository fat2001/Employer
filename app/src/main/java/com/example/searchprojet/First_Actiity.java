package com.example.searchprojet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class First_Actiity extends Activity {
    Button btn_Admin,btn_User;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_actiity);

        btn_Admin=findViewById(R.id.btn_admin);
        btn_User=findViewById(R.id.btn_user);

        btn_User.setOnClickListener(new View.OnClickListener() {
            @Override
            //
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(First_Actiity.this,loginActivity.class);
                startActivity(intent);
            }

        });
    }
}
