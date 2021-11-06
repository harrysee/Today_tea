package kr.hs.emirim.w2015.today_tea;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter{

    private final List<Fragment> mfragList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fragmentManager, @NonNull @org.jetbrains.annotations.NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mfragList.add(new TeaFragment());
        mfragList.add(new SecondFragment());
        mfragList.add(new ThirdFragment());
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment createFragment(int position) {
        return mfragList.get(position);
    }

    @Override
    public int getItemCount() {
        return mfragList.size();
    }
}
