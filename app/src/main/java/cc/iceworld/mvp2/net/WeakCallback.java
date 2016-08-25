package cc.iceworld.mvp2.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import cc.iceworld.mvp2.api.IBase;


/**
 * Created by cxx on 16/7/13.
 * xx.ch@outlook.com
 */
public abstract class WeakCallback<V extends IBase.View, T> implements Callback<T> {
    private WeakReference<V> ref;

    public WeakCallback(V view) {
        this.ref = new WeakReference<>(view);
    }

    @Nullable
    private V getView() {
        return ref != null ? ref.get() : null;
    }

    @Override
    public final void onStart() {
        V v = getView();
        if (v != null && v.isActive()) {
            onStart(v);
        }
    }

    @Override
    public final void onSuccess(@NonNull T t) {
        V v = getView();
        if (v != null && v.isActive()) {
            onSuccess(v, t);
        }
    }

    @Override
    public final void onFailure(int code, @NonNull String msg) {
        V v = getView();
        if (v != null && v.isActive()) {
            onFailure(v, code, msg);
        }
    }

    @Override
    public final void onFinish() {
        V v = getView();
        if (v != null && v.isActive()) {
            onFinish(v);
        }
    }

    public void onStart(@NonNull V view) {

    }

    public abstract void onSuccess(@NonNull V view, @NonNull T t);

    public abstract void onFailure(@NonNull V view, int code, @NonNull String msg);

    public void onFinish(@NonNull V view) {

    }
}
