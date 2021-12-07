package kr.hs.emirim.w2015.today_tea;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class ThirdFragment extends Fragment {
    SQLiteHelper myHelper;
    SQLiteDatabase rsqlDB, wsqlDB;
    Cursor cursor;
    ArrayList<TeaDataClass> tealist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout feel1 = view.findViewById(R.id.feel1);
        LinearLayout feel2 = view.findViewById(R.id.feel2);
        LinearLayout feel3 = view.findViewById(R.id.feel3);
        LinearLayout feel4 = view.findViewById(R.id.feel4);

        feel1.setOnClickListener(FeelOnclickListener);
        feel2.setOnClickListener(FeelOnclickListener);
        feel3.setOnClickListener(FeelOnclickListener);
        feel4.setOnClickListener(FeelOnclickListener);

        myHelper = new SQLiteHelper(getContext());
        rsqlDB = myHelper.getReadableDatabase();    // 디비 읽기
        wsqlDB = myHelper.getWritableDatabase();    // 디비수정
        cursor = rsqlDB.rawQuery("SELECT * FROM teainfoDB;",null);
        super.onViewCreated(view, savedInstanceState);

        tealist = new ArrayList<>();
        while(cursor.moveToNext()){     // teaName, teaEfficacy, teaExplan, teaImg, mindkey
            tealist.add(new TeaDataClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5)));
        }

        cursor.close();
        rsqlDB.close();
        wsqlDB.close();
        myHelper.close();

    }

    View.OnClickListener FeelOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Random randomNum = new Random();
            ArrayList<TeaDataClass> feelList = new ArrayList<>();
            int clickfeel =1;
            switch (v.getId()){
                case R.id.feel1:
                    clickfeel = 1;
                    // 랜덤 1
                    break;
                case R.id.feel2:
                    // 랜덤 2
                    clickfeel = 2;
                    break;
                case R.id.feel3:
                    clickfeel = 3;
                    // 랜덤 3
                    break;
                case R.id.feel4:
                    clickfeel = 4;
                    // 랜덤 4
                    break;
            }

            for(int i = 0; i<tealist.size(); i++) {
                if (tealist.get(i).mindkey == clickfeel) {
                    feelList.add(tealist.get(i));
                }
            }
            int randomIndex = (randomNum.nextInt(feelList.size()));
            Intent intent = new Intent(getActivity(), RandomTeaActivity.class);
            intent.putExtra("chooseTea", feelList.get(randomIndex).teaName); // 선택한 티 이름
            intent.putExtra("clickFeelNum",clickfeel);      // 클릭한 버튼 키
            intent.putExtra("efficacy",feelList.get(randomIndex).teaEfficacy);
            intent.putExtra("explan",feelList.get(randomIndex).teaExplan);
            intent.putExtra("url",feelList.get(randomIndex).teaUri);
            intent.putExtra("imgSrc",feelList.get(randomIndex).teaImg);
            startActivity(intent);
        }
    };
}