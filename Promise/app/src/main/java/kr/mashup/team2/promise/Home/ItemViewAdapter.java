package kr.mashup.team2.promise.Home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.mashup.team2.promise.DB.MyDBHelper;
import kr.mashup.team2.promise.R;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewHolders> {

    private Context context;
    private List<ItemObject> itemList;
    private MyDBHelper myDBHelper;

    public ItemViewAdapter(Context context) {
        this.context = context;
        myDBHelper = new MyDBHelper(context);
        itemList = new ArrayList<>();
        initSampleData();
    }

    private void initSampleData() {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query(MyDBHelper.TABLE_NAME_APPOINTMENT_INFO, null, null, null, null, null, null, null);


        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String userId = cursor.getString(2);
            String location = cursor.getString(3);
            String latitude = cursor.getString(4);
            String longitude = cursor.getString(5);
            String userName;

            Cursor cursorNmae = db.query(MyDBHelper.TABLE_NAME_USER, new String[]{"name"}, "_id = ?",
                    new String[]{userId}, null, null, null);
            cursorNmae.moveToNext();
            userName = cursorNmae.getString(0);
            cursorNmae.close();

            addItem(id, date, location, latitude, longitude, userName);
        }

        cursor.close();
    }

    public void addItem(int id, String date, String location, String latitude, String longitude, String user) {
        itemList.add(0, new ItemObject(id, date, location, latitude, longitude, user));
        notifyItemInserted(0);
    }

    @Override
    public ItemViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_list, null);
        ItemViewHolders holder = new ItemViewHolders(layoutView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolders holder, int position) {
        holder.tvDate.setText(itemList.get(position).getDate());
        holder.tvLocation.setText(itemList.get(position).getLocation());
        holder.tvUsers.setText(itemList.get(position).getUser());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
