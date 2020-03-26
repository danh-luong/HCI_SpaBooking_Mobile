package rosie.com.rosiebeauty;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rosie.com.rosiebeauty.Adapter.RegisterPageAdapter;

public class RegisterActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.registerViewPager);
        RegisterPageAdapter registerPageAdapter = new RegisterPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(registerPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }



    public void onclickRegister(View view) {

    }
}
