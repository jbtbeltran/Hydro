package com.example.hydro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
                        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                        sendBroadcast(broadcastIntent);
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("com.package.ACTION_LOGOUT");
                        registerReceiver(new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {
                                Log.d("onReceive", "Logout in Progress");
                                show(LoginRealActivity.class);
                                finish();
                            }
                        }, intentFilter);
//                     Todo: logout function here
                        break;
                }
                return false;
            }
        });
    }
}
