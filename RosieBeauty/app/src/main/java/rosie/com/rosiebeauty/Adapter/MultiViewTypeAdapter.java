package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Model.MultiViewModel;
import rosie.com.rosiebeauty.R;
import rosie.com.rosiebeauty.SlideshowAdapter;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<MultiViewModel> dataSet;
    Context mContext;
    int total_types;

    public static class SlideshowTypeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSlideshow;

        public SlideshowTypeViewHolder(View itemView) {
            super(itemView);
            this.imgSlideshow = itemView.findViewById(R.id.imageSlideshow);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;
        ImageView image;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.txtTitle);
            this.image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }


    public MultiViewTypeAdapter(ArrayList<MultiViewModel> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case MultiViewModel.TYPE_SLIDESHOW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideshow_layout, parent, false);

                final ViewGroup.LayoutParams lp = view.getLayoutParams();
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams)
                {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams)lp;
                    sglp.setFullSpan(true);
                }

                return new SlideshowTypeViewHolder(view);
            case MultiViewModel.TYPE_IMAGE_WITH_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_with_title, parent, false);
                return new ImageTypeViewHolder(view);

        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return MultiViewModel.TYPE_SLIDESHOW;
            case 1:
                return MultiViewModel.TYPE_IMAGE_WITH_TEXT;

        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        MultiViewModel object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case MultiViewModel.TYPE_SLIDESHOW:
                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setBackgroundResource(R.drawable.slideshow1);
                    break;
                case MultiViewModel.TYPE_IMAGE_WITH_TEXT:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
