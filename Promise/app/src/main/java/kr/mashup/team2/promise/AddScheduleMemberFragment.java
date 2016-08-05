package kr.mashup.team2.promise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddScheduleMemberFragment extends Fragment {

    @BindView(R.id.recycler_view_add_friend) RecyclerView recyclerView;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_add_schedule_member, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        FriendViewAdapter friendViewAdapter = new FriendViewAdapter(getContext());
        recyclerView.setAdapter(friendViewAdapter);

        setCustomActionbar();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void setCustomActionbar() {

        ActionBar actionBar =  ((AddScheduleActivity)getActivity()).getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.actionbar_add_schedule_member, null);
        actionBar.setCustomView(customView);

        Toolbar parent = (Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(customView, params);

        Button btnCancle = (Button)customView.findViewById(R.id.btn_cancle_add_schedule);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AddScheduleActivity)getContext()).finish();
            }
        });
    }
}
