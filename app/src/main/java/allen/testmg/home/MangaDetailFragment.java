package allen.testmg.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import icepick.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sstudio.a9manga.R;
import sstudio.a9manga.api.response.ListStoryResponse;
import sstudio.a9manga.api.response.StoryDetailResponse;
import sstudio.a9manga.base.BaseFragment;
import sstudio.a9manga.rateme.RateMeDialogTimer;
import sstudio.a9manga.utils.NetworkUtils;
import sstudio.a9manga.widget.loaderview.LoadMoreView;

public class MangaDetailFragment extends BaseFragment {
    public static final String EXTRA_STORY = "extra_story";
    @State
    ListStoryResponse.Story story;
    @State
    protected StoryDetailResponse storyDetailResponse;
    private LinearLayoutManager layoutManager;
    RecyclerView mRecyclerView;
    ListChapterAdapter adapter;
    ArrayList<StoryDetailResponse.ChapterInfo> listChapter;
    boolean isLoading = false;

    @State
    int loaded_page = 0;

    public MangaDetailFragment() {
        // Required empty public constructor
    }

    public static MangaDetailFragment newInstance(ListStoryResponse.Story story) {

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STORY, story);
        MangaDetailFragment fragment = new MangaDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_manga_detail;
    }

    @Override
    protected boolean isHasMenu() {
        return false;
    }

    @Override
    protected int getMenuResource() {
        return 0;
    }

    @Override
    public String getScreenTitle() {
        if (story != null) return story.getName();
        else
            return getString(R.string.truyen);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listChapter = new ArrayList<>();
        if (getArguments() != null) {
            story = getArguments().getParcelable(EXTRA_STORY);
        }
        if (storyDetailResponse != null && storyDetailResponse.getStoryDetail() != null) { // get Data from savedInstanceStaate
            listChapter.clear();
            listChapter.addAll(storyDetailResponse.getStoryDetail().getListChap());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (storyDetailResponse == null) {
            getDataFromServer();
        } else {
            bindData(storyDetailResponse);
        }
    }

    private void getDataFromServer() {
        if (NetworkUtils.isConnected(getContext())) {
            if (story != null && !TextUtils.isEmpty(story.getId())) {
                isLoading = true;
                showLoading();
                Call<StoryDetailResponse> getMangaDetail = mApi.getStoryDetail(story.getId(), loaded_page);
                getMangaDetail.enqueue(new Callback<StoryDetailResponse>() {
                    @Override
                    public void onResponse(Call<StoryDetailResponse> call, Response<StoryDetailResponse> response) {
                        storyDetailResponse = response.body();
                        bindData(storyDetailResponse);
                        hideLoading();
                        isLoading = false;
                        loaded_page++;
                    }

                    @Override
                    public void onFailure(Call<StoryDetailResponse> call, Throwable t) {
                        hideLoading();
                        showErrorScreen();
                        isLoading = false;
                    }
                });
            } else {
                Toast.makeText(getContext(), "Invalid story Id", Toast.LENGTH_LONG).show();
            }
        } else {
            showErrorScreen();
        }
    }

    @Override
    protected void tryAgain() {
        super.tryAgain();
        listChapter.clear();
        loaded_page = 0;
        getDataFromServer();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ListChapterAdapter(listChapter, story, onLoadMore);
        mRecyclerView.setAdapter(adapter);

    }

    private LoadMoreView.OnLoadMore onLoadMore = new LoadMoreView.OnLoadMore() {
        @Override
        public void onLoadMore() {
            getDataFromServer();
        }
    };

    private void bindData(final StoryDetailResponse storyDetailResponse) {
        if (storyDetailResponse == null) return;
        final StoryDetailResponse.StoryDetail storyDetail = storyDetailResponse.getStoryDetail();
        if (storyDetail == null) return;
//        storyObject = new StoryObject();
//        storyObject.setDetail(storyDetail);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                listChapter.clear();
                int size = adapter.getItemCount();
                listChapter.addAll(storyDetail.getListChap());
//                DBUtils.addChaptertoStory(storyDetail.getListChap(),storyDetail.getStory_id());
                adapter.setStoryId(storyDetailResponse.getStoryDetail().getStory_id());
                adapter.notifyItemRangeInserted(size, listChapter.size() + 1);
            }
        });

        if (RateMeDialogTimer.shouldShowRateDialog(getContext(), 7, 4)) {
            // Todo: show rate me dialog

        }
    }

    public ArrayList<StoryDetailResponse.ChapterInfo> getListChapter() {
        return listChapter;
    }
}
