package kr.hs.emirim.w2015.today_tea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context){
        super(context, "teainfoDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE teainfoDB (teaname TEXT PRIMARY KEY, teaEfficacy TEXT, teaExplan TEXT, mindkey INTEGER, imgkey INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS teainfoDB;");
        onCreate(db);
    }

    public void insertTEA(SQLiteDatabase db, TeaDataClass tea ){
        db.execSQL("INSERT INTO teainfoDB VALUES("+tea.teaName+","+ tea.teaEfficacy+","+ tea.teaExplan+","+ tea.mindkey+","+ tea.teaImg+");");
    }
}
