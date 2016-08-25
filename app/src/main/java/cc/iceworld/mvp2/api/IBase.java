package cc.iceworld.mvp2.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import cc.iceworld.mvp2.toolbox.FakeLayoutInflater;


/**
 * Created by cxx on 16/6/21.
 * xx.ch@outlook.com
 */
public interface IBase {

    interface View {
        /**
         * 此方法返回需要显示的 View, 用于 Activity.setContentView() 的传入参数 和 Fragment.onCreateView() 的返回值
         * 应在此方法中对必要的 View 进行初始化。
         *
         * @param savedInstanceState 对应 Activity.onCreate(), Fragment.onCreateView() 中的参数。
         */
        @NonNull
        android.view.View onCreateView(@NonNull FakeLayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

        void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState);

        void onStart();

        void onResume();

        void onPause();

        void onStop();

        /**
         * 应在此方法中释放相关资源，解除对 Presenter 的引用。
         */
        void onDestroy();

        boolean isActive();
    }

    interface Presenter<V extends View> {

        @NonNull
        V getIView();
    }
}
