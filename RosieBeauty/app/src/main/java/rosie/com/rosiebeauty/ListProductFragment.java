package rosie.com.rosiebeauty;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;

public class ListProductFragment extends Fragment {
    Context mContext;
    ArrayList<MultiViewModel> gridViewModelArrayList;
    private RecyclerView mRecyclerView;
    private String[] names,
            address,
            name_cn;
    private int[] pic,
            countComment,
            intPromotion;
    private Double[] rateStar;
    float[] price;
    Locale locale = Locale.forLanguageTag("vi-VN");
    java.text.NumberFormat formatPrice = NumberFormat.getCurrencyInstance(locale);
    private int int_promotion = -1;
    TextView txtNearly,
            txtPromotion,
            txtRating;
    MultiViewTypeAdapter adapter;

    public ListProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_product,
                container,
                false);
        //Recycler View
        gridViewModelArrayList = new ArrayList();

        //Get text
        txtNearly = rootView.findViewById(R.id.filter_Nearly);
        txtPromotion = rootView.findViewById(R.id.filter_Promotion);
        txtRating = rootView.findViewById(R.id.filter_Rate);

        mRecyclerView = rootView.findViewById(R.id.item_list_product);
        //Default
        txtNearly.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
        prepareDataNearly();
        adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                ListProductFragment.this.getActivity());
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        //Click filter Nearly
        txtNearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                txtNearly.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareDataNearly();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        ListProductFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });

        //Click filter Promotion
        txtPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                txtPromotion.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareDataPromotion();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        ListProductFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });

        //Click filter Rating
        txtRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                txtRating.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareDataRating();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        ListProductFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });
        return rootView;
    }

    //Promotion
    void prepareDataPromotion() {
        names = new String[]{
                "Trị mụn chuyên sâu",
                "Detox da",
                "Chống lão hoá da",
                "Điều trị nám",
                "Trị sẹo mụn",
                "Tẩy thâm quầng mắt"

        };
        pic = new int[]{
                R.drawable.ser1_acne,
                R.drawable.ser4_mark,
                R.drawable.ser2_antiaging,
                R.drawable.ser8_trinam,
                R.drawable.ser6_scar,
                R.drawable.ser7_thamquang
        };
        intPromotion = new int[]{
                20,
                10,
                10,
                10,
                5,
                5,
        };
        rateStar = new Double[]{
                4.1,
                4.3,
                4.8,
                3.8,
                4.6,
                4.0
        };
        countComment = new int[]{
                4,
                5,
                7,
                8,
                6,
                5
        };
        name_cn = new String[]{
                "Hana Beauty",
                "Koi Spa",
                "Venus Spa",
                "Thu Cúc Clinics",
                "Derma Spa",
                "Tropic Spa"
        };
        address = new String[]{
                "Nguyễn Thiện Thuật, Q.3",
                "Võ Văn Tần, Q.3",
                "Phan Xích Long, Q.Phú Nhuận",
                "Hồ Hảo Hớn, Q.1",
                "Phan Văn Trị, Q. Bình Thạnh",
                "Lê Văn Sĩ, Quận 3"
        };
        price = new float[]{
                1500000,
                500000,
                15000000,
                8000000,
                6000000,
                5000000
        };

        MultiViewModel gridViewModel = null;

        //Promotion
        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE,
                "Khuyến mại");
        gridViewModelArrayList.add(gridViewModel);

        //clear old data
        gridViewModelArrayList.clear();

        for (int i = 0; i < names.length; i++) {
            float price_afterpromotion = 0;
            price_afterpromotion = price[i] - (price[i] * intPromotion[i] / 100);
            float price_acronym = 0;
            //show service
            if (Math.abs(price[i] / 1000000) > 1) {
                price_acronym = (price[i] / 1000000);
                if (price[i] % 1000000 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        name_cn[i],
                        address[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    void prepareDataNearly() {
        //Nearly
        names = new String[]{
                "Trị sẹo mụn",
                "Trị bọng mắt",
                "Dưỡng ẩm chuyên sâu",
                "Massage săn chắc",
                "Trị thâm mụn",
                "Trị nám"

        };
        pic = new int[]{
                R.drawable.ser6_scar,
                R.drawable.ser3_bongmat,
                R.drawable.ser10_duongam,
                R.drawable.ser5_massage_face,
                R.drawable.ser9_tritham,
                R.drawable.ser8_trinam,
        };
        intPromotion = new int[]{
                5,
                5,
                0,
                0,
                10,
                0,
        };
        rateStar = new Double[]{
                4.6,
                4.3,
                5.0,
                4.8,
                4.5,
                4.0,
        };
        countComment = new int[]{
                4,
                5,
                7,
                8,
                10,
                9
        };
        name_cn = new String[]{
                "Hana Beauty",
                "Koi Spa",
                "Venus Spa",
                "Thu Cúc Clinics",
                "Derma Spa",
                "Tropic Spa"
        };
        address = new String[]{
                "Phan Văn Trị, Q. Bình Thạnh",
                "Phạm Văn Đồng, Q. Bình Thạnh",
                "Lê Quang Định, Q. Bình Thạnh",
                "Nơ Trang Long, Q. Bình Thạnh",
                "Nơ Trang Long, Q. Bình Thạnh",
                "Phan Chu Trinh, Q. Bình Thạnh",

        };
        price = new float[]{
                6000000,
                3000000,
                700000,
                1000000,
                10000000,
                15000000
        };

        MultiViewModel gridViewModel = null;
        //Nearly
        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE,
                "Gần bạn");
        gridViewModelArrayList.add(gridViewModel);

        //clear old data
        gridViewModelArrayList.clear();

        for (int i = 0; i < names.length; i++) {
            float afterpromotion = 0;
            //Check promotion
            if (intPromotion[i] == 0) {
                int_promotion = MultiViewModel.NO_PROMOTION;
            } else if (intPromotion[i] > 0) {
                int_promotion = MultiViewModel.HAS_PROMOTION;
                afterpromotion = price[i] - (price[i] * intPromotion[i] / 100);
            }
            float price_acronym = 0;
            //show service
            if (Math.abs(price[i] / 1000000) > 1) {
                price_acronym = (price[i] / 1000000);
                if (price[i] % 1000000 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(afterpromotion),
                            int_promotion,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(afterpromotion),
                            int_promotion,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(afterpromotion),
                        int_promotion,
                        rateStar[i],
                        countComment[i],
                        name_cn[i],
                        address[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }
    }

    //Rating
    void prepareDataRating() {

        names = new String[]{
                "Trị mụn chuyên sâu",
                "Trị sẹo mụn",
                "Tẩy thâm quầng mắt",
                "Trị thâm mụn",
                "Massage săn chắc",
                "Detox da"

        };
        pic = new int[]{
                R.drawable.ser1_acne,
                R.drawable.ser6_scar,
                R.drawable.ser7_thamquang,
                R.drawable.ser9_tritham,
                R.drawable.ser5_massage_face,
                R.drawable.ser4_mark,
        };
        intPromotion = new int[]{
                20,
                0,
                20,
                10,
                0,
                5,
        };
        rateStar = new Double[]{
                5.0,
                4.8,
                4.6,
                4.5,
                4.2,
                4.0,
        };
        countComment = new int[]{
                4,
                5,
                7,
                8,
                10,
                9
        };
        name_cn = new String[]{
                "Hana Beauty",
                "Koi Spa",
                "Venus Spa",
                "Thu Cúc Clinics",
                "Derma Spa",
                "Tropic Spa"
        };
        address = new String[]{
                "Phạm Văn Đồng, Q. Bình Thạnh",
                "Lê Quang Định, Q. Gò Vấp",
                "Phan Đăng Lưu, Q. Phú Nhuận",
                "Nơ Trang Long, Q. Bình Thạnh",
                "Phan Chu Trinh, Q. Bình Thạnh",
                "Nơ Trang Long, Q. Bình Thạnh",

        };
        price = new float[]{
                1500000,
                3000000,
                3000000,
                1000000,
                1000000,
                1000000
        };

        MultiViewModel gridViewModel = null;
        //Nearly
        gridViewModel = new MultiViewModel(MultiViewModel.TYPE_SECTION_TITLE,
                "Gần bạn");
        gridViewModelArrayList.add(gridViewModel);

        //clear old data
        gridViewModelArrayList.clear();

        for (int i = 0; i < names.length; i++) {
            float afterpromotion = 0;
            //Check promotion
            if (intPromotion[i] == 0) {
                int_promotion = MultiViewModel.NO_PROMOTION;
            } else if (intPromotion[i] > 0) {
                int_promotion = MultiViewModel.HAS_PROMOTION;
                afterpromotion = price[i] - (price[i] * intPromotion[i] / 100);
            }
            float price_acronym = 0;
            //show service
            if (Math.abs(price[i] / 1000000) > 1) {
                price_acronym = (price[i] / 1000000);
                if (price[i] % 1000000 == 0) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(afterpromotion),
                            int_promotion,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(afterpromotion),
                            int_promotion,
                            rateStar[i],
                            countComment[i],
                            name_cn[i],
                            address[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMG_TEXT_PRICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(afterpromotion),
                        int_promotion,
                        rateStar[i],
                        countComment[i],
                        name_cn[i],
                        address[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }
    }

    void cleanFilterSelected() {
        txtRating.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        txtPromotion.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        txtNearly.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
    }
}
