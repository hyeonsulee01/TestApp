package com.example.administrator.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPopupActivity extends AppCompatActivity {

    public EditText user_id, user_password;
    public CheckBox autoLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        user_id = (EditText) findViewById(R.id.user_id);
        user_password = (EditText) findViewById(R.id.user_password);
        autoLogin = (CheckBox) findViewById(R.id.autoLogin);

        Button login = (Button) findViewById(R.id.loginBtn) ;

/*
        if(user_id.getText().toString().length() != 0 && user_password.getText().toString().length() != 0)
        {
            login.setEnabled(true);
        }
        else
        {
            login.setEnabled(false);
        }
*/
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    String userID = user_id.getText().toString();
                    String userPwd = user_password.getText().toString();
                    Boolean autoFlag = autoLogin.isChecked();

                    Intent intent = new Intent();
                    intent.putExtra("userID", userID);
                    intent.putExtra("userPwd", userPwd);
                    intent.putExtra("autoLogin", autoFlag);

                    if(user_id.getText().toString().length() != 0 && user_password.getText().toString().length() != 0)
                    {
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "ID/Password를 확인하세요", Toast.LENGTH_LONG).show();
                        setResult(RESULT_CANCELED, intent);
                    }

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
