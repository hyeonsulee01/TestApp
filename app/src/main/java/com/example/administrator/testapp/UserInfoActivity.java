package com.example.administrator.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent() ;

        String loginID = intent.getStringExtra("loginID");
        String loginPwd = intent.getStringExtra("loginPwd");

        Log.d("hyeonsu", "loginID : " + loginID);
        Log.d("hyeonsu", "loginPwd : " + loginPwd);

        String set_pack_name = "3개월 이용권";
        intent.putExtra("package_name", set_pack_name);

        String set_duration = "1/1 ~ 3/30";
        intent.putExtra("duration", set_duration);

        String set_coach_name = "이중영 프로";
        intent.putExtra("coach", set_coach_name);

        String set_avail_count = "3/24";
        intent.putExtra("avail_count", set_avail_count);

        Log.d("hyeonsu", "set_pack_name : " + set_pack_name);
        setResult(RESULT_OK, intent) ;
        finish();

    }
}
