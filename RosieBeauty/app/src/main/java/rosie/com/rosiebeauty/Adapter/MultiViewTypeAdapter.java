package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import rosie.com.rosiebeauty.Model.MultiViewModel;
import rosie.com.rosiebeauty.R;
import rosie.com.rosiebeauty.SlideshowAdapter;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<MultiViewModel> dataSet;
    Context mContext;
    int total_types;
    private MaterialSearchView searchView;

    public void setSearchView(MaterialSearchView searchView) {
        this.searchView = searchView;
    }

    public static class SlideshowTypeViewHolder extends RecyclerView.ViewHolder {

        ViewPager imgSlideshow;

        public SlideshowTypeViewHolder(View itemView) {
            super(itemView);
            this.imgSlideshow = itemView.findViewById(R.id.home_slideshow);
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
                if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
                    sglp.setFullSpan(true);
                }

                return new SlideshowTypeViewHolder(view);
            case MultiViewModel.TYPE_IMAGE_WITH_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_with_title, parent, false);
                final ViewGroup.LayoutParams lp1 = view.getLayoutParams();
                if (lp1 instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp1;
                    sglp.setFullSpan(true);
                }
                return new ImageTypeViewHolder(view);
            case MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false);
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
            case 2:
                return MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT;

        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final MultiViewModel object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case MultiViewModel.TYPE_SLIDESHOW:
                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setBackgroundResource(R.drawable.slideshow1);
                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setAdapter(new SlideshowAdapter(mContext));
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((SlideshowTypeViewHolder) holder).imgSlideshow.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setCurrentItem((((SlideshowTypeViewHolder) holder).imgSlideshow.getCurrentItem() + 1) % ((SlideshowTypeViewHolder) holder).imgSlideshow.getChildCount());
                                }
                            });
                        }
                    };
                    Timer swipeTimer;
                    swipeTimer = new Timer();
                    swipeTimer.schedule(timerTask, 1000, 3000);
                    break;
                case MultiViewModel.TYPE_IMAGE_WITH_TEXT:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    ((ImageTypeViewHolder) holder).image.setClipToOutline(true);
                    break;
                case MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    ((ImageTypeViewHolder) holder).txtType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            searchView.setQuery(object.text, false);
                        }
                    });
                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            searchView.setQuery(object.text, false);
                        }
                    });
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
