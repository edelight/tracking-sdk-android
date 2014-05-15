package com.shoplove.tracking;

import android.app.Activity;

import java.util.regex.Pattern;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class ShopLoveTracking {

    static EventHandler sEventHandler;
    static ILogger sLogger;

    public static void appDidLaunch(Activity activity, String appToken) {
        if(sEventHandler == null && validateAppToken(appToken)) {
            sEventHandler = new EventHandler(activity, appToken);
        }

        trackInstall();
    }

    private static void trackInstall() {
        if(sEventHandler != null) {
            sEventHandler.track("install-sdk");
        }
        else {
            ShopLoveTracking.logger().warn("ShopLoveTracking has not yet been initialized! First call appDidLaunch() with your appToken");
        }
    }

    public static void setLogLevel(ILogger.LogLevel logLevel) {
        logger().setLogLevel(logLevel);
    }

    public static void setLogger(ILogger logger) {
        sLogger = logger;
    }

    public static ILogger logger() {
        if(sLogger == null) {
            sLogger = new Logger();
        }

        return sLogger;
    }


    private static boolean validateAppToken(final String appToken) {
        return appToken != null && Pattern.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", appToken);
    }

}
