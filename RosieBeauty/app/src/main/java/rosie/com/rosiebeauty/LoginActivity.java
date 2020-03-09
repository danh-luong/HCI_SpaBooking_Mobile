package rosie.com.rosiebeauty;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import rosie.com.rosiebeauty.UserData.CurrentUserData;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MANAGER = "manager";
    public static final String ROLE_CUSTOMER = "customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setStatusBarGradiant(this);
        TextView registerLink = (TextView)findViewById(R.id.txtRegisterLink);
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
        String role = requestLogin(userName, password);
        if (role != null) {
            switch (role) {
                case ROLE_ADMIN:
                    intent = new Intent(this, AdminActivity.class);
                    CurrentUserData.setCurrentUserAsAdmin();
                    break;
                case ROLE_MANAGER:
                    intent = new Intent(this, ManagerActivity.class);
                    CurrentUserData.setCurrentUserAsManager();
                    break;
                case ROLE_CUSTOMER:
                    intent = new Intent(this, MainActivity.class);
                    CurrentUserData.setCurrentUserAsCustomer();
                    break;
            }
            finish();
            startActivity(intent);

        } else {
            TextView txtErrorMsg = (TextView)findViewById(R.id.txtErrorMsg);
            txtErrorMsg.setText("Đăng nhập thất bại. Xin kiểm tra lại tài khoản và mật khẩu của bạn.");
            txtErrorMsg.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txtErrorMsg = (TextView)findViewById(R.id.txtErrorMsg);
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

    public String requestLogin(String userName, String password) {
        userName = userName.trim();
        password = password.trim();
        if(userName.equals(LoginActivity.ROLE_CUSTOMER)) return LoginActivity.ROLE_CUSTOMER;
        if (userName.equals(LoginActivity.ROLE_ADMIN)) return LoginActivity.ROLE_ADMIN;
        if (userName.equals(LoginActivity.ROLE_MANAGER)) return LoginActivity.ROLE_MANAGER;
        return null;
    }

}
