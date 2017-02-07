package allen.testmg.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import allen.testmg.R;


/**
 * Created by Allen on 04-Jan-17.
 */

public class ImageLoader {
    public static void loadImage(String url, ImageView iv) {
        Glide.with(iv.getContext().getApplicationContext()).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(iv);
    }
}
