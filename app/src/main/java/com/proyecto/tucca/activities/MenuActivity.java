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
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.proyecto.tucca.R;
import com.proyecto.tucca.fragments.CardsFragment;
import com.proyecto.tucca.fragments.GapAndFareFragment;
import com.proyecto.tucca.fragments.MainFragment;
import com.proyecto.tucca.fragments.MeFragment;
import com.proyecto.tucca.fragments.SalePointFragment;
import com.proyecto.tucca.fragments.TripFragment;

import static com.proyecto.tucca.activities.MainActivity.login;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String STRING_PREFERENCES = "fragments";
    public static final String PREFERENCE_STATUS = "estado.button.sesion";
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //String newString;
        if(savedInstanceState == null) {
            /*Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("nombre");
                System.out.println(newString);
                TextView textView = findViewById(R.id.text_view_name);
                textView.setText(newString);
            }*/
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }/*else {
            newString= (String) savedInstanceState.getSerializable("nombre");
        }*/
        if(!login) {
            navigationView.getMenu().removeItem(R.id.nav_cards);
            navigationView.getMenu().removeItem(R.id.nav_log);
        }else{
            navigationView.getMenu().removeItem(R.id.nav_sign_up);
        }
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
                new TaskCambiarFragment().execute(new MeFragment());
                /*String newString;
                Bundle extras = getIntent().getExtras();
                if(extras == null) {
                    newString= null;
                } else {
                    newString = extras.getString("nombre");
                    System.out.println(newString);
                }*/
                break;
            case R.id.nav_gap_fare:
                new TaskCambiarFragment().execute(new GapAndFareFragment());
                break;
            case R.id.nav_sign_up:
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
                finish();
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

    class TaskCambiarFragment extends AsyncTask<Fragment, Void, String> {

        @Override
        protected String doInBackground(Fragment... fragments) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragments[0]).commit();
            return fragments[0].toString();
        }
    }
}
