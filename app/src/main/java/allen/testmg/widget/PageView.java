package allen.testmg.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.crystal.crystalpreloaders.widgets.CrystalPreloader;

import allen.testmg.R;


/**
 * Created by Allen on 13-Oct-16.
 */
public class PageView extends RelativeLayout {
    ImageView imageView;
    GestureDetector gestureDetector;
    OnDoubletapPage onDoubletapPage;
    CrystalPreloader loading;
    View tryagain, btn_tryagain;
    String url;


    public interface OnDoubletapPage {
        void onDoubletapPage();
    }

    public void setOnDoubletapPage(OnDoubletapPage onDoubletapPage) {
        this.onDoubletapPage = onDoubletapPage;
    }

    private SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            imageView.setImageBitmap(resource);
            loading.setVisibility(GONE);
        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {
            super.onLoadFailed(e, errorDrawable);
            loading.setVisibility(GONE);
            tryagain.setVisibility(VISIBLE);
        }

        @Override
        public void onLoadStarted(Drawable placeholder) {
            super.onLoadStarted(placeholder);
        }
    };

    public PageView(Context context) {
        super(context);
        init(context);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.page_view, this);
        imageView = (ImageView) findViewById(R.id.imageView);
        loading = (CrystalPreloader) findViewById(R.id.loading);
        tryagain = findViewById(R.id.layout_try_again);
        btn_tryagain = tryagain.findViewById(R.id.btn_try_again);
        btn_tryagain.setOnClickListener(onClickTryAgain);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    private OnClickListener onClickTryAgain = new OnClickListener() {
        @Override
        public void onClick(View view) {
            loading.setVisibility(VISIBLE);
            tryagain.setVisibility(GONE);
            display(url);
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void display(String url) {
        this.url = url;
        Glide.with(getContext().getApplicationContext())
                .load(url)
//                .crossFade()
//                .placeholder(R.drawable.loading_drawable)
                .asBitmap()
                .into(target);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("Double Tap", "Tapped");
            onDoubletapPage.onDoubletapPage();
            return true;
        }
    }

    public void destroyResource() {
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            imageView.setImageDrawable(null);
            imageView.setImageBitmap(null);
            drawable.setCallback(null);
        }
        btn_tryagain.setOnClickListener(null);
        target = null;
    }
}
