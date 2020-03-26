package rosie.com.rosiebeauty.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Listener.EditServiceButtonListener;
import rosie.com.rosiebeauty.Listener.RemoveServiceButtonListener;
import rosie.com.rosiebeauty.Model.Service;
import rosie.com.rosiebeauty.R;

public class ServiceRecycleViewAdapter extends RecyclerView.Adapter<ServiceRecycleViewAdapter.ViewHolder> {
    private ArrayList<Service> services;
    private AppCompatActivity currentActivity;
    private Fragment currentFragment;

    public ServiceRecycleViewAdapter(ArrayList<Service> services, AppCompatActivity currentActivity, Fragment currentFragment) {
        this.services = services;
        this.currentActivity = currentActivity;
        this.currentFragment = currentFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_service, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView txtTitle = cardView.findViewById(R.id.txtTitle);
        TextView txtPrice = cardView.findViewById(R.id.txtPrice);
        TextView txtPromotion = cardView.findViewById(R.id.txtPromotion);
        TextView txtCategory = cardView.findViewById(R.id.txtCategory);
        ImageView imageView = cardView.findViewById(R.id.itemImage);
        BootstrapButton btnEditService = cardView.findViewById(R.id.btnEdit);
        ImageButton btnRemoveService = cardView.findViewById(R.id.btnRemoveService);

        Service service = services.get(position);
        txtTitle.setText(service.getTitle());
        txtPrice.setText(service.getPrice() + "VND");
        txtPromotion.setText("-" + service.getPromotion() + "%");
        txtCategory.setText(service.getCategory());
        imageView.setImageResource(service.getImage());

        //bấm vào edit pop up fragment edit
        btnEditService.setOnClickListener(
                new EditServiceButtonListener(
                        service.getTitle(), service.getPrice(),
                        service.getPromotion(), service.getImage(), service.getCategory(), currentActivity));
        btnRemoveService.setOnClickListener(new RemoveServiceButtonListener(holder.cardView, currentActivity, currentFragment, service));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
        }
    }
}
