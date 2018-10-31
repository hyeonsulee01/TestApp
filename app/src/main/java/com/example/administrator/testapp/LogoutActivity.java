package com.example.administrator.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.Intent;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button logout_btn = (Button) findViewById(R.id.login_out_btn);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                SharedPreferences auto = getSharedPreferences("auto", AppCompatActivity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();

                editor.clear();
                editor.commit();

                logout_btn.setText("Login");
                Toast.makeText(getApplicationContext(), "로그아웃.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
