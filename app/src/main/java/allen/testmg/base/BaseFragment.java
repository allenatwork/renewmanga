package allen.testmg.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import allen.testmg.R;


public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected BaseActivity mActivity;
    protected ActionBar mActionBar;

    protected abstract int getLayoutResource();

    protected final boolean isFragmentValid() {
        return isVisible() && isAdded() && isResumed() && !isRemoving() && !isDetached();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        if (mActivity != null) {
        }
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Run on main thread
     *
     * @param runnable
     */
    protected void runOnUiThread(Runnable runnable) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }


    /**
     * Start Activity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Start Activity without bundle
     *
     * @param clazz
     */
    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }
}
