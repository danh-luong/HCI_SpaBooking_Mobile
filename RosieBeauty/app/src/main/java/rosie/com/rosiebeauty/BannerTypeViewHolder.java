package rosie.com.rosiebeauty;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class BannerTypeViewHolder extends RecyclerView.ViewHolder {

    ImageView bannerImg;

    public BannerTypeViewHolder(View itemView) {
        super(itemView);
        this.bannerImg = itemView.findViewById(R.id.imgBanner);
    }
}