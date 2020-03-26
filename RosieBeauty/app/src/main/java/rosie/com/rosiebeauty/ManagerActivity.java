package rosie.com.rosiebeauty;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, new ServiceManagementFragment()).commit();
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
                            selectedFragment = new ServiceManagementFragment();
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
                            selectedFragment = new CurrentUserProfileFragment();
                            toolbar_title.setText("Hồ sơ");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container_manager);
        if (f instanceof ListServiceFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
            bottomNavigationView.setVisibility(View.VISIBLE);
            toolbar_title.setText("Dịch vụ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else if (f instanceof FragmentCreateNewService) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Tạo mới");
        } else if (f instanceof HistoryBookingFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Lịch hẹn");
        } else if (f instanceof CurrentUserProfileFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Hồ sơ");

        }
    }


    public void clickGoToAppointmentDetail(View view) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
        bottomNavigationView.setVisibility(View.GONE);
        toolbar_title.setText("Chi tiết lịch hẹn");
        selectedFragment = new AppointmentDetail();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container_manager, selectedFragment).addToBackStack(null).commit();
    }

    public void clickToCreateService(View view) {
        toolbar_title.setText("Dịch vụ");
        selectedFragment = new ListServiceFragment();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container_manager, selectedFragment).addToBackStack(null).commit();
    }

    public void clickGoToAppointmentDetailManager(View view) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_manager);
        bottomNavigationView.setVisibility(View.GONE);
        toolbar_title.setText("Chi tiết lịch hẹn");
        selectedFragment = new AppointmentDetailManager();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container_manager, selectedFragment).addToBackStack(null).commit();
    }
}
