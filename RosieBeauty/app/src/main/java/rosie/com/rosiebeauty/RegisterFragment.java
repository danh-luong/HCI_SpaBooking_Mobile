package rosie.com.rosiebeauty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private String tabTitle;

    public RegisterFragment(String tabTitle) {
        this.tabTitle = tabTitle;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (tabTitle.equals("Khách hàng")) {
            return inflater.inflate(R.layout.fragment_register_user, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_register_manager, container, false);
        }
    }
}