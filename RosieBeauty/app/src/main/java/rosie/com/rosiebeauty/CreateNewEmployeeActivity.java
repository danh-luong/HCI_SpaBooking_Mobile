package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class CreateNewEmployeeActivity extends AppCompatActivity {

    private EditText edtName, edtPhone, edtEmail, edtPassword, edtConfirmPassword, edtUsername;
    private Button btnCreate;
    private TextView txtError, txtErrorConfirm;
    private TextView titleName, titlePhone, titleEmail, titleUsername, titlePassword, titleConfirmPassword;
    private boolean isError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_employee);
        setStatusBarGradiant(this);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnCreate = findViewById(R.id.btnCreate);

        txtErrorConfirm = findViewById(R.id.txtErrorConfirm);
        txtError = findViewById(R.id.txtError);

        titleName = findViewById(R.id.titleName);
        titlePhone = findViewById(R.id.titlePhone);
        titleEmail = findViewById(R.id.titleEmail);
        titleUsername = findViewById(R.id.titleUsername);
        titlePassword = findViewById(R.id.titlePassword);
        titleConfirmPassword = findViewById(R.id.titleConfirmPassword);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String confirmPassword = edtConfirmPassword.getText().toString().trim();
                String username = edtUsername.getText().toString().trim();

                if (name.equals("")) {
                    titleName.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titleName.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (username.equals("")) {
                    titleUsername.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titleUsername.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (phone.equals("")) {
                    titlePhone.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titlePhone.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (email.equals("")) {
                    titleEmail.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titleEmail.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (password.equals("")) {
                    titlePassword.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titlePassword.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (confirmPassword.equals("")) {
                    titleConfirmPassword.setTextColor(Color.parseColor("#FF0000"));
                    isError = true;
                } else {
                    titleConfirmPassword.setTextColor(Color.parseColor("#000000"));
                    isError = false;
                }

                if (isError) {
                    txtError.setVisibility(View.VISIBLE);
                    return;
                } else {
                    txtError.setVisibility(View.GONE);
                }

                if (!password.equals(confirmPassword)) {
                    txtErrorConfirm.setVisibility(View.VISIBLE);
                    return;
                } else {
                    txtErrorConfirm.setVisibility(View.GONE);
                }

                UserRepository.userList.put(username,
                        new User(username, name, password, email, phone, R.drawable.ic_user, User.ROLE_MANAGER, "active"));
                Intent intent = new Intent(CreateNewEmployeeActivity.this, AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });
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
