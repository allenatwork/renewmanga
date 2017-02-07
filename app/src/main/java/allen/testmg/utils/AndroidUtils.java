package allen.testmg.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

/**
 * Created by thangtranquyet on 3/16/16.
 */
public class AndroidUtils {
    public static final String Tag = AndroidUtils.class.getSimpleName();

    public static int convertDpToPx(Context mContext, double dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = (int) Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        px = (int) (dp * displayMetrics.density + 0.5f);
        return px;
    }


    /**
     * Get height and width screen android device
     *
     * @param activity
     * @return
     */
    public static final int WidthScreenPos = 0;
    public static final int HeightScreenPos = 1;
    public static final int HeightContentPos = 2;
    public static int widthScreen = -1;
    public static int heightScreen = -1;
    public static int heightContent = -1;

    public static int[] initHeightAndWidthScreen(Activity activity) {
        int[] result = new int[3];
        if (activity == null) {
            result[WidthScreenPos] = 768;
            result[HeightScreenPos] = 1280;
            result[HeightContentPos] = 1280;
            return result;
        }
        if (widthScreen != -1 && heightScreen != -1 && heightContent != -1) {
        } else {
            Point size = new Point();
            WindowManager w = activity.getWindowManager();
            //int widthScreen = 0;
            //int heightScreen = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                w.getDefaultDisplay().getSize(size);
                widthScreen = size.x;
                heightScreen = size.y;
            } else {
            /*
            Display d = w.getDefaultDisplay();
            widthScreen = d.getWidth();
            widthScreen = d.getHeight();
            */
                DisplayMetrics displaymetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                widthScreen = displaymetrics.widthPixels;
                heightScreen = displaymetrics.heightPixels;
            }
            int actionBarHeight = 0;
            TypedValue tv = new TypedValue();
            float dimenActionBar = 0;
            actionBarHeight = (int) 0;
            Rect rect = new Rect();
            Window win = (Window) activity.getWindow();
            win.getDecorView().getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
            }
            heightContent = heightScreen - (actionBarHeight + statusBarHeight);
        }
        result[WidthScreenPos] = widthScreen;
        result[HeightScreenPos] = heightScreen;
        result[HeightContentPos] = heightContent;
        return result;
    }


    public static void cleanView(View view) {
        if (view == null) return;
        String Tag = "CleanView: ";
        Drawable drawable = null;
        if (view instanceof ImageButton) {
            ImageButton ib = (ImageButton) view;
            drawable = ib.getDrawable();
            if (drawable != null) {
                ib.setImageDrawable(null);
                ib.setImageBitmap(null);
                drawable.setCallback(null);
            }
        } else if (view instanceof ImageView) {
            ImageView iv = (ImageView) view;
            drawable = iv.getDrawable();
            if (drawable != null) {
                iv.setImageDrawable(null);
                iv.setImageBitmap(null);
                drawable.setCallback(null);
            }
        } else if (view instanceof AdapterView) {
            ((AdapterView) view).setOnItemClickListener(null);
            view.setOnLongClickListener(null);
            view.setOnTouchListener(null);
        } else if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setText(null);
            tv.setOnClickListener(null);
        } else if (view instanceof Button) {
        }
        drawable = view.getBackground();
        if (drawable != null) {
            drawable.setCallback(null);
        }
        view.destroyDrawingCache();
        if (view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) view;
            int count = vg.getChildCount();
            for (int i = 0; i < count; i++) {
                cleanView(vg.getChildAt(i));
            }
        }
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "000000";
        if (TextUtils.isEmpty(deviceId)) {
            WifiManager wimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            deviceId = wimanager.getConnectionInfo().getMacAddress();
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Settings.Secure.ANDROID_ID;
        }
        return deviceId;
    }

    public static String getBase64(String input) {
        if (TextUtils.isEmpty(input)) return null;
        byte[] data = null;
        try {
            data = input.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }
}
