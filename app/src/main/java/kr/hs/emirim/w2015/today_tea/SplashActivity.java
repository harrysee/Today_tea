package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this::startIntent,1000);    // 1초뒤에 넘어가기
    }

    public void startIntent(){
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        finish();
    }
}