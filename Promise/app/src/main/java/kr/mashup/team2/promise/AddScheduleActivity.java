package kr.mashup.team2.promise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddScheduleActivity extends AppCompatActivity {

    @BindView(R.id.pager_add) ViewPager viewPager;
    PageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);

        final MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        CirclePageIndicator circleIndicator = (CirclePageIndicator)findViewById(R.id.circleIndicator);
        pageIndicator = circleIndicator;
        circleIndicator.setViewPager(viewPager);

        final float density = getResources().getDisplayMetrics().density;
        circleIndicator.setRadius(5 * density);
        circleIndicator.setPageColor(0xFF888888);
        circleIndicator.setFillColor(Color.parseColor("#FF4081"));

    }

}
