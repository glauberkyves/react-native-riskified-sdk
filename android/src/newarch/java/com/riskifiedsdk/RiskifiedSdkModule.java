package com.riskifiedsdk;

import android.app.Activity;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class RiskifiedSdkModule extends NativeRiskifiedSdkSpec {
    private RiskifiedSdkModuleImpl riskifiedSdk;

    RiskifiedSdkModule(ReactApplicationContext context) {
        super(context);
        this.riskifiedSdk = new RiskifiedSdkModuleImpl(context);
    }

    @Override
    @NonNull
    public String getName() {
        return RiskifiedSdkModuleImpl.NAME;
    }

    @Override
    public void startBeacon(String shopDomain, String token, boolean debug, Promise promise) {
        riskifiedSdk.startBeacon(shopDomain, token, debug, promise);
    }

    @Override
    public void logRequest(String requestUrl, Promise promise) {
        riskifiedSdk.logRequest(requestUrl, promise);
    }

    @Override
    public void updateSessionToken(String token, Promise promise) {
        riskifiedSdk.updateSessionToken(token, promise);
    }


  @Override
  public void renderOtpWidget(String widgetToken, String envString, boolean debug, Promise promise) {
    Activity currentActivity = getCurrentActivity();
    riskifiedSdk.renderOtpWidget(currentActivity, widgetToken, envString, debug, promise);
  }
}
