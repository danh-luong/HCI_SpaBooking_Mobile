package rosie.com.rosiebeauty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
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
                break;
            case User.ROLE_CUSTOMER:
                myWantedRole = "Khách Hàng";
                break;
            case User.ROLE_MANAGER:
                myWantedRole = "Quản lí";
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
        btnDeactive = findViewById(R.id.btnDeactive);
        btnActive = findViewById(R.id.btnActive);

        if (user.getStatus().equals("active")) {
            btnDeactive.setVisibility(View.VISIBLE);
        } else {
            btnActive.setVisibility(View.VISIBLE);
        }
    }

    public void onclickDeactive(View view) {
        UserRepository.userList.get(user.getRole()).setStatus("deactive");
        Intent intent = new Intent(this, AdminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onclickActive(View view) {
        UserRepository.userList.get(user.getRole()).setStatus("active");
        Intent intent = new Intent(this, AdminActivity.class);
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
}
