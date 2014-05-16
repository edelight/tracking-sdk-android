package com.shoplove.tracking;

import android.util.Log;

import java.util.Locale;

/**
 * A basic logger for the sdk.
 */
public class Logger implements ILogger {

    public static final String LOGGER_TAG = "ShopLoveLogger";

    private LogLevel logLevel;

    public Logger() {
        setLogLevel(LogLevel.INFO);
    }

    @Override
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void setLogLevelString(String logLevelString) {
        if (null != logLevelString) {
            try {
                setLogLevel(LogLevel.valueOf(logLevelString.toUpperCase(Locale.US)));
            } catch (IllegalArgumentException iae) {
                error(String.format("Malformed logLevel '%s', falling back to 'info'", logLevelString));
            }
        }
    }

    @Override
    public void verbose(String message) {
        if (logLevel.androidLogLevel <= Log.VERBOSE) {
            Log.v(LOGGER_TAG, message);
        }
    }

    @Override
    public void debug(String message) {
        if (logLevel.androidLogLevel <= Log.DEBUG) {
            Log.d(LOGGER_TAG, message);
        }
    }

    @Override
    public void info(String message) {
        if (logLevel.androidLogLevel <= Log.INFO) {
            Log.i(LOGGER_TAG, message);
        }
    }

    @Override
    public void warn(String message) {
        if (logLevel.androidLogLevel <= Log.WARN) {
            Log.w(LOGGER_TAG, message);
        }
    }

    @Override
    public void error(String message) {
        if (logLevel.androidLogLevel <= Log.ERROR) {
            Log.e(LOGGER_TAG, message);
        }
    }

    @Override
    public void Assert(String message) {
        Log.println(Log.ASSERT, LOGGER_TAG, message);
    }
}
