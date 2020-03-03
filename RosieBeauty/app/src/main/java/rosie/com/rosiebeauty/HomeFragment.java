package rosie.com.rosiebeauty;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.icu.util.Currency;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;
    private String[] service_names, address;
    private int[] icons, countComment, txtPromotion, price;
    private Double[] rateStar;
    Locale locale = Locale.forLanguageTag("vi-VN");
    java.text.NumberFormat formatPrice = NumberFormat.getCurrencyInstance(locale);


    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Recycler View
        gridViewModelArrayList = new ArrayList();

        prepareData();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(gridViewModelArrayList, this.getActivity().getApplicationContext());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRecyclerView = rootView.findViewById(R.id.suggestionView);
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    void prepareData() {
        service_names = new String[]{
                "Sấy tóc tạo kiểu",
                "Cắt tóc tạo kiểu",
                "Nhuộm tóc",
                "Nhuộm tóc dài",
                "Phủ bóng",
                "Nhuộm Highlight",
                "Uốn tóc",
                "Hấp tóc",
                "Duỗi tóc",
        };
        icons = new int[]{
                R.drawable.ser_blow_dry,
                R.drawable.ser_cutting_hair_girl,
                R.drawable.ser_dye_hair,
                R.drawable.ser_dye_long_hair,
                R.drawable.ser_gloss_hair,
                R.drawable.ser_highlight_hair,
                R.drawable.ser_perm,
                R.drawable.ser_steaming_hair,
                R.drawable.ser_straighten_hair,
        };
        txtPromotion = new int[]{
                5,
                20,
                30,
                10,
                10,
                20,
                10,
                5,
                50
        };
        rateStar = new Double[]{
                3.0,
                4.3,
                5.0,
                4.0,
                3.9,
                4.5,
                2.0,
                3.9,
                4.0,
                5.0
        };
        countComment = new int[]{
                1, 4, 5, 7, 8, 2, 4, 5, 6
        };
        address = new String[]{
                "CN: Nguyễn Thiện Thuật, Q.3",
                "CN: Võ Văn Tần, Q.3",
                "CN: Phan Xích Long, Q.Phú Nhuận",
                "CN: Hồ Hảo Hớn, Q.1",
                "CN: Nguyễn Thiện Thuật, Q.3",
                "CN: Võ Văn Tần, Q.3",
                "CN: Phan Xích Long, Q.Phú Nhuận",
                "CN: Hồ Hảo Hớn, Q.1",
                "CN: Nguyễn Thiện Thuật, Q.3",
        };
        price = new int[]{
                200000, 300000, 500000, 800000, 2000000, 500000, 400000, 600000, 1500000,
        };


        MultiViewModel gridViewModel = null;
        List<MultiViewModel> childMultiViewModels = new ArrayList<>();
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_facial_treatment));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_hair));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_massage));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_makeup));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_nails));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_waxing));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_steam_bath));
        childMultiViewModels.add(new MultiViewModel(MultiViewModel.TYPE_SQUARE_ICON_TEXT_BELOW, R.drawable.ca_burn_fat));

        for (int i = 0; i < service_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SLIDESHOW, "", R.drawable.slide_show0);
                gridViewModelArrayList.add(gridViewModel);
//                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Top Sale");
//                gridViewModelArrayList.add(gridViewModel);
//                Random random = new Random();
//                int min = 200;
//                int max = 500;
//                int price = 0;
//                List<MultiViewModel> childTopPromotions = new ArrayList<>();
//                int sale[] = new int[]{
//                        100, 80, 60, 55, 40, 33, 20, 10
//                };
//                for (int j = 0; j < 8; j++) {
//                    price = random.nextInt((max - min) + 1) + min;
//                    childTopPromotions.add(new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, service_names[j], icons[j], "$" + price + ".00", MultiViewModel.HAS_PROMOTION, "$" + (price - sale[j]) + ".00", MultiViewModel.WRAP_CONTENT));
//                }
//                gridViewModel =
//                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
//                                2, childTopPromotions);
//                gridViewModelArrayList.add(gridViewModel);

                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Danh mục dịch vụ");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel =
                        new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                                2, childMultiViewModels);
                gridViewModelArrayList.add(gridViewModel);

                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Giảm giá HOT");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, icons[i], service_names[i],
                        formatPrice.format(price[i]),formatPrice.format(price[i]-(price[i]*txtPromotion[i]/100)), MultiViewModel.HAS_PROMOTION,
                        rateStar[i], countComment[i], address[i], "-"+txtPromotion[i]+"%");
                gridViewModelArrayList.add(gridViewModel);
            } else {
                if (i % 2 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, icons[i], service_names[i],
                            formatPrice.format(price[i]),formatPrice.format(price[i]-(price[i]*txtPromotion[i]/100)), MultiViewModel.HAS_PROMOTION,
                            rateStar[i], countComment[i], address[i], "-"+txtPromotion[i]+"%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, icons[i], service_names[i],
                            formatPrice.format(price[i]),formatPrice.format(price[i]-(price[i]*txtPromotion[i]/100)), MultiViewModel.HAS_PROMOTION,
                            rateStar[i], countComment[i], address[i], "-"+txtPromotion[i]+"%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            }
        }

    }
}
