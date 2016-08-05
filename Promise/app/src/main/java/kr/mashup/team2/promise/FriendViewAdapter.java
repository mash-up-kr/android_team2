package kr.mashup.team2.promise;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.mashup.team2.promise.DB.MyDBHelper;

public class FriendViewAdapter extends RecyclerView.Adapter<FriendViewHolder>{
    private Context context;
    private ArrayList<FriendObject> itemList;
    private MyDBHelper myDBHelper;

    public FriendViewAdapter(Context context) {
        this.context = context;
        myDBHelper = new MyDBHelper(context);
        itemList = new ArrayList<FriendObject>();
        initSampleData();
    }

    private void initSampleData() {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query(MyDBHelper.TABLE_NAME_FRIEND, null, null, null, null, null, null, null);


        while(cursor.moveToNext()) {
            int id = cursor.getInt(2);
            String name;

            Cursor cursorNmae = db.query(MyDBHelper.TABLE_NAME_USER, new String[]{"name"}, "_id = ?",
                    new String[]{Integer.toString(id)}, null, null, null);
            cursorNmae.moveToNext();
            name = cursorNmae.getString(0);
            cursorNmae.close();

            addItem(id, name);
        }

        cursor.close();
    }

    public void addItem(int id, String name) {
        itemList.add(0, new FriendObject(id, name));
        notifyItemInserted(0);
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item_list, null);
        FriendViewHolder holder = new FriendViewHolder(layoutView, itemList);

        return holder;
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, final int position) {
        holder.tvFriendName.setText(itemList.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
