package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        Intent intent = getIntent();
        userKey = intent.getStringExtra("key");
        user = UserRepository.userList.get(userKey);
        txtProfileName = findViewById(R.id.txtNameProfile);
        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = findViewById(R.id.txtPhoneProfile);
        txtUsernameProfile = findViewById(R.id.txtUsernameProfile);
        txtSpaName = findViewById(R.id.txtSpaName);
        txtAddress = findViewById(R.id.txtAddress);

        txtProfileName.setText("Hoa cúc");
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
}
