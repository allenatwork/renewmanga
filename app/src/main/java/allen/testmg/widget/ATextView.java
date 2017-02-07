package allen.testmg.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import allen.testmg.R;


/**
 * Created by Allen on 28-Dec-16.
 */

public class ATextView extends TextView {
    private static final int FONT_REGULAR = 1;
    private static final int FONT_MEDIUM = 2;
    private static final int FONT_LIGHT = 3;
    private static final int FONT_BOLD = 4;

    public ATextView(Context context) {
        super(context);
        init(context, null);
    }

    public ATextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ATextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attr) {
        int font_value = FONT_REGULAR;
        if (attr != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attr, R.styleable.ATextView, 0, 0);
            font_value = a.getInt(R.styleable.ATextView_font, FONT_REGULAR);
            a.recycle();
        }
        switch (font_value) {
            case FONT_REGULAR:
                setTypeface(FontCache.getTypeface("Regular.ttf", context));
                break;
            case FONT_MEDIUM:
                setTypeface(FontCache.getTypeface("Medium.ttf", context));
                break;
            case FONT_LIGHT:
                setTypeface(FontCache.getTypeface("Light.ttf", context));
                break;
            case FONT_BOLD:
                setTypeface(FontCache.getTypeface("Bold.ttf", context));
                break;
        }
    }
}
