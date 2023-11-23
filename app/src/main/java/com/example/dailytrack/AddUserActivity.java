package com.example.dailytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        EditText EmpName = findViewById(R.id.editTextName);
        EditText EmpMail = findViewById(R.id.editTextEmail);
        EditText EmpPhone = findViewById(R.id.editTextPhone);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText EmpDesign = findViewById(R.id.editTextDesign);

        Button btnAdd = findViewById(R.id.buttonAddEmployee);
        Button btnCancle = findViewById(R.id.buttonCancleEmployee);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Database code to add new user
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });
    }
}