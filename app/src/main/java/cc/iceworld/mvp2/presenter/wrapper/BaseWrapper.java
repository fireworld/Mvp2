package cc.iceworld.mvp2.presenter.wrapper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import cc.iceworld.mvp2.api.IBase;
import cc.iceworld.mvp2.toolbox.FakeLayoutInflater;


/**
 * Created by cxx on 16-6-22.
 * xx.ch@outlook.com
 */
public abstract class BaseWrapper<V extends IBase.View> implements IBase.Presenter<V> {
    protected V mIView;
    protected Context mContext;
    protected View mRootView;
    private boolean mNeedInit = true;

    public BaseWrapper(@NonNull Context context) {
        mContext = context;
    }

    public final void onCreate(@Nullable Bundle savedInstanceState) {
        if (mNeedInit) {
            mIView = getIView();
            mRootView = mIView.onCreateView(FakeLayoutInflater.from(mContext), null, savedInstanceState);
            mIView.onViewCreated(mRootView, savedInstanceState);
            initData(savedInstanceState);
            mNeedInit = false;
        }
    }

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @CallSuper
    public void onStart() {
        if (mIView != null) {
            mIView.onStart();
        }
    }

    @CallSuper
    public void onResume() {
        if (mIView != null) {
            mIView.onResume();
        }
    }

    @CallSuper
    public void onSaveInstanceState(Bundle outState) {

    }

    @CallSuper
    public void onPause() {
        if (mIView != null) {
            mIView.onPause();
        }
    }

    @CallSuper
    public void onStop() {
        if (mIView != null) {
            mIView.onStop();
        }
    }

    @CallSuper
    public void onDestroy() {
        if (mIView != null) {
            mIView.onDestroy();
            mIView = null;
        }
        mContext = null;
        mRootView = null;
        mNeedInit = true;
    }

    protected boolean iViewEnable() {
        return mIView != null && mIView.isActive();
    }

    protected final void toast(@NonNull CharSequence text) {
        if (mContext != null) {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        }
    }

    protected final void toast(@StringRes int resId) {
        if (mContext != null) {
            Toast.makeText(mContext, resId, Toast.LENGTH_SHORT).show();
        }
    }
}
