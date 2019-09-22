package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.ppsmr.attribution.attributionsdk.AttributionSDK;
import com.ppsmr.attribution.attributionsdk.util.ASLog;
import com.react.util.MapUtil;

import org.json.JSONException;

public class AsAttributionSdkBridgeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public AsAttributionSdkBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "AsAttributionSdkBridge";
    }

    @ReactMethod
    public void track(String eventName) {
        AttributionSDK.Instance.track(eventName);
    }

    @ReactMethod
    public void trackWithData(String eventName, ReadableMap data) {
        try{
            AttributionSDK.Instance.track(eventName, MapUtil.toJSONObject(data));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @ReactMethod
    public void setLogLevel(String logLevel) {
        switch (logLevel) {
            case "OFF":
                AttributionSDK.Instance.setLogLevel(ASLog.Level.OFF);
                break;
            case "DEBUG":
                AttributionSDK.Instance.setLogLevel(ASLog.Level.DEBUG);
                break;
            case "WARNING":
                AttributionSDK.Instance.setLogLevel(ASLog.Level.WARNING);
                break;
            case "ERROR":
                AttributionSDK.Instance.setLogLevel(ASLog.Level.ERROR);
                break;
            default:
                break;
        }
    }
}
