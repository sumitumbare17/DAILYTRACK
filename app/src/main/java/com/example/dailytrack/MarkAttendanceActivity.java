package com.example.dailytrack;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailytrack.MapsActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.dailytrack.GeofenceBroadcastReceiver;

public class MarkAttendanceActivity extends AppCompatActivity {

    private TextView textViewLat, textViewLong;
    private LocationManager locationManager;

    private Button TurnGPSOn;
    private com.google.android.gms.location.LocationRequest locationRequest;
    private static final int REQUEST_CHECK_SETTINGS = 10001;

    LatLng latLngUser;

    TextView okay_text, cancel_text;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mark_attendance);

            Button btnMark = findViewById(R.id.btnMark);

            textViewLat = findViewById(R.id.edtLat);
            textViewLong = findViewById(R.id.edtLong);


                


                Dialog dialog = new Dialog(MarkAttendanceActivity.this);

                btnMark.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onClick(View view) {

                        if (isLocationEnabled(MarkAttendanceActivity.this)) {

                        if (GeofenceBroadcastReceiver.track != -1) {


                            Toast.makeText(MarkAttendanceActivity.this, "Attendace is marked", Toast.LENGTH_SHORT).show();


                            dialog.setContentView(R.layout.dialog_layout);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(true);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                            okay_text = dialog.findViewById(R.id.okay_text);


                            okay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    // Toast.makeText(MarkAttendanceActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                                }
                            });

                            dialog.show();
                        } else {
                            dialog.setContentView(R.layout.dialog_close_layout);
                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog.setCancelable(true);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                            okay_text = dialog.findViewById(R.id.okay_text);


                            okay_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    // Toast.makeText(MarkAttendanceActivity.this, "okay clicked", Toast.LENGTH_SHORT).show();
                                }
                            });

                            dialog.show();
                        }
                        }
                        else{
                            Toast.makeText(MarkAttendanceActivity.this, "GPS is OFF", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            } catch(Exception e){
                Toast.makeText(this, "On the GPS", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: Error=" + e);
                System.out.println("last " + e);
            }



    }
/*
     @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == REQUEST_CHECK_SETTINGS) {

             switch (resultCode) {
                 case RESULT_OK:
                     Toast.makeText(this, "GPS is tured on", Toast.LENGTH_SHORT).show();
                     break;
                 case RESULT_CANCELED:
                     Toast.makeText(this, "GPS required to be tured on", Toast.LENGTH_SHORT).show();
             }
         }
     }*/



   /* *//*

    try {
                /*if (ContextCompat.checkSelfPermission(MarkAttendanceActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(MarkAttendanceActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MarkAttendanceActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

                }*//*

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.chec
        kSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


    }catch (Exception e) {
        Toast.makeText(this, "Exception is" + e, Toast.LENGTH_SHORT).show();
        System.out.println(e);
        Log.d(TAG, "Exception occured is: "+e);
    }
      */


    // this below code is to check Gps is on or off
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