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

    MaterialSearchView searchView;
    private Fragment selectedFragment = null;
    private boolean isCurrentSearchFragment = false;
    private SearchFragment searchFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        //color statusbar
        setStatusBarGradiant(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        actionBar.setLogo(R.drawable.ic_spa_logo_no_text);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
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
                            break;
                        case R.id.action_favorites:
                            selectedFragment = new FavoriteFragment();
                            break;
                        case R.id.action_book:
                            selectedFragment = new BookingFragment();
                            break;
                        case R.id.action_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return  true;
                }
            };


    private void setupSearchView() {
        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        //searchView.setSuggestions(getResources().getStringArray(R.array.product_list));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((SearchFragment)selectedFragment).displaySubmitData(query);
                ((SearchFragment)selectedFragment).setIsSubmit(true);
                ((SearchFragment)selectedFragment).previousText = query;
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
                if(searchFragment == null) {
                    searchFragment = new SearchFragment();
                }
                selectedFragment = searchFragment;
                ((SearchFragment)selectedFragment).setSearchView(this.searchView);
                if (!isCurrentSearchFragment) {
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

    public void clickTextViewOnCategoryHome(View view) {
        TextView textView = (TextView) view;
        String text = textView.getText().toString();
        switch (text) {
            default:
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
                bottomNavigationView.setVisibility(View.GONE);
                selectedFragment = new CategoryServices();
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(f instanceof HomeFragment){
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }else if(f instanceof BookingFragment){
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    public void setIsCurrentSearchFragment(boolean isCurrentSearchFragment) {
        this.isCurrentSearchFragment = isCurrentSearchFragment;
    }

    public void clickToShowDetail(View view) {

    }

    public void clickGoToAppointmentDetail(View view) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.GONE);
        selectedFragment = new AppointmentDetail();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.pop_out_right, R.animator.pop_in_left).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();

    }
}