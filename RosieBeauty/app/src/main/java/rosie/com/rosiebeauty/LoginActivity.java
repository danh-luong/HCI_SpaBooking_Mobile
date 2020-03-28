package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;
    private SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserRepository.initDataUser();
        preference = getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        if (preference.contains("username") && preference.contains("password")) {
            String username = preference.getString("username", null);
            String password = preference.getString("password", null);
            Boolean isAuthenticated = requestLogin(username, password);
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
            }
        }
        setStatusBarGradiant(this);
        setContentView(R.layout.activity_login);
        TextView registerLink = (TextView) findViewById(R.id.txtRegisterLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
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
            SharedPreferences.Editor editor = preference.edit();
            editor.putString("username", UserRepository.currentUser.getUsername());
            editor.putString("password", UserRepository.currentUser.getPassword());
            editor.commit();
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
            Drawable background = activity.getResources().getDrawable(R.drawable.startup_grad);
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
            if (user.getPassword().equals(password) && user.getStatus().equals("active")) {
                UserRepository.currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void clickForgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPaswwordActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1000){
            Toast.makeText(LoginActivity.this, "Đổi mật khẩu thành công. Hãy đăng nhập lại.", Toast.LENGTH_LONG).show();
        }
    }
}
