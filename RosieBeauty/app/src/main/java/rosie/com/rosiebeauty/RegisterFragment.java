package rosie.com.rosiebeauty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private String tabTitle;
    private Button btnRegister;

    public RegisterFragment(String tabTitle) {
        this.tabTitle = tabTitle;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        if (tabTitle.equals("Khách hàng")) {
            view = inflater.inflate(R.layout.fragment_register_user, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_register_manager, container, false);
        }
        return view;
    }
}
