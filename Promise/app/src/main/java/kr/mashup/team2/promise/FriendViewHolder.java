package kr.mashup.team2.promise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_add_friend) TextView tvFriendName;
    @BindView(R.id.iv_add_friend) ImageView ivAddFriend;
    ArrayList<FriendObject> itemList;

    View view;

    public FriendViewHolder(View itemView, ArrayList<FriendObject> itemList) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        view = itemView;
        this.itemList = itemList;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    }

}
