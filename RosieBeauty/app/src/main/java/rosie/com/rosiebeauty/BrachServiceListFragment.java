package rosie.com.rosiebeauty;

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

public class BrachServiceListFragment extends Fragment {


    ArrayList<MultiViewModel> gridViewModelArrayList;
    float[] price;
    Locale locale = Locale.forLanguageTag("vi-VN");
    java.text.NumberFormat formatPrice = NumberFormat.getCurrencyInstance(locale);
    TextView filter_all,
            filter_face,
            filter_hair,
            filter_nail,
            filter_massage,
            filter_makeup,
            filter_else;
    MultiViewTypeAdapter adapter;
    private RecyclerView mRecyclerView;
    private String[] names;
    private int[] pic,
            countComment,
            intPromotion;
    private Double[] rateStar;
    private int int_promotion = -1;

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
        View rootView = inflater.inflate(R.layout.fragment_branch_service_list, container, false);

        //Recycler View
        gridViewModelArrayList = new ArrayList();

        //Get text
        filter_all = rootView.findViewById(R.id.filter_all);
        filter_face = rootView.findViewById(R.id.filter_face);
        filter_hair = rootView.findViewById(R.id.filter_hair);
        filter_nail = rootView.findViewById(R.id.filter_nail);
        filter_massage = rootView.findViewById(R.id.filter_massage);
        filter_makeup = rootView.findViewById(R.id.filter_makeup);
        filter_else = rootView.findViewById(R.id.filter_else);

        mRecyclerView = rootView.findViewById(R.id.item_branch_product);
        //Default
        filter_all.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
        prepareFilterAll();
        adapter = new MultiViewTypeAdapter(gridViewModelArrayList, BrachServiceListFragment.this.getActivity());
        StaggeredGridLayoutManager lm =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        //Click filter_all
        filter_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_all.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterAll();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList, BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });

        //Click filter_face
        filter_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_face.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterFace();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList, BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });

        //Click filter_hair
        filter_hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_hair.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterHair();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });
        //Click filter_nail
        filter_nail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_nail.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterNail();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });
        //Click filter_massage
        filter_massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_massage.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterMassage();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });
        //Click filter_makeup
        filter_makeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_makeup.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterMakeup();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });
        //Click filter_else
        filter_else.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanFilterSelected();
                filter_else.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_boder_selected));
                prepareFilterElse();
                adapter = new MultiViewTypeAdapter(gridViewModelArrayList,
                        BrachServiceListFragment.this.getActivity());
                StaggeredGridLayoutManager lm =
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(lm);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(adapter);
            }
        });

        return rootView;
    }

    //Face
    void prepareFilterFace() {
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

        price = new float[]{
                1500000, 500000, 15000000, 8000000, 6000000, 5000000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //Hair
    void prepareFilterHair() {
        names = new String[]{
                "Nhuộm tóc highlight",
                "Cắt tóc tạo kiểu",
                "Nhuộm tóc",
                "Uốn tóc",
                "Sấy tóc tạo kiểu",
                "Phục hồi tóc"

        };
        pic = new int[]{
                R.drawable.ser_highlight_hair,
                R.drawable.ser_cutting_hair_girl,
                R.drawable.ser_dye_hair,
                R.drawable.ser_gloss_hair,
                R.drawable.ser_blow_dry,
                R.drawable.ser_steaming_hair
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

        price = new float[]{
                1500000, 700000, 900000, 500000, 300000, 300000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //Nail
    void prepareFilterNail() {
        names = new String[]{
                "Đính đá",
                "Sơn màu",
                "Cắt da và dưỡng móng",
                "Đắp bột đính đá",
                "Đắp bột hoa 3D",
                "Vẽ móng"

        };
        pic = new int[]{
                R.drawable.ser_dinhda,
                R.drawable.ser_sonmong,
                R.drawable.chamsocmong,
                R.drawable.dapbot,
                R.drawable.daphoa,
                R.drawable.vemong
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

        price = new float[]{
                800000, 500000, 200000, 1000000, 1200000, 700000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //Massage
    void prepareFilterMassage() {
        names = new String[]{
                "Massage toàn thân",
                "Massage mặt",
                "Massage chân",
                "Massage body với nến",
                "Massage Thái",
                "Massage Shiatsu",

        };
        pic = new int[]{
                R.drawable.ser_massbody,
                R.drawable.ser5_massage_face,
                R.drawable.maschan,
                R.drawable.mascandle,
                R.drawable.masthai,
                R.drawable.masshiatsu
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

        price = new float[]{
                2000000, 1000000, 800000, 1800000, 2000000, 2400000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //Makeup
    void prepareFilterMakeup() {
        names = new String[]{
                "Trang điểm Hàn Quốc",
                "Trang điểm cô dâu",
                "Trang điểm Âu Mỹ",
                "Trang điểm cơ bản",
                "Trang điểm dự tiệc",
                "Trang điểm cuốn hút"

        };
        pic = new int[]{
                R.drawable.ser_korea,
                R.drawable.ser_codau,
                R.drawable.aumy,
                R.drawable.hangngay,
                R.drawable.party,
                R.drawable.haha
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

        price = new float[]{
                1500000, 3000000, 2000000, 1000000, 1200000, 2500000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //all
    void prepareFilterAll() {
        names = new String[]{
                "Trị mụn chuyên sâu",
                "Nhuộm tóc highlight",
                "Đính đá",
                "Massage toàn thân",
                "Trang điểm Hàn Quốc",
                "Xông hơi khô",
                "Detox da",
                "Chống lão hoá da",
                "Điều trị nám",
                "Cắt tóc tạo kiểu",
                "Nhuộm tóc",
                "Uốn tóc",
                "Sơn màu",
                "Cắt da và dưỡng móng",
                "Đắp bột đính đá",
                "Massage mặt",
                "Massage chân",
                "Massage body với nến",
                "Trang điểm cô dâu",
                "Trang điểm Âu Mỹ",
                "Trang điểm cơ bản",
                "Xông hơi nước",
                "Xông hơi hồng ngoại",
                "Liệu trình giảm béo",
                "Trị sẹo mụn",
                "Tẩy thâm quầng mắt",
                "Sấy tóc tạo kiểu",
                "Phục hồi tóc",
                "Đắp bột hoa 3D",
                "Vẽ móng",
                "Massage Thái",
                "Massage Shiatsu",
                "Trang điểm dự tiệc",
                "Trang điểm cuốn hút",
                "Triệt lông tay",
                "Triệt lông chân"
        };
        pic = new int[]{
                R.drawable.ser1_acne,
                R.drawable.ser_highlight_hair,
                R.drawable.ser_dinhda,
                R.drawable.ser_massbody,
                R.drawable.ser_korea,
                R.drawable.xonghoinu,
                R.drawable.ser4_mark,
                R.drawable.ser2_antiaging,
                R.drawable.ser8_trinam,
                R.drawable.ser_cutting_hair_girl,
                R.drawable.ser_dye_hair,
                R.drawable.ser_gloss_hair,
                R.drawable.ser_sonmong,
                R.drawable.chamsocmong,
                R.drawable.dapbot,
                R.drawable.ser5_massage_face,
                R.drawable.maschan,
                R.drawable.mascandle,
                R.drawable.ser_codau,
                R.drawable.aumy,
                R.drawable.hangngay,
                R.drawable.xonghoinuoc,
                R.drawable.xonghoihongngoai,
                R.drawable.giambeo,
                R.drawable.ser6_scar,
                R.drawable.ser7_thamquang,
                R.drawable.ser_blow_dry,
                R.drawable.ser_steaming_hair,
                R.drawable.daphoa,
                R.drawable.vemong,
                R.drawable.masthai,
                R.drawable.masshiatsu,
                R.drawable.party,
                R.drawable.haha,
                R.drawable.trietlongtay,
                R.drawable.trietlongchan
        };
        intPromotion = new int[]{
                20, 20, 20, 20, 20, 20, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        };
        rateStar = new Double[]{
                4.1, 4.1, 4.1, 4.1, 4.1, 4.1, 4.3, 4.8, 3.8, 4.3, 4.8, 3.8, 4.3, 4.8, 3.8, 4.3, 4.8,
                3.8, 4.3, 4.8, 3.8, 4.3, 4.8, 3.8, 4.6, 4.0, 4.6, 4.0, 4.6, 4.0, 4.6, 4.0, 4.6, 4.0, 4.6, 4.0,
        };
        countComment = new int[]{
                4, 4, 4, 4, 4, 4, 5, 7, 8, 5, 7, 8, 5, 7, 8, 5, 7, 8, 5, 7, 8, 5, 7, 8, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5, 6, 5,
        };

        price = new float[]{
                150000, 150000, 80000, 200000, 150000, 150000, 50000, 1500000, 800000, 70000, 90000,
                50000, 50000, 20000, 100000, 100000, 80000, 180000, 300000, 200000, 100000, 150000,
                180000, 3000000, 600000, 500000, 30000, 30000, 120000, 70000, 200000, 240000, 120000,
                250000, 500000, 500000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }

    //else
    void prepareFilterElse() {
        names = new String[]{
                "Xông hơi khô",
                "Xông hơi nước",
                "Xông hơi hồng ngoại",
                "Liệu trình giảm béo",
                "Triệt lông tay",
                "Triệt lông chân"

        };
        pic = new int[]{
                R.drawable.xonghoinu,
                R.drawable.xonghoinuoc,
                R.drawable.xonghoihongngoai,
                R.drawable.giambeo,
                R.drawable.trietlongtay,
                R.drawable.trietlongchan
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

        price = new float[]{
                1500000, 1500000, 1800000, 30000000, 500000, 500000
        };

        MultiViewModel gridViewModel = null;

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
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.0f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                } else {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                            pic[i],
                            names[i],
                            String.format("%.1f", price_acronym) + " triệu",
                            formatPrice.format(price_afterpromotion),
                            MultiViewModel.HAS_PROMOTION,
                            rateStar[i],
                            countComment[i],
                            "-" + intPromotion[i] + "%");
                    gridViewModelArrayList.add(gridViewModel);
                }
            } else {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_BRANCH_SERVICE,
                        pic[i],
                        names[i],
                        formatPrice.format(price[i]),
                        formatPrice.format(price_afterpromotion),
                        MultiViewModel.HAS_PROMOTION,
                        rateStar[i],
                        countComment[i],
                        "-" + intPromotion[i] + "%");
                gridViewModelArrayList.add(gridViewModel);
            }
        }

    }


    void cleanFilterSelected() {
        filter_all.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_face.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_hair.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_nail.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_massage.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_makeup.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
        filter_else.setBackground(getActivity().getResources().getDrawable(R.drawable.filter_border));
    }
}
