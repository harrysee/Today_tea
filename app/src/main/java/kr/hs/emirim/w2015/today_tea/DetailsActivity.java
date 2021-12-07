package kr.hs.emirim.w2015.today_tea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    ImageView teaimgview;
    int ImageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // 데이터 가져오기
        Intent intent = getIntent();
        String teaname = intent.getStringExtra("name");
        String teaex = intent.getStringExtra("explan");
        String teaeffica = intent.getStringExtra("efficacy");
        String teauri = intent.getStringExtra("url");
        ImageNum = intent.getIntExtra("imgSrc",1);  // 이미지번호 가져오기
        Log.i(ImageNum+"", "onItemClick: 받음");

        int teaimg = intent.getIntExtra("imgSrc",R.drawable.logo);
        TextView teaname_view = findViewById(R.id.detail_tea_name);
        TextView teaefficacy_view = findViewById(R.id.detail_tea_efficacy);
        TextView teaexplan_view = findViewById(R.id.detail_tea_explan);
        teaimgview = findViewById(R.id.detail_tea_img);
        ImageView backbtn = findViewById(R.id.details_back_btn);
        Button linkbtn = findViewById(R.id.link_btn);
        Button eatbtn = findViewById(R.id.eat_btn);

        // 뒤로가기
        backbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DetailsActivity.this,
                        Pair.create(findViewById(R.id.detail_tea_img),"imageTransition"),
                        Pair.create(findViewById(R.id.detail_tea_name),"nameTransition"),
                        Pair.create(findViewById(R.id.detail_tea_efficacy),"efficTransition")
                );
                finish();
            }
        });

        // 차 링크 열기
        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(teauri));   //이동시키기
                startActivity(intent);
            }
        });

        // 데이터 설정
        teaname_view.setText(teaname);
        teaefficacy_view.setText(teaeffica);
        teaexplan_view.setText(teaex);
        setTeaImage();
    }
    public void setTeaImage(){
        // 스위치 이미지번호로 이미지 넣기
        switch (ImageNum){
            case 1:
                teaimgview.setImageResource(R.drawable.yellow);
                break;
            case 2:
                teaimgview.setImageResource(R.drawable.red);
                break;
            case 3:
                teaimgview.setImageResource(R.drawable.green);
                break;
            case 4:
                teaimgview.setImageResource(R.drawable.orange);
                break;
            case 5:
                teaimgview.setImageResource(R.drawable.blue);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_exit);
    }
}