package kr.mashup.team2.promise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter{
    int numOfTabs;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        this.numOfTabs = 3;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddScheduleMemberFragment tab1 = new AddScheduleMemberFragment();
                return tab1;
            case 1:
                AddScheduleDateFragment tab2 = new AddScheduleDateFragment();
                return tab2;
            case 2:
                AddScheduleLocationFragment tab3 = new AddScheduleLocationFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numOfTabs;
    }
}
