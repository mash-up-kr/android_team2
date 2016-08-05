package kr.mashup.team2.promise.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.mashup.team2.promise.R;

public class ItemViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    @BindView(R.id.tv_main_date) public TextView tvDate;
    @BindView(R.id.tv_main_location) public TextView tvLocation;
    @BindView(R.id.tv_main_users) public TextView tvUsers;

    public ItemViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "position: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("삭제 확인")
                .setMessage("삭제하시겠습니까?")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();

        return false;
    }
}
