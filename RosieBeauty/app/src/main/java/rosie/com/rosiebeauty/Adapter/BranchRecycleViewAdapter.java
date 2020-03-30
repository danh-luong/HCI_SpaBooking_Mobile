package rosie.com.rosiebeauty.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Listener.EditBranchButtonListener;
import rosie.com.rosiebeauty.Listener.RemoveBranchButtonListener;
import rosie.com.rosiebeauty.Model.Branch;
import rosie.com.rosiebeauty.R;

public class BranchRecycleViewAdapter extends RecyclerView.Adapter<BranchRecycleViewAdapter.ViewHolder> {

    private ArrayList<Branch> branches;
    private AppCompatActivity currentActivity;
    private Fragment currentFragment;


    public BranchRecycleViewAdapter(ArrayList<Branch> branches, AppCompatActivity currentActivity,Fragment currentFragment) {
        this.branches = branches;
        this.currentActivity = currentActivity;
        this.currentFragment = currentFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_branch, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtAddress = cardView.findViewById(R.id.txtPrice);
        TextView txtStatus = cardView.findViewById(R.id.txtPromotion);
        ImageView imageView = cardView.findViewById(R.id.itemImage);
        BootstrapButton btnEditBranch = cardView.findViewById(R.id.btnEdit);
        ImageButton btnRemoveBranch = cardView.findViewById(R.id.btnRemoveService);

        Branch branch = branches.get(position);
        txtTitle.setText(branch.getTitle());
        txtAddress.setText(branch.getAddress());
        txtStatus.setText(branch.getStatus());
        imageView.setImageResource(branch.getImage());


        //bấm vào edit pop up fragment edit
        btnEditBranch.setOnClickListener(
                new EditBranchButtonListener(
                        branch.getTitle(), branch.getAddress(),
                        branch.getStatus(), branch.getImage(), currentActivity, branch.getManager()));
        btnRemoveBranch.setOnClickListener(new RemoveBranchButtonListener(holder.cardView, currentActivity, currentFragment, branch));

    }

    @Override
    public int getItemCount() {
        return branches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }
    }

}
