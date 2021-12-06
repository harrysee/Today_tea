package kr.hs.emirim.w2015.today_tea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TeaFragment extends Fragment {
    SQLiteHelper myHelper;
    SQLiteDatabase rsqlDB, wsqlDB;
    Cursor cursor;
    Cursor searchCursor;
    EditText searchEdit;
    ImageView searchBtn;
    ArrayList<TeaDataClass> teas;
    GridView gridView;
    GridAdapter gridAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tea, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        myHelper = new SQLiteHelper(getContext());
        rsqlDB = myHelper.getReadableDatabase();    // 디비 읽기
        wsqlDB = myHelper.getWritableDatabase();    // 디비수정
        cursor = rsqlDB.rawQuery("SELECT * FROM teainfoDB;",null);
        super.onViewCreated(view, savedInstanceState);
        searchEdit = view.findViewById(R.id.search_edit);
        searchBtn = view.findViewById(R.id.search_btn);

        teas = new ArrayList<>();
        while(cursor.moveToNext()){
            teas.add(new TeaDataClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4)));
        }

        gridView = view.findViewById(R.id.tea_grid);
        gridAdapter = new GridAdapter(teas,getActivity());

        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {     // 각 아이템 클릭시
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),teas.get(position).teaName+"을 클릭했습니다",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("name",teas.get(position).teaName);
                intent.putExtra("efficacy",teas.get(position).teaEfficacy);
                intent.putExtra("explan",teas.get(position).teaExplan);
//                intent.putExtra("imgSrc",teas.get(position).teaImg);
                // 설명, 링크 추가
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(searchBtnClickListener);

    }

    View.OnClickListener searchBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String word = searchEdit.getText().toString();
            searchCursor = rsqlDB.rawQuery("SELECT * FROM teainfoDB WHERE teaname LIKE %"+ word +"%;",null);
            teas = new ArrayList<>();
            while(searchCursor.moveToNext()){
                teas.add(new TeaDataClass(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4)));
            }
            gridAdapter.notifyDataSetChanged();
        }
    };


}