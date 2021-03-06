package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rosie.com.rosiebeauty.Data.User;

public class CardViewAccountAdapter extends
        RecyclerView.Adapter<CardViewAccountAdapter.ViewHolder>{

    private List<User> list;
    private Activity activity;

    public CardViewAccountAdapter(List<User> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout cardView = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_account, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        LinearLayout cardView = holder.cardView;
        TextView txtName = (TextView) cardView.findViewById(R.id.txtName);
        TextView txtRoleId = (TextView) cardView.findViewById(R.id.txtRoleId);
        TextView txtEmail = (TextView) cardView.findViewById(R.id.txtEmail);
        TextView txtPhone = (TextView) cardView.findViewById(R.id.txtPhone);
        TextView txtStatus = (TextView) cardView.findViewById(R.id.txtStatus);

        txtName.setText(list.get(position).getName());
        switch (list.get(position).getRole()) {
            case User.ROLE_ADMIN:
                txtRoleId.setText("Admin");
                break;
            case User.ROLE_CUSTOMER:
                txtRoleId.setText("Khách Hàng");
                break;
            case User.ROLE_MANAGER:
                txtRoleId.setText("Quản Lý");
                break;
        }
        txtEmail.setText(list.get(position).getEmail());
        txtPhone.setText(list.get(position).getPhone());

        if (list.get(position).getStatus().equalsIgnoreCase("active")) {
            txtStatus.setText("Kích hoạt");
        } else {
            txtStatus.setText("Vô hiệu hóa");
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardViewAccountAdapter.this.activity, ProfileUserActivity.class);
                intent.putExtra("key", list.get(position).getUsername());
                CardViewAccountAdapter.this.activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout cardView;

        public ViewHolder(LinearLayout cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
