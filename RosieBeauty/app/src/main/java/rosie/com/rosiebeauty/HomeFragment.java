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
    private String[] pro_names, pro_address, near_names, near_address;
    private int[] pro_pic, pro_countComment, pro_txtPromotion, pro_price, near_pic, near_countComment, near_txtPromotion, near_price;
    private Double[] pro_rateStar, near_rateStar;
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
        //Promotion
        pro_names = new String[]{
                "Trang điểm cô dâu",
                "Massage toàn thân",
                "Đính đá móng tay",
                "Nhuộm tóc dài",
                "Sấy tóc tạo kiểu",
                "Liệu trình giảm béo"

        };
        pro_pic = new int[]{
                R.drawable.ser_codau,
                R.drawable.ser_massbody,
                R.drawable.ser_dinhda,
                R.drawable.ser_dye_long_hair,
                R.drawable.ser_blow_dry,
                R.drawable.giambeo
        };
        pro_txtPromotion = new int[]{
                48,
                28,
                20,
                10,
                10,
                10,
        };
        pro_rateStar = new Double[]{
                5.0,
                4.3,
                5.0,
                4.8,
                4.5,
                4.8
        };
        pro_countComment = new int[]{
                4, 5, 7, 8, 6,5
        };
        pro_address = new String[]{
                "Nguyễn Thiện Thuật, Q.3",
                "Võ Văn Tần, Q.3",
                "Phan Xích Long, Q.Phú Nhuận",
                "Hồ Hảo Hớn, Q.1",
                "Phan Xích Long, Q.Phú Nhuận",
                "Lê Văn Sĩ, Quận 3"
        };
        pro_price = new int[]{
                4000000, 3000000, 1500000, 800000, 200000, 3000000
        };
        //Nearly
        near_names = new String[]{
                "Trang điểm Hàn Quốc",
                "Sơn màu móng tay",
                "Nhuộm highlight",
                "Điều trị mụn",
                "Xông hơi thải độc",
                "Mặt nạ thải độc"

        };
        near_pic = new int[]{
                R.drawable.ser_korea,
                R.drawable.ser_sonmong,
                R.drawable.ser_highlight_hair,
                R.drawable.ser_trimun,
                R.drawable.xonghoinu,
                R.drawable.ser_facial1,
        };
        near_txtPromotion = new int[]{
                25,
                20,
                20,
                15,
                15,
                5,
        };
        near_rateStar = new Double[]{
                5.0,
                4.3,
                5.0,
                4.8,
                4.5,
                4.0,
        };
        near_countComment = new int[]{
                4, 5, 7, 8, 10,9
        };
        near_address = new String[]{
                "Phạm Văn Đồng, Q. Bình Thạnh",
                "Lê Quang Định, Q. Gò Vấp",
                "Phan Đăng Lưu, Q. Phú Nhuận",
                "Nơ Trang Long, Q. Bình Thạnh",
                "Phan Chu Trinh, Q. Bình Thạnh",
                "Nơ Trang Long, Q. Bình Thạnh",

        };
        near_price = new int[]{
                8000000, 500000, 800000, 600000, 1000000, 400000
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
        //Slideshow
        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SLIDESHOW, "", R.drawable.slide_show0);
        gridViewModelArrayList.add(gridViewModel);
        //Catagory
        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Danh mục dịch vụ");
        gridViewModelArrayList.add(gridViewModel);
        gridViewModel =
                new MultiViewModel(MultiViewModel.TYPE_RECYLERVIEW, MultiViewModel.ORIENTATION_HORIZONTAL,
                        2, childMultiViewModels);
        gridViewModelArrayList.add(gridViewModel);
        //Promotion
        for (int i = 0; i < pro_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Giảm giá HOT");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, pro_pic[i], pro_names[i],
                        formatPrice.format(pro_price[i]), formatPrice.format(pro_price[i] - (pro_price[i] * pro_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                        pro_rateStar[i], pro_countComment[i], pro_address[i], "-" + pro_txtPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            } else {
                if (i % 2 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, pro_pic[i], pro_names[i],
                            formatPrice.format(pro_price[i]), formatPrice.format(pro_price[i] - (pro_price[i] * pro_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                            pro_rateStar[i], pro_countComment[i], pro_address[i], "-" + pro_txtPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, pro_pic[i], pro_names[i],
                            formatPrice.format(pro_price[i]), formatPrice.format(pro_price[i] - (pro_price[i] * pro_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                            pro_rateStar[i], pro_countComment[i], pro_address[i], "-" + pro_txtPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            }
        }
        //Nearly
        for (int i = 0; i < pro_names.length; i++) {
            if (i == 0) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE, "Gần bạn");
                gridViewModelArrayList.add(gridViewModel);
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, near_pic[i], near_names[i],
                        formatPrice.format(near_price[i]), formatPrice.format(near_price[i] - (near_price[i] * near_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                        near_rateStar[i], near_countComment[i], near_address[i], "-" + near_txtPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            } else {
                if (i % 2 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, near_pic[i], near_names[i],
                            formatPrice.format(near_price[i]), formatPrice.format(near_price[i] - (near_price[i] * near_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                            near_rateStar[i], near_countComment[i], near_address[i], "-" + near_txtPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE, near_pic[i], near_names[i],
                            formatPrice.format(near_price[i]), formatPrice.format(near_price[i] - (near_price[i] * near_txtPromotion[i] / 100)), MultiViewModel.HAS_PROMOTION,
                            near_rateStar[i], near_countComment[i], near_address[i], "-" + near_txtPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            }
        }
    }
}
