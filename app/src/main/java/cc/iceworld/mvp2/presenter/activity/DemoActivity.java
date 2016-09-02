package cc.iceworld.mvp2.presenter.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import cc.iceworld.mvp2.api.IDemo;
import cc.iceworld.mvp2.api.ILoginModel;
import cc.iceworld.mvp2.bean.User;
import cc.iceworld.mvp2.model.LoginModel;
import cc.iceworld.mvp2.toolbox.net.WeakCallback;
import cc.iceworld.mvp2.view.DemoView;


/**
 * Created by cxx on 16-6-23.
 * xx.ch@outlook.com
 */
public class DemoActivity extends BaseActivity<IDemo.View> implements IDemo.Presenter {
    private ILoginModel mModel = new LoginModel();
    private String mUsername;
    private String mPassword;

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        // no data need init
    }

    @Override
    public void toLogin() {
        if (!checkAndTip()) return;
        mModel.login(mUsername, mPassword, new WeakCallback<IDemo.View, User>(mIView) {
            @Override
            public void onStart(@NonNull IDemo.View view) {
                toast("login...");
                view.setSubmitEnabled(false);
            }

            @Override
            public void onSuccess(@NonNull IDemo.View view, @NonNull User user) {
                view.onLoginSuccess(user);
            }

            @Override
            public void onFailure(@NonNull IDemo.View view, int code, @NonNull String msg) {
                view.onLoginFailure(msg);
            }

            @Override
            public void onFinish(@NonNull IDemo.View view) {
                view.setSubmitEnabled(true);
            }
        });
    }

    @NonNull
    @Override
    public IDemo.View getIView() {
        return new DemoView(this);
    }

    private boolean checkAndTip() {
        boolean result = true;
        String username = mIView.getUsername();
        if (TextUtils.isEmpty(username)) {
            mIView.setUsernameError("empty");
            result = false;
        }
        String password = mIView.getPassword();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mIView.setPasswordError("less than 6");
            result = false;
        }
        if (result) {
            mUsername = username;
            mPassword = password;
        }
        return result;
    }
}
