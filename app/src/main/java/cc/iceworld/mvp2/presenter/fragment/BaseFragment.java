package cc.iceworld.mvp2.presenter.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cc.iceworld.mvp2.api.IBase;
import cc.iceworld.mvp2.toolbox.FakeLayoutInflater;


/**
 * Created by cxx on 16/6/22.
 * xx.ch@outlook.com
 */
public abstract class BaseFragment<V extends IBase.View> extends Fragment implements IBase.Presenter<V> {
    protected V mIView;
    private View mRootView;
    private boolean mNeedInit = true;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIView = getIView();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mRootView == null) {
            mRootView = mIView.onCreateView(FakeLayoutInflater.from(inflater), container, savedInstanceState);
            mNeedInit = true;
        }
        return mRootView;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mNeedInit && mIView != null) {
            mIView.onViewCreated(view, savedInstanceState);
            initData(savedInstanceState);
            mNeedInit = false;
        }
    }

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (mIView != null) {
            mIView.onStart();
        }
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mIView != null) {
            mIView.onResume();
        }
    }

    @CallSuper
    @Override
    public void onPause() {
        if (mIView != null) {
            mIView.onPause();
        }
        super.onPause();
    }

    @CallSuper
    @Override
    public void onStop() {
        if (mIView != null) {
            mIView.onStop();
        }
        super.onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        if (mIView != null) {
            mIView.onDestroy();
            mIView = null;
        }
        mRootView = null;
        super.onDestroy();
    }

    protected boolean iViewEnable() {
        return mIView != null && mIView.isActive();
    }

    protected void toast(@NonNull CharSequence text) {
        Activity a = getActivity();
        if (a != null) {
            Toast.makeText(a.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    protected void toast(@StringRes int resId) {
        Activity a = getActivity();
        if (a != null) {
            Toast.makeText(a.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
        }
    }
}
