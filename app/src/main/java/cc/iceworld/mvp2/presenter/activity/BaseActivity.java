package cc.iceworld.mvp2.presenter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cc.iceworld.mvp2.api.IBase;
import cc.iceworld.mvp2.toolbox.FakeLayoutInflater;


/**
 * Created by cxx on 16/6/21.
 * xx.ch@outlook.com
 */
public abstract class BaseActivity<V extends IBase.View> extends Activity implements IBase.Presenter<V> {
    protected V mIView;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIView = getIView();
        // decorView 以 View 的形式返回, 其实质是 FrameLayout 的一个子类, 可参见 PhoneWindow 类的实现。
        View decorView = getWindow().getDecorView();
        ViewGroup group = decorView instanceof ViewGroup ? (ViewGroup) decorView : null;
        View rootView = mIView.onCreateView(FakeLayoutInflater.from(this), group, savedInstanceState);
        ViewGroup.LayoutParams lp = rootView.getLayoutParams();
        if (lp != null) {
            setContentView(rootView, lp);
        } else {
            setContentView(rootView);
        }
        mIView.onViewCreated(rootView, savedInstanceState);
        initData(savedInstanceState);
    }

    protected abstract void initData(@Nullable Bundle savedInstanceState);

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        if (mIView != null) {
            mIView.onStart();
        }
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        if (mIView != null) {
            mIView.onResume();
        }
    }

    @CallSuper
    @Override
    protected void onPause() {
        if (mIView != null) {
            mIView.onPause();
        }
        super.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        if (mIView != null) {
            mIView.onStop();
        }
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        if (mIView != null) {
            mIView.onDestroy();
            mIView = null;
        }
        super.onDestroy();
    }

    protected boolean iViewEnable() {
        return mIView != null && mIView.isActive();
    }

    protected void toast(@NonNull CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    protected void toast(@StringRes int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
