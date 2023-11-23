package com.example.dailytrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shred_prefs",MODE_PRIVATE);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardRules = findViewById(R.id.cardRules);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardMap = findViewById(R.id.cardmap);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardAddUser = findViewById(R.id.cardAddUser);
        CardView cardOrder = findViewById(R.id.cardOrderDetails);
        CardView cardHealthDr = findViewById(R.id.cardHealtDr);
        CardView cardExist = findViewById(R.id.cardExist);

        cardRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,RulesActivity.class));
            }
        });
        cardExist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });

         cardMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MapsActivity.class));

            }
        });

         cardAddUser.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(HomeActivity.this,AddUserActivity.class));
             }
         });

    }
}