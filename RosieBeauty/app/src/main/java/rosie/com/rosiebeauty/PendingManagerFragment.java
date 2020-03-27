package rosie.com.rosiebeauty;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingManagerFragment extends Fragment {

    private View rootView;

    public PendingManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current_account, container, false);
        RecyclerView historyTaskRecycler = (RecyclerView) rootView.findViewById(R.id.recycle_account);
        final List<User> userList = new ArrayList<>(UserRepository.userList.values());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        List<User> unacceptedListUser = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (!userList.get(i).isAccepted() && !userList.get(i).getStatus().equalsIgnoreCase("Decline")) {
                unacceptedListUser.add(userList.get(i));
            }
        }
        PendingManagerAdapter adapter = new PendingManagerAdapter(unacceptedListUser, getActivity());
        historyTaskRecycler.setLayoutManager(linearLayoutManager);
        historyTaskRecycler.setAdapter(adapter);
        return rootView;
    }

}
