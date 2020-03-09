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
import android.widget.EditText;
import android.widget.TextView;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.initDataUser();
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_login);
        TextView registerLink = (TextView) findViewById(R.id.txtRegisterLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void onclickLogin(View view) {
        String userName = ((EditText) findViewById(R.id.edtUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.edtPassword)).getText().toString();
        Boolean isAuthenticated = requestLogin(userName, password);
        if (isAuthenticated == true) {
            switch (UserRepository.currentUser.getRole()) {
                case User.ROLE_ADMIN:
                    intent = new Intent(this, AdminActivity.class);
                    break;
                case User.ROLE_MANAGER:
                    intent = new Intent(this, ManagerActivity.class);
                    break;
                case User.ROLE_CUSTOMER:
                    intent = new Intent(this, MainActivity.class);
                    break;
            }
            finish();
            startActivity(intent);

        } else {
            TextView txtErrorMsg = (TextView) findViewById(R.id.txtErrorMsg);
            txtErrorMsg.setText("Đăng nhập thất bại. Xin kiểm tra lại tài khoản và mật khẩu của bạn.");
            txtErrorMsg.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txtErrorMsg = (TextView) findViewById(R.id.txtErrorMsg);
        txtErrorMsg.setVisibility(View.INVISIBLE);
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

    public Boolean requestLogin(String userName, String password) {
        userName = userName.trim();
        password = password.trim();
        User user = UserRepository.userList.get(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                UserRepository.currentUser = user;
                return true;
            }
        }
        return false;
    }

}
