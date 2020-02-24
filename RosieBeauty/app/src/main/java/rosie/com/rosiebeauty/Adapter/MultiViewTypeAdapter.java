package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import rosie.com.rosiebeauty.Model.MultiViewModel;
import rosie.com.rosiebeauty.R;
import rosie.com.rosiebeauty.SearchFragment;
import rosie.com.rosiebeauty.SlideshowAdapter;
import rosie.com.rosiebeauty.SpaServiceDetailFragment;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<MultiViewModel> dataSet;
    Context mContext;
    int total_types;
    private MaterialSearchView searchView;
    private Fragment triggerFragment;

    public void setSearchView(MaterialSearchView searchView) {
        this.searchView = searchView;
    }

    public Fragment getTriggerFragment() {
        return triggerFragment;
    }

    public void setTriggerFragment(Fragment triggerFragment) {
        this.triggerFragment = triggerFragment;
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
        ImageView icon;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.txtTitle);
            this.image = (ImageView) itemView.findViewById(R.id.imageView);
            if (itemView.findViewById(R.id.suggestion_icon) != null) {
                this.icon = (ImageView) itemView.findViewById(R.id.suggestion_icon);

            }
        }
    }

    public static class ServiceCardListHolder extends RecyclerView.ViewHolder {

        ImageView imgService;
        TextView txtName;
        TextView txtPrice;

        public ServiceCardListHolder(@NonNull View itemView) {
            super(itemView);
            this.imgService = (ImageView) itemView.findViewById(R.id.imageService);
            this.txtName = (TextView) itemView.findViewById(R.id.txtNameService);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPriceService);
        }
    }

    public static class SectionTitleViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;

        public SectionTitleViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.section_title);
        }
    }

    public static class RecylerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView itemRecyler;

        public RecylerViewHolder(View itemView) {
            super(itemView);
            this.itemRecyler = (RecyclerView) itemView.findViewById(R.id.item_recyler_view);
        }
    }

    public static class SquareIconViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public SquareIconViewHolder(View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.square_icon_image);
            this.text = (TextView) itemView.findViewById(R.id.square_icon_text);
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
                return new ImageTypeViewHolder(view);
            case MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_item, parent, false);
                return new ImageTypeViewHolder(view);
            case MultiViewModel.TYPE_TEXT_INSIDE_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_product_layout, parent, false);
                return new ImageTypeViewHolder(view);
            case MultiViewModel.TYPE_IMG_TEXT_PRICE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_card_list, parent, false);
                return new ServiceCardListHolder(view);
            case MultiViewModel.TYPE_SECTIN_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_title, parent, false);
                final ViewGroup.LayoutParams lp1 = view.getLayoutParams();
                if (lp1 instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp1;
                    sglp.setFullSpan(true);
                }
                return new SectionTitleViewHolder(view);
            case MultiViewModel.TYPE_RECYLERVIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_view, parent, false);
                final ViewGroup.LayoutParams lp2 = view.getLayoutParams();
                if (lp2 instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp2;
                    sglp.setFullSpan(true);
                }
                return new RecylerViewHolder(view);
            case MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.square_icon_with_text_below, parent, false);
                return new SquareIconViewHolder(view);
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
            case 3:
                return MultiViewModel.TYPE_TEXT_INSIDE_IMAGE;
            case 4:
                return MultiViewModel.TYPE_IMG_TEXT_PRICE;

            case 5:
                return MultiViewModel.TYPE_SECTIN_TITLE;
            case 6:
                return MultiViewModel.TYPE_RECYLERVIEW;
            case 7:
                return MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW;
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
                                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setCurrentItem((((SlideshowTypeViewHolder) holder).imgSlideshow.getCurrentItem() + 1) % 5);
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
                    ((ImageTypeViewHolder) holder).icon.setImageResource(object.iconId);
                    if (object.styleOfContent == 1) {
                        ((ImageTypeViewHolder) holder).txtType.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchView.showSearch();
                                searchView.setQuery(object.text, false);
                            }
                        });
                        ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchView.showSearch();
                                searchView.setQuery(object.text, false);

                            }
                        });
                    } else if (object.styleOfContent == 2) {
                        ((ImageTypeViewHolder) holder).txtType.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SearchFragment searchFragment = (SearchFragment) triggerFragment;
                                searchFragment.showProductDetail(new SpaServiceDetailFragment());
                            }
                        });
                        ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SearchFragment searchFragment = (SearchFragment) triggerFragment;
                                searchFragment.showProductDetail(new SpaServiceDetailFragment());

                            }
                        });
                    }
                    break;
                case MultiViewModel.TYPE_TEXT_INSIDE_IMAGE:
                    ((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);
                    ((ImageTypeViewHolder) holder).txtType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SearchFragment searchFragment = (SearchFragment) triggerFragment;
                            searchFragment.showProductDetail(new SpaServiceDetailFragment());
                        }
                    });
                    ((ImageTypeViewHolder) holder).image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SearchFragment searchFragment = (SearchFragment) triggerFragment;
                            searchFragment.showProductDetail(new SpaServiceDetailFragment());
                        }
                    });
                    break;
                case MultiViewModel.TYPE_IMG_TEXT_PRICE:
                    ((ServiceCardListHolder) holder).imgService.setImageResource(object.data);
                    ((ServiceCardListHolder) holder).txtName.setText(object.text);
                    ((ServiceCardListHolder) holder).txtPrice.setText(object.text);
                    break;
                case MultiViewModel.TYPE_SECTIN_TITLE:
                    ((SectionTitleViewHolder) holder).txtType.setText(object.text);
                    break;
                case MultiViewModel.TYPE_RECYLERVIEW:
                    ArrayList<MultiViewModel> gridViewModelArrayList = new ArrayList();
                    MultiViewModel gridViewModel = null;

                    for (MultiViewModel.RecyclerChildItem item : object.recyclerChildItems) {
                        gridViewModel = new MultiViewModel(item.type, item.text, item.image);
                        gridViewModelArrayList.add(gridViewModel);
                    }
                    MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, mContext);

                    StaggeredGridLayoutManager lm =
                            new StaggeredGridLayoutManager(object.reclyerSpan, object.orientation);
                    ((RecylerViewHolder) holder).itemRecyler.setLayoutManager(lm);
                    ((RecylerViewHolder) holder).itemRecyler.setItemAnimator(new DefaultItemAnimator());
                    ((RecylerViewHolder) holder).itemRecyler.setAdapter(adapter);
                    break;
                case MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW:
                    ((SquareIconViewHolder) holder).text.setText(object.text);
                    ((SquareIconViewHolder) holder).image.setImageResource(object.data);
                    ((SquareIconViewHolder) holder).image.setClipToOutline(true);
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
