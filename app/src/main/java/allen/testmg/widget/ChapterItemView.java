package allen.testmg.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import allen.testmg.R;
import allen.testmg.api.response.StoryDetailResponse;


/**
 * Created by Allen on 13-Oct-16.
 */
public class ChapterItemView extends LinearLayout {
    TextView title;

    public ChapterItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.chapter_item_view, this);
        title = (TextView) findViewById(R.id.title);
        setDividerPadding(30);

    }

    public void display(final StoryDetailResponse.ChapterInfo chapterInfo, final String story_id) {
        if (chapterInfo == null) return;
        title.setText(chapterInfo.getName());
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Shared.flowManager.readChapterwithId(story_id, chapterInfo.getId(), chapterInfo.getName());
            }
        });
    }
}
