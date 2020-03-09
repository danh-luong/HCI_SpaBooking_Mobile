package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import rosie.com.rosiebeauty.Adapter.MultiViewTypeAdapter;
import rosie.com.rosiebeauty.Model.MultiViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    ArrayList<MultiViewModel> gridViewModelArrayListForSuggestionList;
    private RecyclerView mRecyclerViewForSuggestionList;

    static TextView txtNoResult;
    static LinearLayout linearLayoutTopTrending;
    private boolean isSubmit = false;
    public static String previousText = "";

    ArrayList<MultiViewModel> gridViewModelArrayListForTrendingList;
    ArrayList<MultiViewModel> suggestionList;
    private RecyclerView mRecyclerViewForTrendingList;


    //-----------------------------------Recent search------------------------------------
    private String[] recent_search_names = new String[]{
            "Tắm bùn", "Nhuộm tóc", "Uốn tóc", "Phục hồi tóc"
    };
    private int[] iconsForRecentSearch = new int[]{
            R.drawable.ser_steaming_hair,
            R.drawable.ser_blow_dry,
            R.drawable.ser_dye_hair,
            R.drawable.ser_gloss_hair
    };

    private int[] historyIcon = new int[]{
            R.drawable.clock_icon,
            R.drawable.clock_icon,
            R.drawable.clock_icon,
            R.drawable.clock_icon
    };

    //-----------------------------------Normal suggestion------------------------------------

    private String[] suggestion_item_names = new String[]{
            "Tắm bùn", "Nhuộm tóc", "Uốn tóc", "Tái tạo da mặt", "Phục hồi tóc", "Trị mụn"
    };
    private int[] iconsForSuggestion = new int[]{
            R.drawable.ser_steaming_hair,
            R.drawable.ser_steaming_hair,
            R.drawable.ser_highlight_hair,
            R.drawable.ser_gloss_hair,
            R.drawable.ser_blow_dry,
            R.drawable.ser_steaming_hair
    };

    private int[] glassIcon = new int[]{
            R.drawable.magnifying_glass,
            R.drawable.magnifying_glass,
            R.drawable.magnifying_glass,
            R.drawable.magnifying_glass,
            R.drawable.magnifying_glass,
            R.drawable.magnifying_glass
    };

    //-----------------------------------Trending items------------------------------------

    private String[] trending_product_names = new String[]{
            "Tắm bùn", "Tái tạo da mặt", "Phục hồi tóc", "Trị mụn"
    };
    private int[] trending_product_icons = new int[]{
            R.drawable.ser_blow_dry,
            R.drawable.ser_highlight_hair,
            R.drawable.ser_perm,
            R.drawable.ser_gloss_hair
    };
    private MaterialSearchView searchView;


    public SearchFragment() {
        // Required empty public constructor
    }


    public void setSearchView(MaterialSearchView searchView) {
        this.searchView = searchView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //Recycler View
        gridViewModelArrayListForSuggestionList = new ArrayList();
        gridViewModelArrayListForTrendingList = new ArrayList();

        SearchFragment.txtNoResult = (TextView) rootView.findViewById(R.id.txtNotFound);
        SearchFragment.linearLayoutTopTrending = (LinearLayout)rootView.findViewById(R.id.layoutTopTrending);

        prepareData();

        MultiViewTypeAdapter suggestionAdapter = new MultiViewTypeAdapter(gridViewModelArrayListForSuggestionList, this.getActivity().getApplicationContext());
        suggestionAdapter.setSearchView(searchView);
        mRecyclerViewForSuggestionList = rootView.findViewById(R.id.suggestionView);
        StaggeredGridLayoutManager lmForSuggestion =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerViewForSuggestionList.setLayoutManager(lmForSuggestion);
        mRecyclerViewForSuggestionList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewForSuggestionList.setAdapter(suggestionAdapter);

        MultiViewTypeAdapter trendingAdapter = new MultiViewTypeAdapter(gridViewModelArrayListForTrendingList, this.getActivity().getApplicationContext());
        trendingAdapter.setSearchView(searchView);
        trendingAdapter.setTriggerFragment(this);
        mRecyclerViewForTrendingList = rootView.findViewById(R.id.trendingProduct);
        StaggeredGridLayoutManager lmForTrending =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerViewForTrendingList.setLayoutManager(lmForTrending);
        mRecyclerViewForTrendingList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewForTrendingList.setAdapter(trendingAdapter);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return rootView;

    }

    public void showProductDetail(Fragment selectedFragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
    }

    public void changeContentOnQueryTextChange(String searchText) {
        searchText = searchText.trim();
        if (isSubmit) {
            isSubmit = false;
            return;
        }

        MultiViewModel gridViewModel = null;

        gridViewModelArrayListForSuggestionList = new ArrayList<>();
        gridViewModelArrayListForTrendingList = new ArrayList<>();
        suggestionList = new ArrayList<>();
        if (searchText.equals("")) {
            for (int i = 0; i < recent_search_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, recent_search_names[i], iconsForRecentSearch[i], historyIcon[i], 1);
                gridViewModelArrayListForSuggestionList.add(gridViewModel);
            }

            for (int i = 0; i < trending_product_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_TEXT_INSIDE_IMAGE, trending_product_names[i], trending_product_icons[i]);
                gridViewModelArrayListForTrendingList.add(gridViewModel);
            }

            SearchFragment.txtNoResult.setVisibility(View.GONE);
            SearchFragment.linearLayoutTopTrending.setVisibility(View.VISIBLE);
        } else {
            SearchFragment.linearLayoutTopTrending.setVisibility(View.GONE);
            for (int i = 0; i < recent_search_names.length; i++) {
                if (recent_search_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, recent_search_names[i], iconsForRecentSearch[i], historyIcon[i], 1);
                    suggestionList.add(gridViewModel);
                }
            }

            for (int i = 0; i < suggestion_item_names.length; i++) {
                if (suggestion_item_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, suggestion_item_names[i], iconsForSuggestion[i], glassIcon[i], 1);
                    suggestionList.add(gridViewModel);
                }
            }

            if (suggestionList.size() == 0) {
                SearchFragment.txtNoResult.setVisibility(View.VISIBLE);
            } else {
                SearchFragment.txtNoResult.setVisibility(View.GONE);
            }

        }
        if(this.getActivity() != null) {
            MultiViewTypeAdapter suggestionAdapter = new MultiViewTypeAdapter(gridViewModelArrayListForSuggestionList, this.getActivity().getApplicationContext());
            suggestionAdapter.setSearchView(searchView);
            StaggeredGridLayoutManager lmForSuggestion =
                    new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerViewForSuggestionList.setLayoutManager(lmForSuggestion);
            mRecyclerViewForSuggestionList.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewForSuggestionList.setAdapter(suggestionAdapter);

            MultiViewTypeAdapter trendingAdapter = new MultiViewTypeAdapter(gridViewModelArrayListForTrendingList, this.getActivity().getApplicationContext());
            trendingAdapter.setSearchView(searchView);
            trendingAdapter.setTriggerFragment(this);
            StaggeredGridLayoutManager lmForTrending =
                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerViewForTrendingList.setLayoutManager(lmForTrending);
            mRecyclerViewForTrendingList.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewForTrendingList.setAdapter(trendingAdapter);

            if (suggestionList.size() != 0) {
                MultiViewTypeAdapter suggestionAdapterV2 = new MultiViewTypeAdapter(suggestionList, this.getActivity().getApplicationContext());
                suggestionAdapterV2.setSearchView(searchView);
                suggestionAdapterV2.setTriggerFragment(this);
                StaggeredGridLayoutManager lmForSuggestionV2 =
                        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerViewForSuggestionList.setLayoutManager(lmForSuggestionV2);
                mRecyclerViewForSuggestionList.setItemAnimator(new DefaultItemAnimator());
                mRecyclerViewForSuggestionList.setAdapter(suggestionAdapterV2);
            }
        }
    }


    public void displaySubmitData(String searchText) {
        MultiViewModel gridViewModel = null;
        SearchFragment.linearLayoutTopTrending.setVisibility(View.GONE);
        suggestionList = new ArrayList<>();
        for (int i = 0; i < suggestion_item_names.length; i++) {
            if (suggestion_item_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, suggestion_item_names[i], iconsForSuggestion[i], glassIcon[i], 2);
                suggestionList.add(gridViewModel);
            }
        }
        if(this.getActivity() != null) {
            MultiViewTypeAdapter suggestionAdapter = new MultiViewTypeAdapter(suggestionList, this.getActivity().getApplicationContext());
            suggestionAdapter.setSearchView(searchView);
            suggestionAdapter.setTriggerFragment(this);
            StaggeredGridLayoutManager lmForSuggestion =
                    new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerViewForSuggestionList.setLayoutManager(lmForSuggestion);
            mRecyclerViewForSuggestionList.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewForSuggestionList.setAdapter(suggestionAdapter);
        }

    }



    void prepareData() {
        gridViewModelArrayListForSuggestionList.clear();
        gridViewModelArrayListForTrendingList.clear();


        MultiViewModel gridViewModel = null;
        for (int i = 0; i < recent_search_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, recent_search_names[i], iconsForRecentSearch[i], historyIcon[i], 1);
                gridViewModelArrayListForSuggestionList.add(gridViewModel);
        }

        for (int i = 0; i < trending_product_names.length; i++) {
            gridViewModel = new MultiViewModel(MultiViewModel.TYPE_TEXT_INSIDE_IMAGE, trending_product_names[i], trending_product_icons[i]);
            gridViewModelArrayListForTrendingList.add(gridViewModel);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity)getActivity()).setIsCurrentSearchFragment(false);
    }

    public void setIsSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }
}
