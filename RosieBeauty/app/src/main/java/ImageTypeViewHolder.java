import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import rosie.com.rosiebeauty.R;

public class ImageTypeViewHolder extends RecyclerView.ViewHolder {

    TextView txtType;
    ImageView image;

    public ImageTypeViewHolder(View itemView) {
        super(itemView);

        this.txtType = (TextView) itemView.findViewById(R.id.txtTitle);
        this.image = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
