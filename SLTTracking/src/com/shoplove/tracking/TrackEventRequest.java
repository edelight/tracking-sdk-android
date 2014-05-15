package com.shoplove.tracking;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class TrackEventRequest extends Request<String> {

    private ITrackEvent mTrackEvent;

    public TrackEventRequest(String baseUrl, ITrackEvent trackEvent) {
        super(Method.POST, assembledUrl(baseUrl, trackEvent), null);

        mTrackEvent = trackEvent;

        setRetryPolicy(new DefaultRetryPolicy(10000, 3, 2));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError { return mTrackEvent.getTrackEventParams(); }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError { return mTrackEvent.getTrackEventHeaders(); }

    @Override
    protected void deliverResponse(String response) {
        try {
            ShopLoveTracking.logger().debug("Deliver Response for " + this.getUrl() + " " + getParams() + " " + response);
        }catch (Exception exc) {
            ShopLoveTracking.logger().debug("Deliver Response for " + this.getUrl() + " " + response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        ShopLoveTracking.logger().error("Deliver Error " + error);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        String parsed;
        try {
            if(volleyError.networkResponse != null && volleyError.networkResponse.headers != null) {
                parsed = new String(volleyError.networkResponse.data, HttpHeaderParser.parseCharset(volleyError.networkResponse.headers));
            }else {
                parsed = "Could not parse NetworkResponse appropriately!";
            }

        } catch (UnsupportedEncodingException e) {
            parsed = new String(volleyError.networkResponse.data);
        }

        if(volleyError.networkResponse != null) {
            ShopLoveTracking.logger().error("ERROR: " + volleyError.networkResponse.statusCode + " " + parsed + " " + volleyError.networkResponse.headers + " " + volleyError.networkResponse.data);
        } else {

        }

        return volleyError;
    }


    private static String assembledUrl(String baseUrl, ITrackEvent trackEvent) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(baseUrl);

        if(trackEvent.getTrackEventPath() != null && !trackEvent.getTrackEventPath().isEmpty()) {
            urlBuilder.append(baseUrl).append(trackEvent.getTrackEventPath()).toString();
        }

        return urlBuilder.toString();
    }

}
