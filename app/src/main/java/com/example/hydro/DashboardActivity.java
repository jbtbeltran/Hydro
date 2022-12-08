package com.example.hydro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
//side panel and navigation
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
       NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent home = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_sched:
                        Intent sched = new Intent(DashboardActivity.this, SchedulerActivity.class);
                        startActivity(sched);
                        break;
                    case R.id.nav_report:
                        Intent report = new Intent(DashboardActivity.this, ReportActivity.class);
                        startActivity(report);
                        break;
                    case R.id.nav_admin_mode:
                        Intent admin_mode = new Intent(DashboardActivity.this, LoginActivity.class);
                        startActivity(admin_mode);
                        break;
                    case R.id.nav_settings:
                        Intent setting = new Intent(DashboardActivity.this, SettingsActivity.class);
                        startActivity(setting);
                        break;
                    case R.id.nav_logout:
//                     logout function here
                        break;
                }
                return false;
            }
        });
    }

}