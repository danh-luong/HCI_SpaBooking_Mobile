package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.MultiViewModel;
import rosie.com.rosiebeauty.R;

public class ManagerViewTypeAdapter extends RecyclerView.Adapter {
    Context mContext;
    private ArrayList<MultiViewModel> dataSet;

    public ManagerViewTypeAdapter(ArrayList<MultiViewModel> dataSet, Context mContext) {
        this.mContext = mContext;
        this.dataSet = dataSet;
    }

    public static class CreateServiceHolder extends RecyclerView.ViewHolder {
        TextView txtTitleCreateService, txtNameService, txtPhoto, txtDesService, txtPrice, txtCatagory,
                quantityPromotion, txtPromotion, txtStartDay, txtEndDay, txtTitlePromotion, txtPromotionInWeek, txtPromotionAtWeekend;
        EditText edtNameService, edtDesService, edtPrice, edtQPromotion, edtPricePromo, edtStartDay, edtEndDay;
        ImageButton btnPhoto;
        ImageView viewPhoto;
        Spinner spinnerCatagory;
        Button btnCreateService;

        public CreateServiceHolder(@NonNull View itemView) {
            super(itemView);
            this.txtTitleCreateService = (TextView) itemView.findViewById(R.id.txtTitleCreateService);
            this.txtNameService = (TextView) itemView.findViewById(R.id.txtNameService);
            this.txtPhoto = (TextView) itemView.findViewById(R.id.txtPhoto);
            this.txtDesService = (TextView) itemView.findViewById(R.id.txtDesService);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            this.txtCatagory = (TextView) itemView.findViewById(R.id.txtCatagory);
            this.quantityPromotion = (TextView) itemView.findViewById(R.id.quantityPromotion);
            this.txtPromotion = (TextView) itemView.findViewById(R.id.txtPromotion);
            this.txtStartDay = (TextView) itemView.findViewById(R.id.txtStartDay);
            this.txtEndDay = (TextView) itemView.findViewById(R.id.txtEndDay);
            this.txtTitlePromotion = (TextView) itemView.findViewById(R.id.txtTitlePromotion);
            this.txtPromotionInWeek = (TextView) itemView.findViewById(R.id.txtPromotionInWeek);
            this.txtPromotionAtWeekend = (TextView) itemView.findViewById(R.id.txtPromotionAtWeekend);

            this.edtNameService = (EditText) itemView.findViewById(R.id.edtNameService);
            this.edtDesService = (EditText) itemView.findViewById(R.id.edtDesService);
            this.edtPrice = (EditText) itemView.findViewById(R.id.edtPrice);
            this.edtQPromotion = (EditText) itemView.findViewById(R.id.edtQPromotion);
            this.edtPricePromo = (EditText) itemView.findViewById(R.id.edtPricePromo);
            this.edtStartDay = (EditText) itemView.findViewById(R.id.edtStartDay);
            this.edtEndDay = (EditText) itemView.findViewById(R.id.edtEndDay);

            this.btnPhoto = (ImageButton) itemView.findViewById(R.id.btnPhoto);
            this.viewPhoto = (ImageView) itemView.findViewById(R.id.viewPhoto);
            this.spinnerCatagory = (Spinner) itemView.findViewById(R.id.spinnerCatagory);
            this.btnCreateService = (Button) itemView.findViewById(R.id.btnCreateService);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case MultiViewModel.CREATE_NEW_SERVICE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_create_new_service, parent, false);
                return new CreateServiceHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MultiViewModel object = dataSet.get(position);
        if (object != null) {
            switch (object.type) {
                case MultiViewModel.CREATE_NEW_SERVICE:
                    ((CreateServiceHolder) holder).txtTitleCreateService.setText(object.text);
                    ((CreateServiceHolder) holder).txtNameService.setText(object.text);
                    ((CreateServiceHolder) holder).txtPhoto.setText(object.text);
                    ((CreateServiceHolder) holder).txtDesService.setText(object.text);
                    ((CreateServiceHolder) holder).txtPrice.setText(object.text);
                    ((CreateServiceHolder) holder).txtCatagory.setText(object.text);
                    ((CreateServiceHolder) holder).quantityPromotion.setText(object.text);
                    ((CreateServiceHolder) holder).txtPromotion.setText(object.text);
                    ((CreateServiceHolder) holder).txtStartDay.setText(object.text);
                    ((CreateServiceHolder) holder).txtEndDay.setText(object.text);
                    ((CreateServiceHolder) holder).txtTitlePromotion.setText(object.text);
                    ((CreateServiceHolder) holder).txtPromotionInWeek.setText(object.text);
                    ((CreateServiceHolder) holder).txtPromotionAtWeekend.setText(object.text);

                    ((CreateServiceHolder) holder).edtNameService.setText(object.text);
                    ((CreateServiceHolder) holder).edtDesService.setText(object.text);
                    ((CreateServiceHolder) holder).edtPrice.setText(object.text);
                    ((CreateServiceHolder) holder).edtQPromotion.setText(object.text);
                    ((CreateServiceHolder) holder).edtPricePromo.setText(object.text);
                    ((CreateServiceHolder) holder).edtStartDay.setText(object.text);
                    ((CreateServiceHolder) holder).edtEndDay.setText(object.text);

                    ((CreateServiceHolder) holder).spinnerCatagory.getTag(object.data);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
