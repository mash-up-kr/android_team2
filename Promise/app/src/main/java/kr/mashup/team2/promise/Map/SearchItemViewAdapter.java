package kr.mashup.team2.promise.Map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.mashup.team2.promise.R;

public class SearchItemViewAdapter extends RecyclerView.Adapter<SearchItemViewHolders> {

    private Context context;
    private List<SearchItem> itemList;

    public SearchItemViewAdapter(Context context, List<SearchItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public SearchItemViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_list, null);
        SearchItemViewHolders holder = new SearchItemViewHolders(layoutView);

        return holder;
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolders holder, int position) {
        holder.tvSearched.setText(itemList.get(position).getTitle());
        Log.d("SearchList", itemList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
