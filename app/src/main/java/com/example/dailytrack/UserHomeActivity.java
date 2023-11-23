package com.example.dailytrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.dailytrack.MainActivity;

public class UserHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        CardView markAttend = findViewById(R.id.cardMarkAttend);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView attendSummary = findViewById(R.id.cardAttendSum);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView leaveAply = findViewById(R.id.cardAplyLeave);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView leaveStus = findViewById(R.id.cardLeaveStus);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView out = findViewById(R.id.cardExist);

        if (isLocationEnabled(this)) {


            markAttend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(UserHomeActivity.this, MarkAttendanceActivity.class));
                }
            });

            out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(UserHomeActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }
        else {
            Toast.makeText(this, "GPS is OFF", Toast.LENGTH_SHORT).show();
        }

    }


    // to check GPS  is ON or OFF
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(context, "GPS is OFF", Toast.LENGTH_SHORT).show();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }
}