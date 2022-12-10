package com.example.hydro;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hydro.util.IntentUtil;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseActivity extends AppCompatActivity {

    protected void show(Class<?> cls) {
        startActivity(IntentUtil.createIntent(this, cls));
    }

    protected abstract void setCustomContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setCustomContentView();
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
                        show(DashboardActivity.class);
                        break;
                    case R.id.nav_sched:
                        show(SchedulerActivity.class);
                        break;
                    case R.id.nav_report:
                        show(ReportActivity.class);
                        break;
                    case R.id.nav_admin_mode:
                        show(LoginActivity.class);
                        break;
                    case R.id.nav_settings:
                        show(SettingsActivity.class);
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
