package com.example.administrator.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.widget.Button;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQ_GET_UESRINFO = 1;
    static final int REQ_LOGIN = 2;

    String loginID, loginPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((Toolbar) findViewById(R.id.toolbar)).setLogo(R.mipmap.gallogo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView center = (ImageView) findViewById(R.id.imageView3);

        View user_info = (View) findViewById(R.id.items_table);
        user_info.setVisibility(View.INVISIBLE);

        //버튼 이벤트 등록
        BtnOnClickListener onClickListener = new BtnOnClickListener() ;
        Button membership = (Button) findViewById(R.id.membership) ;
        membership.setOnClickListener(onClickListener) ;

        Button lesson = (Button) findViewById(R.id.lesson) ;
        lesson.setOnClickListener(onClickListener) ;

        Button event = (Button) findViewById(R.id.event) ;
        event.setOnClickListener(onClickListener) ;

        Button coupon = (Button) findViewById(R.id.coupon) ;
        coupon.setOnClickListener(onClickListener) ;

        Button login_out_btn = (Button) findViewById(R.id.login_out_btn) ;
        login_out_btn.setOnClickListener(onClickListener) ;


//Login Popup
        SharedPreferences auto = getSharedPreferences("auto", AppCompatActivity.MODE_PRIVATE);

        loginID = auto.getString("userID", null);
        loginPwd = auto.getString("userPwd", null);
        Button login_logout = (Button) findViewById(R.id.login_out_btn);

        ImageView barcode_image = (ImageView) findViewById(R.id.imageView2);
        TextView barcode_none = (TextView) findViewById(R.id.barcode_none);

        if(loginID != null && loginPwd != null)
        {
            login_logout.setText("Logout");
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class) ;
            intent.putExtra("loginID", loginID);
            intent.putExtra("loginPwd", loginPwd);
            Log.d("hyeonsu", "loginID Main : " + loginID);
            Log.d("hyeonsu", "loginPwd Main : " + loginPwd);

            center.getBackground().setAlpha(153);

            user_info.setVisibility(View.VISIBLE);

            barcode_image.setVisibility(View.VISIBLE);
            barcode_none.setVisibility(View.INVISIBLE);

            TextView duration_word = (TextView) findViewById(R.id.duration_word);
            TextView pro_word = (TextView) findViewById(R.id.pro_word);
            TextView avail_word = (TextView) findViewById(R.id.avail_word);

            duration_word.setText("기간");
            pro_word.setText("프로");
            avail_word.setText("레슨현황");

            startActivityForResult(intent, REQ_GET_UESRINFO);

            TextView user_id_display = (TextView) findViewById(R.id.user_id_display) ;
            Button logout_btn = (Button) findViewById(R.id.login_out_btn);

            if(user_id_display.getHeight() != logout_btn.getHeight())
            {
                logout_btn.setHeight(user_id_display.getHeight());
            }

            user_id_display.setText(loginID + " 님의 접속을 환영합니다.");
            Toast.makeText(getApplicationContext(), "자동 로그인 성공 : " + loginID, Toast.LENGTH_LONG).show();
        }
        else
        {
            user_info.setVisibility(View.INVISIBLE);

            barcode_image.setVisibility(View.INVISIBLE);
            barcode_none.setVisibility(View.VISIBLE);

            center.getBackground().setAlpha(255);

            TextView user_id_display = (TextView) findViewById(R.id.user_id_display) ;
            user_id_display.setText("");

            login_logout.setText("Login");
        }
//

//User Package Info 가져오기, Test용
        /*
        Button button1 = (Button) findViewById(R.id.button1) ;
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class) ;
                Log.d("hyeonsu", "setOnClickListener" );
                intent.putExtra("loginID", loginID);
                intent.putExtra("loginPwd", loginPwd);
                startActivityForResult(intent, REQ_GET_UESRINFO);
            }
        });
        */

//

    }

    //Button 처리 루틴

    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d("hyeonsu", "view.getId() : " + view.getId());
            switch (view.getId()) {
                //상위 메뉴
                case R.id.membership :
                    Toast.makeText(getApplicationContext(), "멤버쉽 구현중", Toast.LENGTH_LONG).show();
                    break ;
                case R.id.lesson :
                    Toast.makeText(getApplicationContext(), "레슨예약 구현중", Toast.LENGTH_LONG).show();
                    break ;
                case R.id.event :
                    Toast.makeText(getApplicationContext(), "이벤트 구현중", Toast.LENGTH_LONG).show();
                    break ;
                case R.id.coupon:
                    Toast.makeText(getApplicationContext(), "쿠폰 구현중", Toast.LENGTH_LONG).show();
                    break;
                case R.id.login_out_btn:
                    Button login_out = (Button) findViewById(R.id.login_out_btn);
                    ImageView center = (ImageView) findViewById(R.id.imageView3);

                    ImageView barcode_image = (ImageView) findViewById(R.id.imageView2);
                    TextView barcode_none = (TextView) findViewById(R.id.barcode_none);

                    //로그아웃
                    if(login_out.getText()=="Logout")
                    {
                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(intent);
                        SharedPreferences auto = getSharedPreferences("auto", AppCompatActivity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = auto.edit();

                        editor.clear();
                        editor.commit();

                        login_out.setText("Login");

                        View user_info = (View) findViewById(R.id.items_table);
                        user_info.setVisibility(View.INVISIBLE);

                        barcode_image.setVisibility(View.INVISIBLE);
                        barcode_none.setVisibility(View.VISIBLE);

                        center.getBackground().setAlpha(255);

                        TextView user_id_display = (TextView) findViewById(R.id.user_id_display) ;
                        user_id_display.setText("");

                        Toast.makeText(getApplicationContext(), "로그아웃.", Toast.LENGTH_SHORT).show();
                    }
                    //로그인
                    else if(login_out.getText()=="Login")
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginPopupActivity.class);
                        intent.putExtra("data", "Test Popup");
                        startActivityForResult(intent, REQ_LOGIN);
                    }
            }
        }
    }

//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView center = (ImageView) findViewById(R.id.imageView3);

        ImageView barcode_image = (ImageView) findViewById(R.id.imageView2);
        TextView barcode_none = (TextView) findViewById(R.id.barcode_none);

        if(requestCode == REQ_GET_UESRINFO)
        {
            if(resultCode == RESULT_OK)
            {
                TextView package_name = (TextView) findViewById(R.id.v_package_name) ;
                String pack_name = data.getStringExtra("package_name");

                if(pack_name != null) {
                    package_name.setText(pack_name);
                }

                TextView duration = (TextView) findViewById(R.id.v_pack_duration) ;
                String set_duration = data.getStringExtra("duration");

                if(set_duration != null) {
                    duration.setText(set_duration);
                }

                TextView coach_name = (TextView) findViewById(R.id.v_coach) ;
                String set_coach_name = data.getStringExtra("coach");

                if(set_coach_name != null) {
                    coach_name.setText(set_coach_name);
                }

                TextView avail_count = (TextView) findViewById(R.id.v_avail_count) ;
                String set_avail_count = data.getStringExtra("avail_count");

                if(set_avail_count != null) {
                    avail_count.setText(set_avail_count);
                }

                Log.d("hyeonsu", "pack_name : " + pack_name);
                Log.d("hyeonsu", "v_pack_duration : " + set_duration);
                Log.d("hyeonsu", "set_coach_name : " + set_coach_name);
                Log.d("hyeonsu", "set_avail_count : " + set_avail_count);
            }
        }
        else if(requestCode == REQ_LOGIN)
        {
            if(resultCode==RESULT_OK)
            {
                //데이터 받기
                String userID = data.getStringExtra("userID");
                String userPwd = data.getStringExtra("userPwd");
                Boolean autoFlag = data.getBooleanExtra("autoLogin", false);

                View user_info = (View) findViewById(R.id.items_table);

                TextView user_id_display = (TextView) findViewById(R.id.user_id_display) ;
                Button logout_btn = (Button) findViewById(R.id.login_out_btn);

                if(user_id_display.getHeight() != logout_btn.getHeight())
                {
                    logout_btn.setHeight(user_id_display.getHeight());
                }

                center.getBackground().setAlpha(153);

                user_info.setVisibility(View.VISIBLE);

                barcode_image.setVisibility(View.VISIBLE);
                barcode_none.setVisibility(View.INVISIBLE);

                user_id_display.setText(userID + " 님의 접속을 환영합니다.");
                logout_btn.setText("Logout");

                Log.d("hyeonsu", "user_id_display.getHeight : " + user_id_display.getHeight());
                Log.d("hyeonsu", "logout_btn.getHeight() : " + logout_btn.getHeight());
                Log.d("hyeonsu", "userID : " + userID);
                Log.d("hyeonsu", "userPwd : " + userPwd);
                Log.d("hyeonsu", "autoLogin : " + autoFlag);

                if(autoFlag)
                {
                    SharedPreferences auto = getSharedPreferences("auto", AppCompatActivity.MODE_PRIVATE);
                    SharedPreferences.Editor autoLogin = auto.edit();

                    autoLogin.putString("userID", userID);
                    autoLogin.putString("userPwd", userPwd);

                    autoLogin.commit();
                }
                else
                {
                    SharedPreferences auto = getSharedPreferences("auto", AppCompatActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = auto.edit();

                    editor.clear();
                    editor.commit();
                }
                //로그인 정보 확인하는 루틴 추가

                //로그인 ID에 따라 Barcode생성하는 코드 추가

                user_id_display.setVisibility(View.VISIBLE);

                TextView duration_word = (TextView) findViewById(R.id.duration_word);
                TextView pro_word = (TextView) findViewById(R.id.pro_word);
                TextView avail_word = (TextView) findViewById(R.id.avail_word);

                duration_word.setText("기간");
                pro_word.setText("프로");
                avail_word.setText("레슨현황");

                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class) ;
                Log.d("hyeonsu", "setOnClickListener" );
                startActivityForResult(intent, REQ_GET_UESRINFO);

                //Toast.makeText(getApplicationContext(), "로그인 성공 : " + userID, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "ID/Password를 확인하세요", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_1) {
            // 마이 페이지
            Intent mypage_intent = new Intent(MainActivity.this, MypageActivity.class) ;
            startActivity(mypage_intent);

            item.setCheckable(false);

        } else if (id == R.id.menu_2) {
            //레슨예약
        } else if (id == R.id.menu_3) {
            //타석정보
        } else if (id == R.id.menu_4) {
            //공지사항
        } else if (id == R.id.menu_5) {
            //연혁
        } else if (id == R.id.menu_6) {
            //코치진
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
