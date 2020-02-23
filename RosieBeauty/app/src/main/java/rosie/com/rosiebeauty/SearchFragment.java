package rosie.com.rosiebeauty;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    static TextView txtTopTrending;

    ArrayList<MultiViewModel> gridViewModelArrayListForTrendingList;
    private RecyclerView mRecyclerViewForTrendingList;

    private String[] suggestion_item_names = new String[]{
            "AAAA", "BBBBB", "CCCCCC", "DDDD"
    };
    private int[] iconsForRecentSearch = new int[]{
            R.drawable.ic_action_book,
            R.drawable.ic_action_favorites,
            R.drawable.ic_action_home,
            R.drawable.ic_action_home
    };

    private int[] historyIcon = new int[]{
            R.drawable.clock_icon,
            R.drawable.clock_icon,
            R.drawable.clock_icon,
            R.drawable.clock_icon
    };

    private String[] product_item_names = new String[]{
            "product1", "product2", "product3"
    };
    private int[] iconForSuggestion = new int[]{
            R.drawable.ic_action_book,
            R.drawable.ic_action_favorites,
            R.drawable.ic_action_home
    };

    private int[] productIcon = new int[]{
            R.drawable.magnifying_class,
            R.drawable.magnifying_class,
            R.drawable.magnifying_class
    };

    private String[] trending_product_names = new String[]{
            "AAAA", "BBBBB", "CCCCCC", "DDDD"
    };
    private int[] trending_product_icons = new int[]{
            R.drawable.ic_action_book,
            R.drawable.ic_action_favorites,
            R.drawable.ic_action_home,
            R.drawable.ic_action_home
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
        SearchFragment.txtTopTrending = (TextView) rootView.findViewById(R.id.txtTopTrending);

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
        mRecyclerViewForTrendingList = rootView.findViewById(R.id.trendingProduct);
        StaggeredGridLayoutManager lmForTrending =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerViewForTrendingList.setLayoutManager(lmForTrending);
        mRecyclerViewForTrendingList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewForTrendingList.setAdapter(trendingAdapter);

        return rootView;

    }

    public void changeContentOnQueryTextChange(String searchText) {
        MultiViewModel gridViewModel = null;

        gridViewModelArrayListForSuggestionList = new ArrayList<>();
        gridViewModelArrayListForTrendingList = new ArrayList<>();
        if (searchText.equals("")) {
            for (int i = 0; i < suggestion_item_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, suggestion_item_names[i], iconsForRecentSearch[i], historyIcon[i]);
                gridViewModelArrayListForSuggestionList.add(gridViewModel);
            }

            for (int i = 0; i < trending_product_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_TEXT_INSIDE_IMAGE, trending_product_names[i], trending_product_icons[i]);
                gridViewModelArrayListForTrendingList.add(gridViewModel);
            }

            SearchFragment.txtNoResult.setVisibility(View.GONE);
            SearchFragment.txtTopTrending.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < suggestion_item_names.length; i++) {
                if (suggestion_item_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, suggestion_item_names[i], iconsForRecentSearch[i], historyIcon[i]);
                    gridViewModelArrayListForSuggestionList.add(gridViewModel);
                }
            }

            for (int i = 0; i < trending_product_names.length; i++) {
                if (trending_product_names[i].toLowerCase().contains(searchText.toLowerCase())) {
                    gridViewModel = new MultiViewModel(MultiViewModel.TYPE_TEXT_INSIDE_IMAGE, trending_product_names[i], trending_product_icons[i]);
                    gridViewModelArrayListForTrendingList.add(gridViewModel);
                }
            }

            if (gridViewModelArrayListForSuggestionList.size() == 0 &&
                    gridViewModelArrayListForTrendingList.size() == 0) {
                SearchFragment.txtNoResult.setVisibility(View.VISIBLE);
            } else {
                SearchFragment.txtNoResult.setVisibility(View.GONE);
            }

            if (gridViewModelArrayListForTrendingList.size() == 0) {
                SearchFragment.txtTopTrending.setVisibility(View.GONE);
            } else {
                SearchFragment.txtTopTrending.setVisibility(View.VISIBLE);
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
            StaggeredGridLayoutManager lmForTrending =
                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerViewForTrendingList.setLayoutManager(lmForTrending);
            mRecyclerViewForTrendingList.setItemAnimator(new DefaultItemAnimator());
            mRecyclerViewForTrendingList.setAdapter(trendingAdapter);
        }
    }



    void prepareData() {
        gridViewModelArrayListForSuggestionList.clear();
        gridViewModelArrayListForTrendingList.clear();


        MultiViewModel gridViewModel = null;
        for (int i = 0; i < suggestion_item_names.length; i++) {
                gridViewModel = new MultiViewModel(MultiViewModel.TYPE_IMAGE_INLINE_WITH_TEXT, suggestion_item_names[i], iconsForRecentSearch[i], historyIcon[i]);
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
}
