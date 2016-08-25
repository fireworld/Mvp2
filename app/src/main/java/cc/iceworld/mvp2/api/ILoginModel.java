package cc.iceworld.mvp2.api;


import cc.iceworld.mvp2.bean.User;
import cc.iceworld.mvp2.net.Callback;

/**
 * Created by cxx on 16-7-21.
 * xx.ch@outlook.com
 */
public interface ILoginModel {

    void login(String username, String password, Callback<User> callback);
}
