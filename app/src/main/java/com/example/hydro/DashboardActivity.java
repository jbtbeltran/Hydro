package com.example.hydro;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hydro.model.sensor.PhLevel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends BaseActivity {
    TextView textPhLevel;


    @Override
    protected void setCustomContentView() {
        setContentView(R.layout.activity_dashboard);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textPhLevel = findViewById(R.id.textViewPHLv);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("/sensors/ph_level");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PhLevel phLevel = snapshot.getValue(PhLevel.class);
                textPhLevel.setText(textPhLevel.getText() + " " +  phLevel.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });


    }


}