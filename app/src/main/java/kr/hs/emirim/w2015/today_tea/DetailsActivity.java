package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // 데이터 가져오기
        Intent intent = getIntent();
        String teaname = intent.getStringExtra("name");
        String teaeffica = intent.getStringExtra("explan");
        int teaimg = intent.getIntExtra("imgSrc",R.drawable.logo);
        TextView teaname_view = findViewById(R.id.detail_tea_name);
        TextView teaefficacy_view = findViewById(R.id.detail_tea_efficacy);
        TextView teaexplan_view = findViewById(R.id.detail_tea_explan);
        ImageView teaimgview = findViewById(R.id.detail_tea_img);
        ImageView backbtn = findViewById(R.id.details_back_btn);

        // 뒤로가기
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 데이터 설정
        teaname_view.setText(teaname);
        teaefficacy_view.setText(teaeffica);
//        teaexplan_view.setText(teaex);
        teaimgview.setImageResource(teaimg);

    }
}