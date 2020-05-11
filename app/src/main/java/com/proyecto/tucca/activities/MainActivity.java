package com.proyecto.tucca.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.proyecto.tucca.fragments.CardsFragment;
import com.proyecto.tucca.fragments.LoginFragment;
import com.proyecto.tucca.fragments.MainFragment;
import com.proyecto.tucca.R;
import com.proyecto.tucca.fragments.MeFragment;
import com.proyecto.tucca.fragments.SalePointFragment;
import com.proyecto.tucca.fragments.SettingsFragment;
import com.proyecto.tucca.fragments.TripFragment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.proyecto.tucca.fragments.LoginFragment.login;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    /*public static Socket cliente;
    public static DataOutputStream dataOut;
    public static DataInputStream dataIn;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        //conectar();
        //new TaskConectar().execute();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                new TaskCambiarFragment().execute(new MainFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;
            case R.id.nav_trip:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripFragment()).commit();
                break;
            case R.id.nav_cards:
                new TaskCambiarFragment().execute(new CardsFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CardsFragment()).commit();
                break;
            case R.id.nav_sales:
                new TaskCambiarFragment().execute(new SalePointFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SalePointFragment()).commit();
                break;
            case R.id.nav_log:
                if(login){
                    new TaskCambiarFragment().execute(new MeFragment());
                }else{
                    new TaskCambiarFragment().execute(new LoginFragment());
                }
                break;
            case R.id.nav_settings:
                new TaskCambiarFragment().execute(new SettingsFragment());
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    /*private void conectar(){
        final int PUERTO = 6000;
        final String HOST = "192.168.1.13";
        //"localhost";
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                cliente = new Socket(HOST, PUERTO);
                dataOut = new DataOutputStream(cliente.getOutputStream());
                dataIn = new DataInputStream(cliente.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(LoginFragment.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }*/

    class TaskCambiarFragment extends AsyncTask<Fragment, Void, String>{

        @Override
        protected String doInBackground(Fragment... fragments) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragments[0]).commit();
            return fragments[0].toString();
        }
    }
}
