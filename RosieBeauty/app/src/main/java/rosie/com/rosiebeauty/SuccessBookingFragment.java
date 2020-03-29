package rosie.com.rosiebeauty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class SuccessBookingFragment extends Fragment {
    RelativeLayout reason, button_container;
    ImageView barcode;

    public SuccessBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment_appointment_detail, container, false);
        barcode = rootView.findViewById(R.id.barcode);
        barcode.setVisibility(View.GONE);
        reason = rootView.findViewById(R.id.reason);
        reason.setVisibility(View.GONE);
        button_container = rootView.findViewById(R.id.button_container);
        button_container.setVisibility(View.GONE);
        return rootView;
    }
}
