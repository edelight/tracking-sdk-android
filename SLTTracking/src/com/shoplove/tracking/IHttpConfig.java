package com.shoplove.tracking;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public interface IHttpConfig {

    /**
     * @return Base Url to the API.
     */
    public String getBaseUrl();

    /**
     * @return Current SDK Version number.
     */
    public String getClientSdk();

    /**
     * @return A valid User-Agent for all Requests.
     */
    public String getUserAgent();

    /**
     * @return The Vendor Id of the device.
     */
    public String getVendorId();

}
