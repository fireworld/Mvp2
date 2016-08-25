package cc.iceworld.mvp2.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import cc.iceworld.mvp2.api.IBase;


/**
 * Created by cxx on 16/6/21.
 * xx.ch@outlook.com
 */
public abstract class BaseView<P extends IBase.Presenter> implements IBase.View {
    private boolean mIsActive = false;
    protected P mPresenter;

    public BaseView(P presenter) {
        mPresenter = presenter;
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mIsActive = true;
    }

    @CallSuper
    @Override
    public void onStart() {

    }

    @CallSuper
    @Override
    public void onResume() {

    }

    @CallSuper
    @Override
    public void onPause() {

    }

    @CallSuper
    @Override
    public void onStop() {

    }

    @CallSuper
    @Override
    public void onDestroy() {
        mIsActive = false;
        mPresenter = null;
    }

    @Override
    public final boolean isActive() {
        return mIsActive;
    }
}
