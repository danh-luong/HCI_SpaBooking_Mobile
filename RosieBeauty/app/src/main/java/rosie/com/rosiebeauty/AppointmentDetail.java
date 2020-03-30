package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentDetail extends Fragment {
    Button btnCancle;
    RelativeLayout successBooking;

    public AppointmentDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View rootView = inflater.inflate(R.layout.fragment_appointment_detail, container, false);
        successBooking = rootView.findViewById(R.id.successBooking);
        successBooking.setVisibility(View.GONE);
        btnCancle = (Button) rootView.findViewById(R.id.btnCancel);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                        "Cuộc hẹn của bạn đã được huỷ!\nXin cảm ơn!", Snackbar.LENGTH_LONG);
                snackBar.show();
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }

}
