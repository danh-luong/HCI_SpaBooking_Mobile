package rosie.com.rosiebeauty.Listener;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import rosie.com.rosiebeauty.BranchManagementFragment;
import rosie.com.rosiebeauty.Model.Branch;

public class RemoveBranchButtonListener implements View.OnClickListener {

    private CardView cardView;
    private AppCompatActivity currentActivity;
    private Fragment currentFragment;
    private Branch branch;

    public RemoveBranchButtonListener(CardView cardView, AppCompatActivity currentActivity, Fragment currentFragment, Branch branch) {
        this.cardView = cardView;
        this.currentActivity = currentActivity;
        this.currentFragment = currentFragment;
        this.branch = branch;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(currentActivity)
                .setTitle("Xác nhận xóa chi nhánh")
                .setMessage("Bạn có muốn xóa chi nhánh này?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        BranchManagementFragment fragment = (BranchManagementFragment)currentFragment;
                        fragment.getBranches().remove(branch);
                        ((BranchManagementFragment) currentFragment).loadRecycleView();
                        Toast.makeText(currentActivity, "Chi nhánh đã bị xóa!", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton("Hủy", null).show();
    }
}
