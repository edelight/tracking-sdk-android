package com.shoplove.tracking;

import android.app.Activity;
import android.content.Context;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class EventHandler {

    private RequestHandler mRequestHandler;

    private IHttpConfig mHttpConfig;
    private AbstractHttpClient mHttpClient;


    private String mAppToken;

    private Context mApplicationContext;

    EventHandler(Activity activity, final String appToken) {
        mAppToken = appToken;
        mApplicationContext = activity.getApplicationContext();
        mHttpClient = createHttpClient();
        mHttpConfig = new ShopLoveHttpConfig(activity);
        mRequestHandler = new RequestHandler(activity, mHttpClient, mHttpConfig);
    }

    public void track(String action) {

        if(action == null || action.isEmpty()) return;

        Map<String, String> params = new HashMap<String, String>();
        params.put("action", action);

        Map<String, String> headers = getBaseHeaders(mApplicationContext);

        Event event = new Event(null, headers, params);

        mRequestHandler.addTrackEvent(event);
    }


    private AbstractHttpClient createHttpClient() {

        // following code solves Adapter is detached error
        // refer: http://stackoverflow.com/questions/5317882/android-handling-back-button-during-asynctask

        BasicHttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));

        org.apache.http.conn.ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);

        DefaultHttpClient client = new org.apache.http.impl.client.DefaultHttpClient(cm, params);

        return client;
    }

    private Map<String, String> getBaseHeaders(Context context) {

        if(mHttpConfig == null) return null;

        Map<String, String> headers = new HashMap<String, String>();

        if(mHttpConfig.getUserAgent() != null && !mHttpConfig.getUserAgent().isEmpty()) {
            headers.put("User-Agent", mHttpConfig.getUserAgent());
        }

        if(mAppToken != null && !mAppToken.isEmpty()) {
            headers.put("AppToken", mAppToken);
        }

        if(mHttpConfig.getClientSdk() != null && !mHttpConfig.getClientSdk().isEmpty()) {
            headers.put("ClientSdk", mHttpConfig.getClientSdk());
        }

        if(mHttpConfig.getVendorId() != null && !mHttpConfig.getVendorId().isEmpty()) {
            headers.put("VendorId", mHttpConfig.getVendorId());
        }

        return headers;
    }

}
