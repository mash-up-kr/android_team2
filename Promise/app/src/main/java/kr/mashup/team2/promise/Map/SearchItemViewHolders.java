package kr.mashup.team2.promise.Map;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.mashup.team2.promise.R;

public class SearchItemViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_map_searched) TextView tvSearched;

    public SearchItemViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

}
