package kr.hs.emirim.w2015.today_tea;

import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class TeaInputData extends AppCompatActivity {

    public void insertData(){
        SQLiteDatabase DB;
        SQLiteHelper helper = new SQLiteHelper(this);
        DB = helper.getWritableDatabase();

        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다 ㅎ",1,1));
        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다 ㅎ",1,1));
        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다 ㅎ",1,1));
        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다 ㅎ",1,1));
        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다 ㅎ",1,1));
    }
}
