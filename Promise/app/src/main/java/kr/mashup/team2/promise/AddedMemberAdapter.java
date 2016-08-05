package kr.mashup.team2 .promise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AddedMemberAdapter extends RecyclerView.Adapter<AddedMemberHolder>{
    private Context context;
    private List<FriendObject> itemList;

    public AddedMemberAdapter(Context context) {
        this.context = context;
        itemList = new ArrayList<>();
    }

    public void addItem(int id, String name) {
        itemList.add(0, new FriendObject(id, name));
        notifyItemInserted(0);
    }

    @Override
    public AddedMemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.added_friend_list, null);
        AddedMemberHolder holder = new AddedMemberHolder(layoutView);

        return holder;
    }

    @Override
    public void onBindViewHolder(AddedMemberHolder holder, int position) {
        holder.tvAddedMember.setText(itemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
