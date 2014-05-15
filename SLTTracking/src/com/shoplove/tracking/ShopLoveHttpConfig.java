package com.shoplove.tracking;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import java.util.Locale;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class ShopLoveHttpConfig implements IHttpConfig {

    private static final String BASE_URL = "https://api.shoplove.com/v6/trackings";
    private static final String CLIENT_SDK = "1.0.0";

    private String mUserAgent;
    private String mVendorId;

    ShopLoveHttpConfig(Context context) {
        mUserAgent = createUserAgent(context);
        mVendorId = getVendorId(context);
    }

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public String getClientSdk() {
        return CLIENT_SDK;
    }

    @Override
    public String getUserAgent() {
        return mUserAgent;
    }

    @Override
    public String getVendorId() {
        return mVendorId;
    }


    private String createUserAgent(Context context) {
        String applicationName = "";
        String versionName = "";

        try {
            final PackageManager pm = context.getPackageManager();
            if(pm != null) {
                ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), 0);
                PackageInfo pi = pm.getPackageArchiveInfo(context.getPackageName(), 0);
                if(pi != null) {
                    versionName = pi.versionName;
                }
                if(ai != null) {
                    applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(Unknown)");
                }
            }
        } catch (final PackageManager.NameNotFoundException ex) {

        } catch (final IllegalStateException ex) {

        }

        final String userAgent = "" + applicationName + "/" + versionName + " (" + android.os.Build.MODEL + "; Android " + android.os.Build.VERSION.RELEASE + "; " + Locale.getDefault().toString() + ")";

        return userAgent;
    }

    private String getVendorId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
