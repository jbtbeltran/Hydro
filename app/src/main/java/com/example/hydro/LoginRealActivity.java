package com.example.hydro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hydro.model.AquaMessageResponse;
import com.example.hydro.model.LoginParameter;
import com.example.hydro.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginRealActivity extends AppCompatActivity {

    EditText txtBoxUserName, txtBoxPass;
    Button loginBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_real);

        sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        txtBoxUserName = findViewById(R.id.txtBoxUsername);
        txtBoxPass = findViewById(R.id.txtBoxPassword);
        loginBtn = findViewById(R.id.btnLogin);

        if (sharedPreferences.contains("isLogIn")) {
            Intent dashboard = new Intent(LoginRealActivity.this, DashboardActivity.class);
            startActivity(dashboard);
        } else {


            loginBtn.setOnClickListener(view -> {

                editor.putBoolean("isLogIn",true);
                editor.commit();

                LoginParameter loginParameter = new LoginParameter();

                loginParameter.setUsername(txtBoxUserName.getText().toString());
                loginParameter.setPassword(txtBoxPass.getText().toString());

                LoginService loginService = retrofit().create(LoginService.class);

                loginService.login(loginParameter).enqueue(new Callback<AquaMessageResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<AquaMessageResponse> call, @NonNull Response<AquaMessageResponse> response) {

                        if (response.code() == 200) {
                            Intent dashboard = new Intent(LoginRealActivity.this, DashboardActivity.class);
                            startActivity(dashboard);
                        } else {
                            Toast.makeText(getApplicationContext(), "Username and Password Invalid", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AquaMessageResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Username and Password Invalid", Toast.LENGTH_SHORT).show();
                    }
                });


            });
        }

    }



    private Retrofit retrofit() {
//        replace the ip address with your ip address
//        ex. http://your ip address:8080/Aqua/
        return new Retrofit.Builder()
                .baseUrl("http://192.168.100.187:8080/aqua/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}