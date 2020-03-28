package rosie.com.rosiebeauty.Listener;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.EditBranchActivity;

public class EditBranchButtonListener implements View.OnClickListener {

    private String title;
    private String address;
    private String status;
    private int image;
    private AppCompatActivity currentActivity;
    private User manager;

    public EditBranchButtonListener(String title, String address, String status, int image, AppCompatActivity currentActivity) {
        this.title = title;
        this.address = address;
        this.status = status;
        this.image = image;
        this.currentActivity = currentActivity;
    }

    public EditBranchButtonListener(String title, String address, String status, int image, AppCompatActivity currentActivity, User manager) {
        this.title = title;
        this.address = address;
        this.status = status;
        this.image = image;
        this.currentActivity = currentActivity;
        this.manager = manager;
    }

    public User getManager() {
        return manager;
    }

    public void setUser(User manager) {
        this.manager = manager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(currentActivity, EditBranchActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("address", address);
        intent.putExtra("image", image);
        intent.putExtra("status", status);
        intent.putExtra("manager", manager);
        currentActivity.startActivity(intent);
    }
}
