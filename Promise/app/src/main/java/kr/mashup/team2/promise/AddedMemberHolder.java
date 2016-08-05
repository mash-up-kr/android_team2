package kr.mashup.team2.promise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddedMemberHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.iv_added) ImageView ivAddedMember;
    @BindView(R.id.tv_added_member) TextView tvAddedMember;

    public AddedMemberHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        ivAddedMember.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
