package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RandomTeaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_tea);
        TextView nametext = findViewById(R.id.random_tea_name);
        TextView explanText = findViewById(R.id.random_tea_explan);
        ImageView back_img = findViewById(R.id.random_back_img);

        Intent intent = getIntent();
        String teaName = intent.getStringExtra("chooseTea");
        int clickmind = intent.getIntExtra("clickFeelNum",1);

        nametext.setText(teaName);
        //클릭한 마인드버튼에 따라서 설명글 다르게 하기

        back_img.setOnClickListener(new View.OnClickListener() {    //클릭하면 돌아가기
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}