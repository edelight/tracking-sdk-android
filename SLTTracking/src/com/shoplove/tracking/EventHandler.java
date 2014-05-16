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
 * The EventHandler acts as the core handler for all events.
 * It creates the required HttpClient and constructs the basic header for all requests made by the ShopLove Tracking SDK.
 */
public class EventHandler {

    /** An implementation of the IRequestHandler interface. Used to enqueue the created events. */
    private IRequestHandler mRequestHandler;

    /** A HttpConfiguration for the HttpClient and all generated requests. */
    private IHttpConfig mHttpConfig;

    /** The HttpClient used to perform the requests. */
    private AbstractHttpClient mHttpClient;

    /** The AppToken of the specific Client. */
    private String mAppToken;

    /**
     * Creates a new EventHandler.
     *
     * @param activity An activity to setup SDK.
     * @param appToken The client AppToken.
     */
    EventHandler(Activity activity, final String appToken) {
        mAppToken = appToken;
        mHttpClient = createHttpClient();
        mHttpConfig = new ShopLoveHttpConfig(activity);
        mRequestHandler = new RequestHandler(activity, mHttpClient, mHttpConfig);
    }

    /**
     * Creates a new Event to pass it to the RequestHandler.
     * If it's not a valid action, the call will be ignored.
     *
     * @param action The action string to track.
     */
    public void track(String action) {

        if(action == null || action.isEmpty()) {
            ShopLoveTracking.logger().warn("Empty Action!");
            return;
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("action", action);

        Map<String, String> headers = getBaseHeaders();

        Event event = new Event(null, headers, params);

        if(!mRequestHandler.addTrackEvent(event)) {
            ShopLoveTracking.logger().error("Malformed TrackEvent " + event);
        }
    }

    /**
     * Creates and initializes a new HttpClient for the SDK.
     *
     * @return The created HttpClient.
     */
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

    /**
     * Creates and returns the basic header fields for all requests.
     *
     * @return The header fields for the requests.
     */
    private Map<String, String> getBaseHeaders() {

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
