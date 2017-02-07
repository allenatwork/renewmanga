package allen.testmg.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 9/9/2016.
 */
public class ChapterDetailResponse extends BaseResponse implements Parcelable {

    @SerializedName("data")
    private ChapterContent data;

    public ChapterContent getChapterContent() {
        return data;
    }

    public static class ChapterContent implements Parcelable {

        @SerializedName("story_id")
        private String story_id;
        @SerializedName("chapter_id")
        private String chapter_id;
        @SerializedName("story_name")
        private String story_name;
        @SerializedName("chapter_name")
        private String chapter_name;
        @SerializedName("content")
        private List<String> listUrl;

        public String getStory_id() {
            return story_id;
        }


        public String getChapter_id() {
            return chapter_id;
        }


        public String getStory_name() {
            return story_name;
        }


        public String getChapter_name() {
            return chapter_name;
        }

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public List<String> getContent() {
            return listUrl;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.story_id);
            dest.writeString(this.chapter_id);
            dest.writeString(this.story_name);
            dest.writeString(this.chapter_name);
            dest.writeStringList(this.listUrl);
        }

        public ChapterContent() {
        }

        protected ChapterContent(Parcel in) {
            this.story_id = in.readString();
            this.chapter_id = in.readString();
            this.story_name = in.readString();
            this.chapter_name = in.readString();
            this.listUrl = in.createStringArrayList();
        }

        public static final Parcelable.Creator<ChapterContent> CREATOR = new Parcelable.Creator<ChapterContent>() {
            @Override
            public ChapterContent createFromParcel(Parcel source) {
                return new ChapterContent(source);
            }

            @Override
            public ChapterContent[] newArray(int size) {
                return new ChapterContent[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
    }

    public ChapterDetailResponse() {
    }

    protected ChapterDetailResponse(Parcel in) {
        this.data = in.readParcelable(ChapterContent.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChapterDetailResponse> CREATOR = new Parcelable.Creator<ChapterDetailResponse>() {
        @Override
        public ChapterDetailResponse createFromParcel(Parcel source) {
            return new ChapterDetailResponse(source);
        }

        @Override
        public ChapterDetailResponse[] newArray(int size) {
            return new ChapterDetailResponse[size];
        }
    };
}
