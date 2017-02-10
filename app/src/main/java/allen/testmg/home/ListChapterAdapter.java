package allen.testmg.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import allen.testmg.api.response.ListStoryResponse;
import allen.testmg.api.response.StoryDetailResponse;
import allen.testmg.widget.ChapterItemView;
import allen.testmg.widget.LoadMoreView;
import allen.testmg.widget.MangaDetailHeader;


/**
 * Created by Allen on 13-Oct-16.
 */
public class ListChapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_ITEM = 1;
    public static final int VIEW_LOAD_MORE = 2;
    int item_cout = 0;
    ArrayList<StoryDetailResponse.ChapterInfo> listChapter;
    String story_id;
    ListStoryResponse.Story story;
    LoadMoreView.OnLoadMore onLoadMore;

    public ListChapterAdapter(ArrayList<StoryDetailResponse.ChapterInfo> listChapter, ListStoryResponse.Story story, LoadMoreView.OnLoadMore onLoadMore) {
        this.story = story;
        this.listChapter = listChapter;
        this.onLoadMore = onLoadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_ITEM) {
            ChapterItemView view = new ChapterItemView(parent.getContext());
            ChapterViewHolder viewHolder = new ChapterViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_HEADER) {
            MangaDetailHeader view = new MangaDetailHeader(parent.getContext());
            HeaderViewHolder viewHolder = new HeaderViewHolder(view);
            return viewHolder;
        } else {
            LoadMoreView view = new LoadMoreView(parent.getContext());
            LoadMoreViewHolder viewHolder = new LoadMoreViewHolder(view, onLoadMore);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) ((HeaderViewHolder) holder).display(story);
        else if (holder instanceof ChapterViewHolder)
            ((ChapterViewHolder) holder).display(listChapter.get(position - 1), story_id);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_HEADER;
        if (position == item_cout - 1) return VIEW_LOAD_MORE;
        else return VIEW_ITEM;
    }

    @Override
    public int getItemCount() {
        if (listChapter == null || listChapter.size() == 0) return 1;
        return item_cout = listChapter.size() + 1 + 1; // Plus 1 for header // Plus 1 for footer load more
    }

    public void setStoryId(String story_id) {
        try {
            this.story_id = story_id;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        ChapterItemView view;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            view = (ChapterItemView) itemView;
        }

        public void display(StoryDetailResponse.ChapterInfo chapterInfo, String story_id) {
            view.display(chapterInfo, story_id);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        MangaDetailHeader view;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            view = (MangaDetailHeader) itemView;
        }

        public void display(ListStoryResponse.Story story) {
            view.display(story);
        }
    }

    public static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        LoadMoreView view;

        public LoadMoreViewHolder(View itemView, LoadMoreView.OnLoadMore onLoadMore) {
            super(itemView);
            view = (LoadMoreView) itemView;
            view.setOnLoadMore(onLoadMore);
        }
    }
}
