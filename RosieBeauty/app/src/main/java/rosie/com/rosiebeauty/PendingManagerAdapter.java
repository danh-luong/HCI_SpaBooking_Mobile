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

public class PendingManagerAdapter extends
        RecyclerView.Adapter<PendingManagerAdapter.ViewHolder>{

    private List<User> list;
    private Activity activity;

    public PendingManagerAdapter(List<User> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout cardView = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_manager_account, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        LinearLayout cardView = holder.cardView;
        TextView txtName = (TextView) cardView.findViewById(R.id.txtName);
        TextView txtRoleId = (TextView) cardView.findViewById(R.id.txtRoleId);
        TextView txtEmail = (TextView) cardView.findViewById(R.id.txtEmail);
        TextView txtPhone = (TextView) cardView.findViewById(R.id.txtPhone);

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

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PendingManagerAdapter.this.activity, PendingRequestDetailActivity.class);
                intent.putExtra("key", list.get(position).getUsername());
                PendingManagerAdapter.this.activity.startActivity(intent);
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
