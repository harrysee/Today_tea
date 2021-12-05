package kr.hs.emirim.w2015.today_tea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences setcheck = getSharedPreferences("setting", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = (ViewPager2) findViewById(R.id.container);

        ViewPagerAdapter viewAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        ArrayList<String> textlist = new ArrayList<>();
        textlist.add("TAE");
        textlist.add("GAIDE");
        textlist.add("TODAYTAE");
        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> tab.setText(textlist.get(position))).attach();

        if(setcheck.getBoolean("firstcheck",true)){
            SharedPreferences.Editor editor = setcheck.edit();
            editor.putBoolean("firstcheck",false);
            editor.apply();
            datasave(this);
        }

    }
    private static void datasave(Activity activity){
        SQLiteDatabase DB;
        SQLiteHelper helper = new SQLiteHelper(activity);
        DB = helper.getWritableDatabase();

        helper.insertTEA(DB, new TeaDataClass("홍차","#심신안정","홍차는 주로 많이 먹곤합니다",1,1));
        helper.insertTEA(DB, new TeaDataClass("녹차","#심신안정","홍차는 주로 많이 먹곤합니다",1,1));
        helper.insertTEA(DB, new TeaDataClass("밀크티","#심신안정","홍차는 주로 많이 먹곤합니다",1,1));
        helper.insertTEA(DB, new TeaDataClass("자스민차","#심신안정","홍차는 주로 많이 먹곤합니다",1,1));
        DB.close();
        helper.close();
    }

    public static class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

}
