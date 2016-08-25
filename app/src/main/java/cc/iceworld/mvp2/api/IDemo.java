package cc.iceworld.mvp2.api;

import android.support.annotation.NonNull;

import cc.iceworld.mvp2.bean.User;


/**
 * Created by cxx on 16-6-23.
 * xx.ch@outlook.com
 */
public interface IDemo {

    interface View extends IBase.View {

        String getUsername();

        String getPassword();

        void setUsernameError(@NonNull String tip);

        void setPasswordError(@NonNull String tip);

        void setSubmitEnabled(boolean enabled);

        void onLoginSuccess(@NonNull User user);

        void onLoginFailure(String msg);
    }

    interface Presenter extends IBase.Presenter<View> {

        void toLogin();
    }
}
