package kr.mashup.team2.promise;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddScheduleDateFragment extends Fragment {

    View view;
    int year, month, day;
    @BindView(R.id.tv_selected_year) TextView tvYear;
    @BindView(R.id.tv_selected_month) TextView tvMonth;
    @BindView(R.id.tv_selected_day) TextView tvDay;
    @BindView(R.id.layout_date) LinearLayout layoutDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_schedule_date, container, false);
        ButterKnife.bind(this, view);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        tvYear.setText(Integer.toString(year));
        tvMonth.setText(Integer.toString(month));
        tvDay.setText(Integer.toString(day));

        ((AddScheduleActivity)getContext()).getSupportActionBar().setTitle("시간 & 장소");

        layoutDate.setOnClickListener(clickListener);

        setCustomActionbar();

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new DatePickerDialog(view.getContext(), dateSetListener, year, month - 1, day).show();
        }
    };


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            tvYear.setText(Integer.toString(year));
            tvMonth.setText(Integer.toString(monthOfYear + 1));
            tvDay.setText(Integer.toString(dayOfMonth));
        }
    };

    public void onStart() {
        super.onStart();


    }

    private void setCustomActionbar() {

        ActionBar actionBar = ((AddScheduleActivity)getActivity()).getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.actionbar_add_schedule_date, null);
        actionBar.setCustomView(customView);

        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(customView, params);


    }
}
