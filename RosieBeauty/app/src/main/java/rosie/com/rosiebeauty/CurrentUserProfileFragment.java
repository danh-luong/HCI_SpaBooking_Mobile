package rosie.com.rosiebeauty;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import rosie.com.rosiebeauty.Data.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentUserProfileFragment extends Fragment {

    private EditText edtUserName, edtPhone, edtEmail, edtName;
    private View rootView;
    private TextView txtNameProfile;
    private Button btnUpdateUserProfile;

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

        return rootView;
    }

}
