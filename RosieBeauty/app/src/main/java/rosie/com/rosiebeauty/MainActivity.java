package rosie.com.rosiebeauty;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity {

    public static String schedule_button_date = "";
    public static String schedule_button_time = "";

    MaterialSearchView searchView;
    private Fragment selectedFragment = null;
    private boolean isCurrentSearchFragment = false;
    private SearchFragment searchFragment = null;
    private TextView toolbar_title;

    public Fragment getSelectedFragment() {
        return selectedFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        //color statusbar
        setStatusBarGradiant(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        toolbar_title = (TextView) findViewById(R.id.text_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        setupSearchView();

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
                    searchView.closeSearch();
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            selectedFragment = new HomeFragment();
                            MainActivity.this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            toolbar_title.setText("Rose Spa");
                            break;
                        case R.id.action_favorites:
                            selectedFragment = new FavoriteFragment();
                            toolbar_title.setText("Yêu thích");
                            break;
                        case R.id.action_map:
                            selectedFragment = new FragmentNearly();
                            toolbar_title.setText("Gần đây");
                            break;
                        case R.id.action_book:
                            selectedFragment = new BookingFragment();
                            toolbar_title.setText("Lịch hẹn");
                            break;
                        case R.id.action_profile:
                            selectedFragment = new CurrentUserProfileFragment();
                            toolbar_title.setText("Hồ sơ");
                            break;
                    }
                    isCurrentSearchFragment = false;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };


    private void setupSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        //searchView.setSuggestions(getResources().getStringArray(R.array.product_list));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((SearchFragment) selectedFragment).displaySubmitData(query);
                ((SearchFragment) selectedFragment).setIsSubmit(true);
                ((SearchFragment) selectedFragment).previousText = query;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((SearchFragment) selectedFragment).changeContentOnQueryTextChange(newText);
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_search:
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                }
                selectedFragment = searchFragment;
                ((SearchFragment) selectedFragment).setSearchView(this.searchView);
                if (!isCurrentSearchFragment) {
                    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
                    bottomNavigationView.setVisibility(View.GONE);
                    searchView.clearFocus();
                    isCurrentSearchFragment = true;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                } else {
                    searchView.showSearch();
                }
                break;
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof HomeFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            toolbar_title.setText("Rose Spa");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else if (f instanceof BookingFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Lịch hẹn");
        } else if (f instanceof FavoriteFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Yêu thích");
        } else if (f instanceof ProfileFragment) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar_title.setText("Hồ sơ");

        }
    }

    public void setIsCurrentSearchFragment(boolean isCurrentSearchFragment) {
        this.isCurrentSearchFragment = isCurrentSearchFragment;
    }

    public void clickGoToAppointmentDetail(View view) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.GONE);
        toolbar_title.setText("Chi tiết lịch hẹn");
        selectedFragment = new AppointmentDetail();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();

    }

    public void clickToViewList(View view) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.GONE);
        toolbar_title.setText("Dịch vụ");
        selectedFragment = new ListProductFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
    }

}