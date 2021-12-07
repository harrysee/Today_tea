package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button linkbtn = findViewById(R.id.detail_link_btn);

        // 데이터 넘겨받기
        Intent intent = getIntent();
        String teaName = intent.getStringExtra("chooseTea");
        int clickmind = intent.getIntExtra("clickFeelNum",1);
        String teaex = intent.getStringExtra("explan");
        String teaeffica = intent.getStringExtra("efficacy");
        String teauri = intent.getStringExtra("url");
        int imageNum = intent.getIntExtra("imageSrc",1);

        nametext.setText(teaName);
        //클릭한 마인드버튼에 따라서 설명글 다르게 하기
        switch (clickmind){
            case 1:
                explanText.setText("즐거운 일이 있나봐요 긍정적인 마음을 유지하면서도 잠시 차분해지고 싶을땐 "+teaName+" 차를 드셔보세요\n"+teaex);
                break;
            case 2:
                explanText.setText("우울한 일이 있었나보네요.. 부정적인 마음을 다스리고 고요한 마음을 되찾기 위해 "+teaName+" 차를 드셔보세요\n"+teaex);
                break;
            case 3:
                explanText.setText("심심하고 지루하시죠 텐션을 높이고 집중력을 높이기 위해 "+teaName+" 차를 드셔보세요\n"+teaex);
                break;
            case 4:
                explanText.setText("분노가 참아지지 않나요? 마음을 다스리고 화를 가라앉히기 위해 "+teaName+" 차를 드셔보세요\n"+teaex);
                break;
        }

        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       // 상세보기 넘어가기
                Intent intent = new Intent(RandomTeaActivity.this, DetailsActivity.class);
                intent.putExtra("name",teaName);
                intent.putExtra("efficacy",teaeffica);
                intent.putExtra("explan",teaex);
                intent.putExtra("url",teauri);
                intent.putExtra("imgSrc",imageNum);
                startActivity(intent);
            }
        });

        back_img.setOnClickListener(new View.OnClickListener() {    //클릭하면 돌아가기
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}