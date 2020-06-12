package com.proyecto.yourmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    int tiempoRefresco = 500;
    int PEDI_PERMISO_GPS = 1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PEDI_PERMISO_GPS);

                return;
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempoRefresco, 0, locationListener);

        if (conexion()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doStuff();
                }
            }, 5000);


        } else {
            AlertDialog.Builder dialogo2 = new AlertDialog.Builder(MainActivity.this);
            dialogo2.setTitle("Error");
            dialogo2.setIcon(R.drawable.out);
            dialogo2.setMessage(R.string.error_servidor);
            dialogo2.setCancelable(false);
            dialogo2.setPositiveButton(R.string.Confirmar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    //aceptar();
                    dialogo1.dismiss();
                    Intent i = new Intent(MainActivity.this, MainActivity.class);

                    startActivity(i);
                }
            });
            dialogo2.show();


        }


    }

    private void doStuff() {
        Intent i = new Intent(MainActivity.this, PrimerActivity.class);
        startActivity(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PEDI_PERMISO_GPS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    return;

                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempoRefresco, 0, locationListener);
                }
            }
        } else {
            Toast.makeText(this, "Debes concederme permiso para usar el GPS!!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean conexion() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
