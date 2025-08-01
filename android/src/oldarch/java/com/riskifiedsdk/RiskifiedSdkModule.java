package com.riskifiedsdk;

import android.app.Activity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class RiskifiedSdkModule extends ReactContextBaseJavaModule {
    private RiskifiedSdkModuleImpl riskifiedSdk;

    RiskifiedSdkModule(ReactApplicationContext context) {
        super(context);
        this.riskifiedSdk = new RiskifiedSdkModuleImpl(context);
    }

    @Override
    public String getName() {
        return RiskifiedSdkModuleImpl.NAME;
    }

    @ReactMethod
    public void startBeacon(String shopDomain, String token, Boolean debug, Promise promise) {
        riskifiedSdk.startBeacon(shopDomain, token, debug, promise);
    }

    @ReactMethod
    public void logRequest(String requestUrl, Promise promise) {
        riskifiedSdk.logRequest(requestUrl, promise);
    }

    @ReactMethod
    public void updateSessionToken(String token, Promise promise) {
        riskifiedSdk.updateSessionToken(token, promise);
    }

  @ReactMethod
  public void renderOtpWidget(String widgetToken, String envString, boolean debug, Promise promise) {
    Activity currentActivity = getCurrentActivity();
    riskifiedSdk.renderOtpWidget(currentActivity, widgetToken, envString, debug, promise);
  }
}
