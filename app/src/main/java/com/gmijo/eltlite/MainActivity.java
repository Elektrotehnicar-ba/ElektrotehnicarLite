package com.gmijo.eltlite;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pocetna, R.id.navigation_pretvaraci, R.id.navigation_komponente, R.id.navigation_forum)
                .build();
        provjeriinternet();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //Osvjezi BTN kada konekcija == fail
        Button reload = findViewById(R.id.button_osvjezi);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                provjeriinternet();
            }
        });

    }

    //Provjera da li je slucajno prtisinuo back
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Da li ste sigurni da želite izaći?")
                .setCancelable(false)
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Ne", null)
                .show();
    }
    public  void provjeriinternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo podatci = connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI);
        View fragment = findViewById(R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        RelativeLayout relativeLayout = findViewById(R.id.konekcija);
        ActionBar actionBar = getSupportActionBar();
        if (podatci.isConnected()) {
            fragment.setVisibility(View.VISIBLE);
            bottomNavigationView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            assert actionBar != null;
            actionBar.show();
        } else if (wifi.isConnected()) {
            fragment.setVisibility(View.VISIBLE);
            bottomNavigationView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            assert actionBar != null;
            actionBar.show();
        } else {
            fragment.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            assert actionBar != null;
            actionBar.hide();
        }
    }

}