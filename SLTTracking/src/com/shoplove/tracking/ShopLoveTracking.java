package com.shoplove.tracking;

import android.app.Activity;

import java.util.regex.Pattern;

/**
 * The main interface of the ShopLove Tracking SDK.
 * It provides the basic functionality.
 */
public class ShopLoveTracking {

    static EventHandler sEventHandler;
    static ILogger sLogger;

    /**
     * Call this method as soon as your app is getting started.
     * If the pass AppToken is valid, the ShopLove Tracking SDK gets activated and sends an initial 'install-sdk' event.
     * The SDK cannot be activated if you pass an invalid AppToken. Thus, all upcomin calls to the SDK will be ignored.
     *
     * @param activity An activity to setup the SDK.
     * @param appToken Your AppToken.
     *  @return True, if the SDK has been activated.
     */
    public static boolean appDidLaunch(Activity activity, String appToken) {
        if(sEventHandler == null && validateAppToken(appToken)) {
            sEventHandler = new EventHandler(activity, appToken);
        }

        trackInstall();

        return sEventHandler != null;
    }

    /**
     * Tracks the install-sdk event.
     */
    private static void trackInstall() {
        if(sEventHandler != null) {
            sEventHandler.track("install-sdk");
        }
        else {
            ShopLoveTracking.logger().error("ShopLoveTracking has not yet been initialized correctly! First call appDidLaunch() with your appToken!");
        }
    }

    /**
     * Define the amount of logs.
     *
     * @param logLevel An appropriate loglevel.
     */
    public static void setLogLevel(ILogger.LogLevel logLevel) {
        logger().setLogLevel(logLevel);
    }

    /**
     * Here you can set your own logger implementation.
     *
     * @param logger Your logger implementation.
     */
    public static void setLogger(ILogger logger) {
        sLogger = logger;
    }

    /**
     * The returned logger will be used in the whole ShopLove Tracking SDK.
     *
     * @return The used Logger object.
     */
    public static ILogger logger() {
        if(sLogger == null) {
            sLogger = new Logger();
        }

        return sLogger;
    }


    /**
     * Validates the passed token by a simple pattern matching.
     *
     * @param appToken The AppToken to check.
     * @return True if it's a valid token.
     */
    private static boolean validateAppToken(final String appToken) {
        return appToken != null && Pattern.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", appToken);
    }

}
