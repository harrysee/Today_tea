package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    Animation anim_FadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ConstraintLayout bg = findViewById(R.id.splash_back);
        ImageView logo = findViewById(R.id.imageView);
//        Handler handler = new Handler();
//        handler.postDelayed(this::startIntent,1000);    // 1초뒤에 넘어가기

        anim_FadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        anim_FadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bg.startAnimation(anim_FadeIn);
        logo.startAnimation(anim_FadeIn);
    }

    public void startIntent(){
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        finish();
    }
}