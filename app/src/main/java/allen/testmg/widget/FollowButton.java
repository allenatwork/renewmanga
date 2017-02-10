package allen.testmg.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sstudio.a9manga.R;

/**
 * Created by Admin on 12-Nov-16.
 */

public class FollowButton extends LinearLayout {
    boolean isFollowed;
    TextView status;
    OnFollowStory onFollowStory;

    public interface OnFollowStory {
        void onFollowStory(boolean yes);
    }

    public void setOnFollowStory(OnFollowStory onFollowStory) {
        this.onFollowStory = onFollowStory;
    }

    public FollowButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FollowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FollowButton(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.widget_follow_button, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        status = (TextView) findViewById(R.id.status);
        setStateFollowed(isFollowed);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isFollowed = !isFollowed;
                if (onFollowStory != null) {
                    onFollowStory.onFollowStory(isFollowed);
                }

                setStateFollowed(isFollowed);
            }
        });
    }

    public void setStateFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
        if (isFollowed) {
            setBackgroundResource(R.drawable.bg_corner_blue);
            status.setTextColor(getResources().getColor(android.R.color.white));
            status.setText(getResources().getString(R.string.you_are__followed));
        } else {
            setBackgroundResource(R.drawable.bg_corner_white);
            status.setTextColor(getResources().getColor(R.color.main_color));
            status.setText(getResources().getString(R.string.you_are_not_follow_yet));
        }
    }
}
