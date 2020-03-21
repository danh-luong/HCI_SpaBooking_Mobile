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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ManagerActivity extends AppCompatActivity {
    private TextView toolbar_title;
    private Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarGradiant(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        toolbar_title = (TextView) findViewById(R.id.text_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, new ListServiceFragment()).commit();
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_service:
                            selectedFragment = new ListServiceFragment();
                            ManagerActivity.this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            toolbar_title.setText("Dịch vụ");
                            break;
                        case R.id.action_createService:
                            selectedFragment = new FragmentCreateNewService();
                            toolbar_title.setText("Tạo mới");
                            break;
                        case R.id.action_scan:
                            selectedFragment = new ScanFragment();
                            toolbar_title.setText("Quét mã");
                            break;
                        case R.id.action_history:
                            selectedFragment = new HistoryBookingFragment();
                            toolbar_title.setText("Lịch hẹn");
                            break;
                        case R.id.action_profile:
                            selectedFragment = new ProfileFragment();
                            toolbar_title.setText("Hồ sơ");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, selectedFragment).commit();
                    return true;
                }
            };
}
