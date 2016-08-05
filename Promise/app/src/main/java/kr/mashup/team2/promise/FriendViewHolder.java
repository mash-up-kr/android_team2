package kr.mashup.team2.promise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_add_friend) TextView tvFriendName;
    @BindView(R.id.iv_add_friend) ImageView ivAddFriend;

    public FriendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

}
