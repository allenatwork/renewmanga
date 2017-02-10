package allen.testmg.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import allen.testmg.R;


/**
 * Created by Allen on 12/4/2016.
 */

public class LoadMoreView extends LinearLayout {
    View btn_loadmore;
    OnLoadMore onLoadMore;

    public void setOnLoadMore(OnLoadMore onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    public interface OnLoadMore {
        void onLoadMore();
    }

    public LoadMoreView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.load_more_view, this);
        setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setPadding(20, 20, 20, 20);
        setGravity(Gravity.CENTER);
        btn_loadmore = findViewById(R.id.more_please);
        btn_loadmore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadMore.onLoadMore();
            }
        });
    }
}
