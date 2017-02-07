package allen.testmg.api;

import allen.testmg.api.response.ChapterDetailResponse;
import allen.testmg.api.response.ListStoryResponse;
import allen.testmg.api.response.StoryDetailResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 9/9/2016.
 */
public interface ApiInterface {
    @GET("story")
    Call<ListStoryResponse> getListStories();

    @GET("story/{id}")
    Call<StoryDetailResponse> getStoryDetail(@Path("id") String story_id, @Query("page") int page);

    @GET("story/{storyid}/chapters/{chapid}")
    Call<ChapterDetailResponse> getChapterDetail(@Path("storyid") String story_id, @Path("chapid") String chap_id);
}
