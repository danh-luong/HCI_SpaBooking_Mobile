package rosie.com.rosiebeauty.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;


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
        TextView txtPriceAfterPromotion;
        RelativeLayout relativeLayout;

        public ServiceCardListHolder(@NonNull View itemView) {
            super(itemView);
            this.imgService = (ImageView) itemView.findViewById(R.id.imageService);
            this.txtName = (TextView) itemView.findViewById(R.id.txtNameService);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPriceService);
            this.txtPriceAfterPromotion = (TextView) itemView.findViewById(R.id.txtPriceAfterPromotion);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.service_card_item_container);
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

    public static class AppointmentItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtAppointmentCode;
        TextView txtBookingDate;
        TextView txtAppointmentDate;
        TextView txtPayPrice;

        public AppointmentItemViewHolder(View itemView) {
            super(itemView);
            this.txtAppointmentCode = (TextView) itemView.findViewById(R.id.txtBookingCode);
            this.txtBookingDate = (TextView) itemView.findViewById(R.id.txtBookingDate);
            this.txtAppointmentDate = (TextView) itemView.findViewById(R.id.txtAppointmentDate);
            this.txtPayPrice = (TextView) itemView.findViewById(R.id.txtPayPrice);
        }
    }

    public static class FavoriteItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtAddress;
        RatingBar ratingBar;

        public FavoriteItemViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.itemImage);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            this.txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            this.ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
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
            case MultiViewModel.TYPE_SECTION_TITLE:
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
            case MultiViewModel.TYPE_APPOINTMENT_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_booking_item, parent, false);
                return new AppointmentItemViewHolder(view);
            case MultiViewModel.TYPE_FAVORITE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
                return new FavoriteItemViewHolder(view);
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
                return MultiViewModel.TYPE_SECTION_TITLE;
            case 6:
                return MultiViewModel.TYPE_RECYLERVIEW;
            case 7:
                return MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW;
            case 8:
                return MultiViewModel.TYPE_APPOINTMENT_ITEM;
            case 9:
                return MultiViewModel.TYPE_FAVORITE_ITEM;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final MultiViewModel object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case MultiViewModel.TYPE_SLIDESHOW:
                    ((SlideshowTypeViewHolder) holder).imgSlideshow.setClipToOutline(true);
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
                    ((ServiceCardListHolder) holder).relativeLayout.getLayoutParams().width = object.width;
                    ((ServiceCardListHolder) holder).imgService.setImageResource(object.data);
                    ((ServiceCardListHolder) holder).imgService.setClipToOutline(true);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        ((ServiceCardListHolder) holder).imgService.setOutlineProvider(new ViewOutlineProvider() {
                            @Override
                            public void getOutline(View view, Outline outline) {
                                int curveRadius = 30;
                                outline.setRoundRect(0, 0, view.getWidth(), (view.getHeight() + curveRadius), curveRadius);
                            }
                        });

                    }
                    ((ServiceCardListHolder) holder).txtPriceAfterPromotion.setClipToOutline(true);
                    ((ServiceCardListHolder) holder).txtName.setText(object.text);
                    if (object.hasPromotion == MultiViewModel.HAS_PROMOTION) {
                        ((ServiceCardListHolder) holder).txtPrice.setText(object.price);
                        ((ServiceCardListHolder) holder).txtPrice.setPaintFlags(((ServiceCardListHolder) holder).txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        ((ServiceCardListHolder) holder).txtPriceAfterPromotion.setText(object.priceAfterPromotion);
                    } else {
                        ((ServiceCardListHolder) holder).txtPrice.setText("");
                        ((ServiceCardListHolder) holder).txtPriceAfterPromotion.setText(object.price);
                        ((ServiceCardListHolder) holder).txtPriceAfterPromotion.setTextColor(Color.parseColor("#015FA3"));
                    }
                    break;
                case MultiViewModel.TYPE_SECTION_TITLE:
                    ((SectionTitleViewHolder) holder).txtType.setText(object.text);
                    break;
                case MultiViewModel.TYPE_RECYLERVIEW:
                    ArrayList<MultiViewModel> gridViewModelArrayList = new ArrayList();
                    MultiViewModel gridViewModel = null;

                    for (MultiViewModel item : object.multiViewModels) {
                        gridViewModelArrayList.add(item);
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
                case MultiViewModel.TYPE_APPOINTMENT_ITEM:
                    ((AppointmentItemViewHolder) holder).txtAppointmentCode.setText(object.appointment.appointmentCode);
                    ((AppointmentItemViewHolder) holder).txtBookingDate.setText(object.appointment.bookingDate);
                    ((AppointmentItemViewHolder) holder).txtAppointmentDate.setText(object.appointment.appointmentDate);
                    ((AppointmentItemViewHolder) holder).txtPayPrice.setText(object.appointment.payPrice);
                    break;
                case MultiViewModel.TYPE_FAVORITE_ITEM:
                    ((FavoriteItemViewHolder) holder).imageView.setImageResource(object.favoriteItem.getImage());
                    ((FavoriteItemViewHolder) holder).imageView.setClipToOutline(true);
                    ((FavoriteItemViewHolder) holder).txtTitle.setText(object.favoriteItem.getTitle());
                    ((FavoriteItemViewHolder) holder).txtAddress.setText(object.favoriteItem.getLocation());
                    ((FavoriteItemViewHolder) holder).ratingBar.setRating(object.favoriteItem.getStar());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        ((FavoriteItemViewHolder) holder).imageView.setOutlineProvider(new ViewOutlineProvider() {
                            @Override
                            public void getOutline(View view, Outline outline) {
                                int curveRadius = 30;
                                outline.setRoundRect(0, 0, (view.getWidth()+ curveRadius), view.getHeight() , curveRadius);
                            }
                        });

                    }
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
