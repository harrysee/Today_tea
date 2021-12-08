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
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharepreference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharepreference = PreferenceManager.getDefaultSharedPreferences(this);
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

        if(sharepreference.getBoolean("firstcheck",true)){
            SharedPreferences.Editor editor = sharepreference.edit();
            editor.putBoolean("firstcheck",false);
            editor.apply();
            datasave(this);
        }
    }
    private static void datasave(Activity activity){
        SQLiteDatabase DB;
        SQLiteHelper helper = new SQLiteHelper(activity);
        DB = helper.getWritableDatabase();

        // 노랑 1, 빨강 2, 녹색 3, 주황 4, 투명 5
        // 즐거움, 우울, 지루, 화
        helper.insertTEA(DB, new TeaDataClass("라벤더","#심신안정#수면장애_개선","라벤더에는 신경을 안정시켜주는 성분인 리나릴 아세테이트 성분이 함유되어 있어 차를 마시거나 향을 맡게 되면 불안증세를 호전시키고, 편하게 해주며 이를 통해 수면의 질을 향상 시키고 싶을 때나, 심신의 안정을 취하고 싶을 때 도움이 된다.",1,2,"https://s.lotteon.com/KaMcPcV_D"));
        helper.insertTEA(DB, new TeaDataClass("루이보스","#혈압조절","루이보스에 들어있는 성분이 앤지오텐신 전환 효소를 억제함으로써 혈압에 유익한 효과를 줄 수 있습니다. ",4,2,"http://auction.kr/iCFyVY9"));
        helper.insertTEA(DB, new TeaDataClass("얼그레이","#스트레스완화#불안완화","찻잎에 입혀진 베르가못 향은 진정효과가 있어 아로마 테라피에 사용되고 있으며, 스트레스, 우울, 불안 등을 줄이고 싶을 때, 얼그레이 차를 마시면 효과가 있습니다.",4,2,"https://smartstore.naver.com/twinings/products/5128045833"));
        helper.insertTEA(DB, new TeaDataClass("캐모마일","#근육이완#신체내부스트레스완화","캐모마일 차는 긴장되고 스트레스 받은 몸을 이완시켜주며, 스트레스성 질환에 시달릴 때, 캐모마일 차를 마시면 도움이 됩니다.",1,3,"https://smartstore.naver.com/twinings/products/5129469524"));
        helper.insertTEA(DB, new TeaDataClass("페퍼민트","#피로회복","눈이 피로할 때나, 침침할때, 머리가 지끈거릴 때 페퍼민트 차를 마시면 도움 받을 수 있습니다.",3,3,"https://smartstore.naver.com/twinings/products/5129474465"));
        helper.insertTEA(DB, new TeaDataClass("히비스커스","#피로회복","눈이 피로할 때나 비타민C 보충이 필요할 때, 히비스커스 차를 마시면 도움 받을 수 있습니다.",2,3,"https://smartstore.naver.com/teamaid/products/2974431427?NaPm=ct%3Dkwvrkx9s%7Cci%3Dc1047270899687cd93404dbab77afdcc8feb59f4%7Ctr%3Dslbrc%7Csn%3D613008%7Chk%3Deb8dfffa0fb0e3fdbe4a5e9a9b4ab1080ae23089"));
        helper.insertTEA(DB, new TeaDataClass("블루멜로우","#염증_완화","당아욱은 빈혈과 골다공증, 기관지염, 인후통과 같은 염증 이뇨작용을 원활하게 하며, 림프절결핵에 효능이 있다. 또한 레몬즙과 같은 산성 물질과 만나게 되면 수색이 파란색에서 붉은색으로 변한다.",5,1,"http://auction.kr/iBxKSBZ"));
        helper.insertTEA(DB, new TeaDataClass("국화","#스트레스_완화#혈압조절","국화는 스트레스 완화와 안정을 돕고, 혈압을 낮출 수 있으며, 숙면을 도울 수 있습니다.",1,3,"https://smartstore.naver.com/handsherb/products/414544109"));
        helper.insertTEA(DB, new TeaDataClass("맨드라미","#염증완화","맨드라미는 눈 충혈, 심장 활동, 관절 염증 통증 완화에 좋으며, 특히 여성질환인 월경통과 자궁염, 자궁출혈, 냉증 등에 도움이 된다.",2,3,"https://smartstore.naver.com/leelora/products/742139644"));
        helper.insertTEA(DB, new TeaDataClass("매화","#혈액순환","매화는 혈액순환에 도움을 줘 체온유지에 좋으며, 잘못된 식습관과 음주 등으로 위 관련 질환을 걱정하는 사람들도 매화꽃차를 꾸준히 마신다면 위를 보호하는 효능으로 위 관련 질환 예방에 효과가 있을 수 있다.",1,4,"https://smartstore.naver.com/leelora/products/742135756"));
        helper.insertTEA(DB, new TeaDataClass("mindle","#혈액순환","매화는 혈액순환에 도움을 줘 체온유지에 좋으며, 잘못된 식습관과 음주 등으로 위 관련 질환을 걱정하는 사람들도 매화꽃차를 꾸준히 마신다면 위를 보호하는 효능으로 위 관련 질환 예방에 효과가 있을 수 있다.",1,4,"https://smartstore.naver.com/leelora/products/742135756"));
        helper.insertTEA(DB, new TeaDataClass("rose","#혈액순환","매화는 혈액순환에 도움을 줘 체온유지에 좋으며, 잘못된 식습관과 음주 등으로 위 관련 질환을 걱정하는 사람들도 매화꽃차를 꾸준히 마신다면 위를 보호하는 효능으로 위 관련 질환 예방에 효과가 있을 수 있다.",1,4,"https://smartstore.naver.com/leelora/products/742135756"));
        helper.insertTEA(DB, new TeaDataClass("dongonal","#혈액순환","매화는 혈액순환에 도움을 줘 체온유지에 좋으며, 잘못된 식습관과 음주 등으로 위 관련 질환을 걱정하는 사람들도 매화꽃차를 꾸준히 마신다면 위를 보호하는 효능으로 위 관련 질환 예방에 효과가 있을 수 있다.",1,4,"https://smartstore.naver.com/leelora/products/742135756"));
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
