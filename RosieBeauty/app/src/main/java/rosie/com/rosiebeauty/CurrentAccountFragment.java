package rosie.com.rosiebeauty;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        List<User> userList = new ArrayList<>(UserRepository.userList.values());
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
        return rootView;
    }

}
