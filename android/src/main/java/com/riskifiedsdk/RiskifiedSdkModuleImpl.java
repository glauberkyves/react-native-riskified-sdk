package com.riskifiedsdk;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;

import com.riskified.android_sdk.RiskifiedBeaconMain;
import com.riskified.android_sdk.RiskifiedBeaconMainInterface;
import com.riskified.android_sdk.otp.OtpWidgetEventsHandler;
import com.riskified.android_sdk.otp.OtpEnv;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

public class RiskifiedSdkModuleImpl {
  public static final String NAME = "RiskifiedSdk";
  private RiskifiedBeaconMainInterface RXBeacon;
  private Context context;

  public RiskifiedSdkModuleImpl(ReactApplicationContext context) {
    this.context = context;
  }

  public void startBeacon(String shopDomain, String token, Boolean debug, Promise promise) {
    if (RXBeacon == null) {
      try {
        RXBeacon = new RiskifiedBeaconMain();
        RXBeacon.startBeacon(shopDomain, token, debug, context);
        promise.resolve(null);
      } catch (Exception e) {
        promise.reject("startBeacon error", e.getMessage());
      }
    }
  }

  public void logRequest(String requestUrl, Promise promise) {
    if (RXBeacon != null) {
      RXBeacon.logRequest(requestUrl);
    }
    promise.resolve(null);
  }

  public void updateSessionToken(String token, Promise promise) {
    if (RXBeacon != null) {
      RXBeacon.updateSessionToken(token);
    }
    promise.resolve(null);
  }

  public void renderOtpWidget(Activity currentActivity, String widgetToken, String envString, boolean debug, Promise promise) {
    OtpEnv env = OtpEnv.valueOf(envString.toUpperCase());

    OtpWidgetEventsHandler eventsHandler = new OtpWidgetEventsHandler() {
      @Override
      public void onVerifiedHandler(String challengeAccessToken) {
        promise.resolve(challengeAccessToken);
      }

      @Override
      public void onTimeoutHandler() {
        promise.reject("timeout", "OTP widget timed out");
      }

      @Override
      public void widgetClosedHandler() {
        promise.reject("widget_closed", "OTP widget was closed");
      }
    };

    if (RXBeacon != null) {
      try {
        if (currentActivity != null) {
          RXBeacon.renderOtpWidget(currentActivity, widgetToken, eventsHandler, env, debug);
        } else {
          promise.reject("activity_null", "Activity is null, not rendering OTP");
        }
      } catch(Exception e) {
        promise.reject("render_error", "Error rendering OTP widget", e);
      }
    }
  }
}
