package rosie.com.rosiebeauty;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import rosie.com.rosiebeauty.Data.UserRepository;
import rosie.com.rosiebeauty.Listener.EditBranchButtonListener;
import rosie.com.rosiebeauty.Model.Branch;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentUserProfileFragment extends Fragment {

    private EditText edtUserName, edtPhone, edtEmail, edtName;
    private View rootView;
    private TextView txtNameProfile;
    private Button btnUpdateUserProfile;
    private Button btnLogOut;
    private Branch branch;

    public CurrentUserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current_user_profile, container, false);
        edtEmail = rootView.findViewById(R.id.edtEmailProfile);
        edtUserName = rootView.findViewById(R.id.edtUsernameProfile);
        edtPhone = rootView.findViewById(R.id.edtPhone);
        txtNameProfile = rootView.findViewById(R.id.txtNameProfile);
        edtName = rootView.findViewById(R.id.edtNameProfile);
        btnUpdateUserProfile = rootView.findViewById(R.id.btnUpdateUserProfile);

        edtEmail.setFocusableInTouchMode(true);
        edtUserName.setFocusableInTouchMode(true);
        edtPhone.setFocusableInTouchMode(true);

        edtEmail.setText(UserRepository.currentUser.getEmail());
        edtUserName.setText(UserRepository.currentUser.getUsername());
        edtPhone.setText(UserRepository.currentUser.getPhone());
        txtNameProfile.setText(UserRepository.currentUser.getName());
        edtName.setText(UserRepository.currentUser.getName());

        txtNameProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNameProfile.setVisibility(View.GONE);
                edtName.setVisibility(View.VISIBLE);
                edtName.requestFocus();
            }
        });

        btnUpdateUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String username = edtUserName.getText().toString();
                String phone = edtPhone.getText().toString();
                UserRepository.currentUser.setName(name);
                UserRepository.currentUser.setEmail(email);
                UserRepository.currentUser.setUsername(username);
                UserRepository.currentUser.setPhone(phone);

                Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Successful!!", Snackbar.LENGTH_LONG);
                snackBar.show();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(CurrentUserProfileFragment.this).attach(CurrentUserProfileFragment.this).commit();
            }
        });

        btnLogOut = rootView.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                getActivity().finish();
                startActivity(intent);
            }
        });
        if (UserRepository.currentUser.getRole().equals("manager")) {
            LinearLayout spaContainer = rootView.findViewById(R.id.spaInfoContainer);
            spaContainer.setVisibility(View.VISIBLE);
            ImageButton btnEdit = rootView.findViewById(R.id.btnEdit);
            branch = new Branch(R.drawable.spa_01, "Rosie Spa", "Nguyên hồng", "active", UserRepository.userList.get("manager"));
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditBranchActivity.class);
                    intent.putExtra("title", branch.getTitle());
                    intent.putExtra("address", branch.getAddress());
                    intent.putExtra("image", branch.getImage());
                    intent.putExtra("status", branch.getStatus());
                    intent.putExtra("manager", branch.getManager());
                    startActivityForResult(intent, 1000);
                }
            });
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1000 && resultCode == AppCompatActivity.RESULT_OK) {
            Toast.makeText(getActivity(), "Cập nhật chi nhánh thành công", Toast.LENGTH_LONG).show();
            EditText valueSpa = rootView.findViewById(R.id.valueSPa);
            branch = (Branch) data.getSerializableExtra("branch");
            valueSpa.setText(branch.getTitle());
        }
    }
}
