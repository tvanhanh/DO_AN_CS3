package com.example.do_an_cs3.Ardapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.do_an_cs3.View.back_end.View_fragment.FragmentPersonnal.ListDeparmentFragment;
import com.example.do_an_cs3.View.back_end.View_fragment.FragmentPersonnal.ListPersonnalFragment;
import com.example.do_an_cs3.View.back_end.View_fragment.FragmentHome.UpdateNewFragment;

public class ViewPagerAdapter2 extends FragmentPagerAdapter {
    public ViewPagerAdapter2(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListPersonnalFragment();
            case 1:
                return new ListDeparmentFragment();

            default:
                return new UpdateNewFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "NHÂN SỰ";
                break;
            case 1:
                title = "CÁC BỘ PHẬN";
                break;
        }
        return title;
    }
}
