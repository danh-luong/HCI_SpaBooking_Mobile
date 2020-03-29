package rosie.com.rosiebeauty;

import androidx.annotation.Nullable;
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

public class ForgotPaswwordActivity extends AppCompatActivity {
    private EditText emailForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_paswword);
        setStatusBarGradiant(this);
        emailForgotPassword = findViewById(R.id.emailForgotPassword);
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

    public void clickGotoOpt(View view) {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (emailForgotPassword.getText().toString().isEmpty()) {
            emailForgotPassword.setError("Hãy nhập email của bạn.");
        } else if (!emailForgotPassword.getText().toString().matches(emailPattern)) {
            emailForgotPassword.setError("Email không hợp lệ");
        } else {
            Intent intent = new Intent(ForgotPaswwordActivity.this, OptActivity.class);
            startActivityForResult(intent, 1000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
