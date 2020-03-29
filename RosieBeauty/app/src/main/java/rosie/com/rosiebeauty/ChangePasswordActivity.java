package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText password, confirmPassword;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setStatusBarGradiant(this);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        errorMsg = findViewById(R.id.errorMessage);
    }

    protected void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.forgot_password_background);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    public void clickChangePassword(View view) {
        if (password.getText().toString().trim().isEmpty() || confirmPassword.getText().toString().trim().isEmpty()) {
            if (password.getText().toString().trim().isEmpty()) {
                password.setError("Hãy nhập mật khẩu.");
            }
            if (confirmPassword.getText().toString().trim().isEmpty()) {
                confirmPassword.setError("Hãy nhập xác nhận mật khẩu.");
            }
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            errorMsg.setText("Xác nhận mật khẩu không khớp.");
        } else {
            setResult(RESULT_OK);
            finish();
        }
    }
}
