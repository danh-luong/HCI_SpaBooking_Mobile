package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

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

        public CreateServiceHolder(@NonNull View itemView) {
            super(itemView);
            this.edtNameService = (EditText) itemView.findViewById(R.id.edtNameService);
            this.edtDesService = (EditText) itemView.findViewById(R.id.edtDesService);
            this.edtPrice = (EditText) itemView.findViewById(R.id.edtPrice);
            this.edtQPromotion = (EditText) itemView.findViewById(R.id.edtQPromotion);
            this.edtPricePromo = (EditText) itemView.findViewById(R.id.edtPricePromo);
            this.edtStartDay = (EditText) itemView.findViewById(R.id.edtStartDay);
            this.edtEndDay = (EditText) itemView.findViewById(R.id.edtEndDay);

            this.spinnerCatagory = (Spinner) itemView.findViewById(R.id.spinnerCatagory);
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
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
