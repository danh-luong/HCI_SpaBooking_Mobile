package rosie.com.rosiebeauty;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingManagerFragment extends Fragment {

    private View rootView;
    private FloatingActionButton btnSearchUser;
    private List<User> unacceptedListUser;

    public PendingManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pending_manager, container, false);
        RecyclerView historyTaskRecycler = (RecyclerView) rootView.findViewById(R.id.recycle_account);
        final List<User> userList = new ArrayList<>(UserRepository.userList.values());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        unacceptedListUser = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (!userList.get(i).isAccepted() && !userList.get(i).getStatus().equalsIgnoreCase("Decline")) {
                unacceptedListUser.add(userList.get(i));
            }
        }
        PendingManagerAdapter adapter = new PendingManagerAdapter(unacceptedListUser, getActivity());
        historyTaskRecycler.setLayoutManager(linearLayoutManager);
        historyTaskRecycler.setAdapter(adapter);

        final EditText edtSearchName = rootView.findViewById(R.id.edtSearchName);
        btnSearchUser = rootView.findViewById(R.id.btnSearchUser);
        btnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = edtSearchName.getText().toString();
                List<User> searchList = new ArrayList<>();
                for (int i = 0; i < unacceptedListUser.size(); i++) {
                    if (unacceptedListUser.get(i).getName().toLowerCase().contains(searchName.toLowerCase())) {
                        searchList.add(unacceptedListUser.get(i));
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                PendingManagerAdapter adapter = new PendingManagerAdapter(searchList, getActivity());
                RecyclerView historyTaskRecycler = (RecyclerView) rootView.findViewById(R.id.recycle_account);
                historyTaskRecycler.setLayoutManager(linearLayoutManager);
                historyTaskRecycler.setAdapter(adapter);
            }
        });

        return rootView;
    }

}
