package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.ManagerViewModel;
import rosie.com.rosiebeauty.R;

public class ManagerViewTypeAdapter extends RecyclerView.Adapter {
    Context mContext;
    private ArrayList<ManagerViewModel> dataSet;


    public ManagerViewTypeAdapter(ArrayList<ManagerViewModel> dataSet, Context mContext) {
        this.mContext = mContext;
        this.dataSet = dataSet;
    }

    public static class CreateServiceHolder extends RecyclerView.ViewHolder {
        EditText edtNameService, edtDesService, edtPrice, edtQPromotion, edtPricePromo, edtStartDay, edtEndDay;
        Spinner spinnerCatagory;
        Button btnCreateService;

        public CreateServiceHolder(@NonNull View itemView) {
            super(itemView);
            this.edtNameService = (EditText) itemView.findViewById(R.id.edtNameService);
            this.edtDesService = (EditText) itemView.findViewById(R.id.edtDesService);
            this.edtPrice = (EditText) itemView.findViewById(R.id.edtPrice);
            this.edtQPromotion = (EditText) itemView.findViewById(R.id.edtQPromotion);
            this.edtPricePromo = (EditText) itemView.findViewById(R.id.edtPricePromo);
            this.edtStartDay = (EditText) itemView.findViewById(R.id.edtStartDay);
            this.edtEndDay = (EditText) itemView.findViewById(R.id.edtEndDay);
            this.btnCreateService = (Button) itemView.findViewById(R.id.btnCreateService);
            this.spinnerCatagory = (Spinner) itemView.findViewById(R.id.spinnerCatagory);
        }

    }

    public static class AppointmentItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtAppointmentCode;
        TextView txtName;
        TextView txtBookingDate;
        TextView txtAppointmentDate;
        TextView txtPayPrice;

        public AppointmentItemViewHolder(View itemView) {
            super(itemView);
            this.txtAppointmentCode = (TextView) itemView.findViewById(R.id.txtBookingCode);
            this.txtName = (TextView) itemView.findViewById(R.id.txtNameCustomer);
            this.txtBookingDate = (TextView) itemView.findViewById(R.id.txtBookingDate);
            this.txtAppointmentDate = (TextView) itemView.findViewById(R.id.txtAppointmentDate);
            this.txtPayPrice = (TextView) itemView.findViewById(R.id.txtPayPrice);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ManagerViewModel.CREATE_NEW_SERVICE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_new_service, parent, false);
                return new CreateServiceHolder(view);
            case ManagerViewModel.TYPE_APPOINTMENT_MANAGER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_booking_item_manager, parent, false);
                return new AppointmentItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ManagerViewModel object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {
                case ManagerViewModel.CREATE_NEW_SERVICE:
                    ((CreateServiceHolder) holder).edtNameService.setText(object.getEdtNameService());
                    ((CreateServiceHolder) holder).edtDesService.setText(object.getEdtDesService());
                    ((CreateServiceHolder) holder).edtPrice.setText(object.getEdtPrice());
                    ((CreateServiceHolder) holder).edtQPromotion.setText(object.getEdtQPromotion());
                    ((CreateServiceHolder) holder).edtPricePromo.setText(object.getEdtPricePromo());
                    ((CreateServiceHolder) holder).edtStartDay.setText(object.getEdtStartDay());
                    ((CreateServiceHolder) holder).edtEndDay.setText(object.getEdtEndDay());
                    ((CreateServiceHolder) holder).spinnerCatagory.getTag(object.getSpinnerCatagory());
                    ((CreateServiceHolder) holder).btnCreateService.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "Successful!", Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
                case ManagerViewModel.TYPE_APPOINTMENT_MANAGER:
                    ((AppointmentItemViewHolder) holder).txtAppointmentCode.setText(object.appointment.appointmentCode);
                    ((AppointmentItemViewHolder) holder).txtName.setText(object.appointment.name);
                    ((AppointmentItemViewHolder) holder).txtBookingDate.setText(object.appointment.bookingDate);
                    ((AppointmentItemViewHolder) holder).txtAppointmentDate.setText(object.appointment.appointmentDate);
                    ((AppointmentItemViewHolder) holder).txtPayPrice.setText(object.appointment.payPrice);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getType();
    }
}
