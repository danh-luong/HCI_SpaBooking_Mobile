package rosie.com.rosiebeauty.Listener;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import rosie.com.rosiebeauty.Model.Service;
import rosie.com.rosiebeauty.ServiceManagementFragment;

public class RemoveServiceButtonListener implements View.OnClickListener{
    private CardView cardView;
    private AppCompatActivity currentActivity;
    private Fragment currentFragment;
    private Service service;

    public RemoveServiceButtonListener(CardView cardView, AppCompatActivity currentActivity, Fragment currentFragment, Service service) {
        this.cardView = cardView;
        this.currentActivity = currentActivity;
        this.currentFragment = currentFragment;
        this.service = service;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(currentActivity)
                .setTitle("Xác nhận xóa dịch vụ")
                .setMessage("Bạn có muốn xóa dịch vụ này?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ServiceManagementFragment fragment = (ServiceManagementFragment) currentFragment;
                        fragment.getServices().remove(service);
                        ((ServiceManagementFragment) currentFragment).loadRecycleView();
                        Toast.makeText(currentActivity, "Dịch vụ đã bị xóa!", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton("Hủy", null).show();
    }
}
