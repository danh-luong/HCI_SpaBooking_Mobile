package rosie.com.rosiebeauty.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import rosie.com.rosiebeauty.ManagerActivity;
import rosie.com.rosiebeauty.Model.ManagerViewModel;
import rosie.com.rosiebeauty.R;
import rosie.com.rosiebeauty.ServiceManagementFragment;

public class ManagerViewTypeAdapter extends RecyclerView.Adapter implements DatePickerDialog.OnDateSetListener {
    Context mContext;
    private ArrayList<ManagerViewModel> dataSet;
    private boolean isEndDate = false;
    private EditText edtStartDay;
    private EditText edtEndDay;
    private Spinner spinnerCatagory;


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
                    edtStartDay = ((CreateServiceHolder) holder).edtStartDay;
                    edtStartDay.setKeyListener(null);
                    edtStartDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isEndDate = false;
                            showDatePickerDialog();
                        }
                    });
                    ((CreateServiceHolder) holder).edtEndDay.setText(object.getEdtEndDay());
                    edtEndDay = ((CreateServiceHolder) holder).edtEndDay;
                    edtEndDay.setKeyListener(null);
                    edtEndDay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isEndDate = true;
                            showDatePickerDialog();
                        }
                    });
                    ((CreateServiceHolder) holder).spinnerCatagory.getTag(object.getSpinnerCatagory());
                    ((CreateServiceHolder) holder).btnCreateService.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_manager, new ServiceManagementFragment()).commit();
                            ((AppCompatActivity) mContext).setTitle("Dịch vụ");
                            ((ManagerActivity) mContext).selectService();
                            Toast.makeText(mContext, "Tạo dịch vụ thành công", Toast.LENGTH_LONG).show();
                        }
                    });
                    spinnerCatagory = ((CreateServiceHolder) holder).spinnerCatagory;
                    ArrayList<String> categoriesList = new ArrayList<>();
                    categoriesList.add("Massage");
                    categoriesList.add("Làm tóc");
                    categoriesList.add("Dưỡng da");
                    categoriesList.add("Làm móng");
                    categoriesList.add("Trang điểm");
                    ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>
                            (mContext,
                                    android.R.layout.simple_spinner_dropdown_item, categoriesList);
                    spinnerCatagory.setAdapter(categorySpinnerAdapter);
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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String strMonth = month + "";
        String strDayOfMonth = dayOfMonth + "";
        if (strMonth.length() == 1) strMonth = "0" + month;
        if (strDayOfMonth.length() == 1) strDayOfMonth = "0" + dayOfMonth;
        String date = strMonth + "/" + strDayOfMonth + "/" + year;
        if (isEndDate) {
            edtEndDay.setText(date);
        } else {
            edtStartDay.setText(date);
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mContext,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
