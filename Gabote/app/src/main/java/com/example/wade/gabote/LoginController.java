package com.example.wade.gabote;

import android.app.Activity;

/**
 * Created by Wade on 2015-10-29.
 */
public class LoginController {

    public LoginController(){
    }
    protected void checkLogin(Activity activity, String username, String pass)
    {
        new GetData.checkUserLogin(activity,username,pass);
    }
}