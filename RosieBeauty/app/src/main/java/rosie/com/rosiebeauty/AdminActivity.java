package rosie.com.rosiebeauty;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    private TextView toolbar_title;
    private Fragment selectedFragment = null;

    public Fragment getSelectedFragment() {
        return selectedFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setStatusBarGradiant(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar_title = (TextView) findViewById(R.id.text_toolbar);
        toolbar_title.setText("Quản lý tài khoản");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurrentAccountFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            selectedFragment = new CurrentAccountFragment();
                            toolbar_title.setText("Quản lý tài khoản");
                            break;
                        case R.id.action_profile:
                            selectedFragment = new CurrentUserProfileFragment();
                            toolbar_title.setText("Hồ sơ");
                            break;
                        case R.id.action_branch:
                            selectedFragment = new BranchManagementFragment();
                            toolbar_title.setText("Phê duyệt quản lý");
                            break;
                        case R.id.action_pending_user:
                            selectedFragment = new PendingManagerFragment();
                            toolbar_title.setText("Quản lý spa");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

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
