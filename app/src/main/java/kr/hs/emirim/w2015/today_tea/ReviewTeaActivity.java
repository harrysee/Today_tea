package kr.hs.emirim.w2015.today_tea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReviewTeaActivity extends AppCompatActivity {
    SqlRvHelper myHelper;
    SQLiteDatabase rsqlDB;
    Cursor cursor;
    ArrayList<ListDataClass> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_tea);
        SQLiteDatabase DB;
        SqlRvHelper helper = new SqlRvHelper(ReviewTeaActivity.this);
        DB = helper.getWritableDatabase();
        myHelper = new SqlRvHelper(ReviewTeaActivity.this);
        rsqlDB = myHelper.getReadableDatabase();    // 디비 읽기
        cursor = rsqlDB.rawQuery("SELECT * FROM teaReview;",null);

        ImageView back_img = findViewById(R.id.add_back_img);
        back_img.setOnClickListener(new View.OnClickListener() {    //클릭하면 돌아가기
            @Override
            public void onClick(View v) {
                myHelper.close();
                DB.close();
                rsqlDB.close();
                cursor.close();
                finish();
            }
        });
        // ListView 생성
        ListView listView = findViewById(R.id.add_list);
        items = new ArrayList<>();

        setItems();
        // 만들어진 item 리스트로 Aapter 생성
        ListView_Adapter mAdapter = new ListView_Adapter(this, items);
        listView.setAdapter(mAdapter);      // ListView에 Adapter 연결

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alt_info = new AlertDialog.Builder(ReviewTeaActivity.this,R.style.MyAlertDialogStyle);
                alt_info.setTitle("삭제하기")
                        .setMessage("진짜 삭제?")
                        .setIcon(R.drawable.logo)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myHelper.deleteTEA(DB, items.get(position).getTitle());
                                setItems();
                                mAdapter.update(items);
                            }
                        });
                AlertDialog alert = alt_info.create();
                alert.show();

                return false;
            }
        });
    }
    public void setItems(){
        // 디비에서 가져오기
        while(cursor.moveToNext()){
            items.add(new ListDataClass(cursor.getString(0),cursor.getString(1)));
        }
    }
}