package rosie.com.rosiebeauty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import rosie.com.rosiebeauty.Adapter.RegisterPageAdapter;

public class RegisterActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RegisterPageAdapter registerPageAdapter;
    private TextView txtTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.registerViewPager);
        registerPageAdapter  = new RegisterPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(registerPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        txtTerm = findViewById(R.id.textTerms);
        txtTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                TextView textView = new TextView(RegisterActivity.this);
//                textView.setText("Điều khoản sử dụng:");
//                textView.setTextColor(getColor(R.color.service_item_blue_color));
//                textView.setTextSize(20);
//                builder.setCustomTitle(textView);
                builder.setTitle("Điều khoản dịch vụ:");
                builder.setMessage(getString(R.string.policies));
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

//                builder.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }



    public void onclickRegister(View view) {
        if(tabLayout.getSelectedTabPosition() == 0){
            Toast.makeText(this, "Đăng ký thành công. Mời bạn đăng nhập.", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Đăng ký đã được tiếp nhận. Chúng tôi sẽ liên hệ với bạn sau.", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
