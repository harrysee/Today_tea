package kr.hs.emirim.w2015.today_tea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

class SqlRvHelper extends SQLiteOpenHelper {
    public SqlRvHelper(Context context){
        super(context, "teaReview", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE teaReview (teaname TEXT PRIMARY KEY, teaExplan VARCHAR(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS teaReview;");
        onCreate(db);
    }

    public void insertTEA(SQLiteDatabase db, ListDataClass review ){
        db.execSQL("INSERT INTO teaReview VALUES('"+review.getTitle()+"', '"+ review.getText()+"' );");
    }
    public void deleteTEA(SQLiteDatabase db, String name ){
        db.execSQL("DELETE FROM teaReview WHERE teaname = '"+ name +"';");
    }
}
