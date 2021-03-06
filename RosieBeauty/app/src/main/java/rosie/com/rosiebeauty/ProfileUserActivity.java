package rosie.com.rosiebeauty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class ProfileUserActivity extends AppCompatActivity {

    private TextView txtProfileName, txtEmailProfile, txtPhoneProfile, txtUsernameProfile;
    private Spinner spnRole;
    private ImageView avatar;
    private Button btnDeactive, btnActive;
    private User user;
    private String roleName;
    private TextView txtRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setStatusBarGradiant(this);
        Intent intent = getIntent();
        String userKey = intent.getStringExtra("key");
        user = UserRepository.userList.get(userKey);
        avatar = findViewById(R.id.avatar);
        txtProfileName = findViewById(R.id.txtNameProfile);
        txtEmailProfile = findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = findViewById(R.id.txtPhoneProfile);
        txtUsernameProfile = findViewById(R.id.txtUsernameProfile);

        txtProfileName.setText(user.getName());
        txtEmailProfile.setText(user.getEmail());
        txtPhoneProfile.setText(user.getPhone());
        txtUsernameProfile.setText(user.getUsername());
        avatar.setImageResource(user.getImgSrc());

        txtRole = findViewById(R.id.txtRole);

        spnRole = findViewById(R.id.spnRole);
        List<String> listRoletemp = new ArrayList<>();
        listRoletemp.add(User.ROLE_ADMIN);
        listRoletemp.add("Khách Hàng");
        listRoletemp.add("Quản lí");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listRoletemp);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRole.setAdapter(dataAdapter);
        String myWantedRole = null;
        switch (user.getRole()) {
            case User.ROLE_ADMIN:
                myWantedRole = User.ROLE_ADMIN;
                txtRole.setText(User.ROLE_ADMIN);
                break;
            case User.ROLE_CUSTOMER:
                myWantedRole = "Khách Hàng";
                txtRole.setText("Khách Hàng");
                break;
            case User.ROLE_MANAGER:
                myWantedRole = "Quản lí";
                txtRole.setText("Quản lí");
                break;
        }
        int spinnerPosition = dataAdapter.getPosition(myWantedRole);
        spnRole.setSelection(spinnerPosition);
        spnRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleName = spnRole.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnRole.setEnabled(false);
        btnDeactive = findViewById(R.id.btnDeactive);
        btnActive = findViewById(R.id.btnActive);

        if (user.getStatus().equals("active")) {
            btnDeactive.setVisibility(View.VISIBLE);
        } else {
            btnActive.setVisibility(View.VISIBLE);
        }

        Button btnChangeRole = findViewById(R.id.btnChangeRole);

        if (user.getRole().equalsIgnoreCase("manager")) {
//            Button btnSpaDetail = findViewById(R.id.btnSpaDetail);
//            btnSpaDetail.setVisibility(View.VISIBLE);
            btnDeactive.setVisibility(View.GONE);
            btnActive.setVisibility(View.GONE);
        }

        btnChangeRole.setVisibility(View.GONE);
    }

    public void onclickDeactive(View view) {
        UserRepository.userList.get(user.getUsername()).setStatus("deactive");
        Intent intent = new Intent(this, AdminActivity.class);
        CurrentAccountFragment.status = CurrentAccountFragment.STATUS_DEACTIIVE;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onclickActive(View view) {
        UserRepository.userList.get(user.getUsername()).setStatus("active");
        Intent intent = new Intent(this, AdminActivity.class);
        CurrentAccountFragment.status = CurrentAccountFragment.STATUS_ACTIVE;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void changeRole(View view) {
        String selectedRole = null;
        if (roleName.equals(User.ROLE_ADMIN)) {
            selectedRole = User.ROLE_ADMIN;
        } else if (roleName.equals("Khách Hàng")) {
            selectedRole = User.ROLE_CUSTOMER;
        } else if (roleName.equals("Quản lí")) {
            selectedRole = User.ROLE_MANAGER;
        }
        user.setRole(selectedRole);
        Intent intent = new Intent(this, AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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

    public void clickToWatchSpaDetail(View view) {
        Intent intent = new Intent(this, EditBranchActivity.class);
        intent.putExtra("title","Rose Spa Hai Bà Trưng");
        intent.putExtra("address","235B - Hai Bà Trưng - Quận 1 " );
        intent.putExtra("status", "Đang hoạt động");
        intent.putExtra("image", R.drawable.spa2batrung);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
