package allen.testmg.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Admin on 9/9/2016.
 */
public class ListStoryResponse extends BaseResponse implements Parcelable {
    @SerializedName("data")
    private List<Story> listStories;

    public List<Story> getListStory() {
        return listStories;
    }

    public static class Story implements Parcelable {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("description")
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.avatar);
            dest.writeString(this.description);
        }

        public Story() {
        }

        protected Story(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.avatar = in.readString();
            this.description = in.readString();
        }

        public static final Creator<Story> CREATOR = new Creator<Story>() {
            @Override
            public Story createFromParcel(Parcel source) {
                return new Story(source);
            }

            @Override
            public Story[] newArray(int size) {
                return new Story[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.listStories);
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
    }

    public ListStoryResponse() {
    }

    protected ListStoryResponse(Parcel in) {
        this.listStories = in.createTypedArrayList(Story.CREATOR);
        this.success = in.readByte() != 0;
        this.message = in.readString();
    }

    public static final Creator<ListStoryResponse> CREATOR = new Creator<ListStoryResponse>() {
        @Override
        public ListStoryResponse createFromParcel(Parcel source) {
            return new ListStoryResponse(source);
        }

        @Override
        public ListStoryResponse[] newArray(int size) {
            return new ListStoryResponse[size];
        }
    };
}
