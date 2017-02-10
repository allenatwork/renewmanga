package allen.testmg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import allen.testmg.R;
import allen.testmg.api.response.ListStoryResponse;
import allen.testmg.utils.ImageLoader;


/**
 * Created by Admin on 20-Nov-16.
 */

public class MangaDetailHeader extends RelativeLayout {
    TextView mDes;
    ImageView mThumnail;
    FollowButton followButton;

    public MangaDetailHeader(Context context) {
        super(context);
        init(context);
    }

    public MangaDetailHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MangaDetailHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.manga_detail_header, this);
        mDes = (TextView) findViewById(R.id.des);
        mThumnail = (ImageView) findViewById(R.id.avatar);
        followButton = (FollowButton) findViewById(R.id.follow);
//        followButton.setStateFollowed(DBUtils.isFollowedStory(storyObject));
    }

    public void display(final ListStoryResponse.Story story) {
        mDes.setText(story.getDescription());
        ImageLoader.loadImage(getContext(), story.getAvatar(), mThumnail);
//        followButton.setOnFollowStory(new FollowButton.OnFollowStory() {
//            @Override
//            public void onFollowStory(boolean requestFollow) {
//                if (story != null)
//                    DBUtils.followStory(requestFollow, story);
//            }
//        });
    }
}
