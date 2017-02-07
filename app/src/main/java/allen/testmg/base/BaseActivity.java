package allen.testmg.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import allen.testmg.R;
import allen.testmg.utils.AndroidUtils;

public abstract class BaseActivity extends AppCompatActivity {
    private final String Tag = BaseActivity.class.getSimpleName();
    protected boolean mPaused;
    protected Toolbar toolbar;

    protected abstract int getLayoutResource();

//    protected DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
//        daoSession = ((NineApp) getApplication()).getDaoSession();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPaused = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
            AndroidUtils.cleanView(view);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Start Activity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Start Activity without bundle
     *
     * @param clazz
     */
    public void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}

