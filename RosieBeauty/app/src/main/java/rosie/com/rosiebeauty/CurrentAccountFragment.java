package rosie.com.rosiebeauty;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentAccountFragment extends Fragment {

    private View rootView;
    private FloatingActionButton btnCreateNewEmployee;

    public CurrentAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current_account, container, false);
        RecyclerView historyTaskRecycler = (RecyclerView) rootView.findViewById(R.id.recycle_account);
        final List<User> userList = new ArrayList<>(UserRepository.userList.values());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        CardViewAccountAdapter adapter = new CardViewAccountAdapter(userList, getActivity());
        historyTaskRecycler.setLayoutManager(linearLayoutManager);
        historyTaskRecycler.setAdapter(adapter);
        btnCreateNewEmployee = rootView.findViewById(R.id.btnCreateNewEmployee);
        btnCreateNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateNewEmployeeActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton btnSearchUser = rootView.findViewById(R.id.btnSearchUser);
        final EditText edtSearchName = rootView.findViewById(R.id.edtSearchName);
        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = edtSearchName.getText().toString();
                List<User> searchList = new ArrayList<>();
                List<User> currentUserList = new ArrayList<>(UserRepository.userList.values());
                for (int i = 0; i < currentUserList.size(); i++) {
                    if (currentUserList.get(i).getName().toLowerCase().contains(searchName.toLowerCase())) {
                        searchList.add(currentUserList.get(i));
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                CardViewAccountAdapter adapter = new CardViewAccountAdapter(searchList, getActivity());
                RecyclerView historyTaskRecycler = (RecyclerView) rootView.findViewById(R.id.recycle_account);
                historyTaskRecycler.setLayoutManager(linearLayoutManager);
                historyTaskRecycler.setAdapter(adapter);
            }
        });

        return rootView;
    }

}
