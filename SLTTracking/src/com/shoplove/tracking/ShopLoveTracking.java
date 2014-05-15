package com.shoplove.tracking;

import android.app.Activity;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class ShopLoveTracking {

    static EventHandler sEventHandler;


    public static void trackInstall() {
        if(sEventHandler != null) {
            sEventHandler.track("install-sdk");
        }
    }

    public static void appDidLaunch(Activity activity, String appToken) {
        if(sEventHandler == null) {
            sEventHandler = new EventHandler(activity, appToken);
        }

        trackInstall();
    }

}
