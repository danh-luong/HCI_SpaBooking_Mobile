package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class PendingRequestDetailActivity extends AppCompatActivity {

    private TextView txtProfileName, txtEmailProfile, txtPhoneProfile, txtUsernameProfile, txtSpaName, txtAddress;
    private User user;
    private String userKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request_detail);
        setStatusBarGradiant(this);
        Intent intent = getIntent();
        userKey = intent.getStringExtra("key");
        user = UserRepository.userList.get(userKey);
        txtProfileName = findViewById(R.id.txtNameProfile);
        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = findViewById(R.id.txtPhoneProfile);
        txtUsernameProfile = findViewById(R.id.txtUsernameProfile);
        txtSpaName = findViewById(R.id.txtSpaName);
        txtAddress = findViewById(R.id.txtAddress);

        txtProfileName.setText("Trần Quang");
        txtEmailProfile.setText(user.getEmail());
        txtPhoneProfile.setText(user.getPhone());
        txtUsernameProfile.setText(user.getUsername());
        txtSpaName.setText(user.getSpaName());
        txtAddress.setText(user.getAddress());
    }

    public void onclickDeactive(View view) {
        UserRepository.userList.get(userKey).setStatus("Decline");
        Intent intent = new Intent(this, AdminActivity.class);
        Toast.makeText(this, "Đã Từ Chối Yêu Cầu", Toast.LENGTH_LONG).show();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onclickActive(View view) {
        UserRepository.userList.get(userKey).setAccepted(true);
        Intent intent = new Intent(this, AdminActivity.class);
        Toast.makeText(this, "Đã Duyệt Thành Công!", Toast.LENGTH_LONG).show();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.gradient_statusbar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }
}
