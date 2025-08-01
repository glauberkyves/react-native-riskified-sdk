#import "RiskifiedSdk.h"
#import <RiskifiedBeacon/RiskifiedBeacon.h>
#import <RiskifiedBeacon/RiskifiedBeacon-Swift.h>
#import <React/RCTLog.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "RiskifiedSdkSpec.h"
#import <ReactCommon/RCTTurboModule.h>
#endif

@implementation RiskifiedSdk
static BOOL isBeaconInitialized = NO;

RCT_EXPORT_MODULE(RiskifiedSdk)

RCT_REMAP_METHOD(startBeacon,
                useShop:(NSString *)shopDomain
                token:(NSString *)token
                debug:(BOOL)debug
                withResolver:(RCTPromiseResolveBlock)resolve
                withRejecter:(RCTPromiseRejectBlock)reject)
{
    if (isBeaconInitialized) {
        resolve(nil);
        return;
    }
    [RiskifiedBeacon startBeacon:shopDomain sessionToken:token debugInfo:debug];
    isBeaconInitialized = YES;
    resolve(nil);
}

RCT_REMAP_METHOD(updateSessionToken,
                useToken:(NSString *)token
                withResolver:(RCTPromiseResolveBlock)resolve
                withRejecter:(RCTPromiseRejectBlock)reject)
{
    [RiskifiedBeacon updateSessionToken:token];
    resolve(nil);
}

RCT_REMAP_METHOD(logRequest,
                useUrlString:(NSString *)requestUrl
                withResolver:(RCTPromiseResolveBlock)resolve
                withRejecter:(RCTPromiseRejectBlock)reject)
{
    NSURL *url = [NSURL URLWithString:requestUrl];
    [RiskifiedBeacon logRequest:url];
    resolve(nil);
}

RCT_REMAP_METHOD(renderOtpWidget,
                 widgetToken:(NSString *)widgetToken
                 envString:(NSString *)envString
                 debug:(BOOL)debug
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)
{
    if (@available(iOS 13.0, *)) {
        dispatch_async(dispatch_get_main_queue(), ^{
            UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;

            OtpEnv env = [self otpEnvFromString:envString];

            OtpConfig *config = [[OtpConfig alloc] initWithWidgetToken:widgetToken
                                                   verificationHandler:^(NSString *challengeAccessToken) {
                                                       resolve(challengeAccessToken);
                                                   }
                                                   widgetClosedHandler:^{
                                                       reject(@"widget_closed", @"OTP widget was closed", nil);
                                                   }
                                                   onTimeoutHandler:^{
                                                       reject(@"timeout", @"OTP widget timed out", nil);
                                                   }
                                                   parent:rootViewController
                                                   env:env
                                                   isDebug:debug];

            [RiskifiedBeacon OfferOTP:config];
        });
    } else {
        reject(@"UNSUPPORTED_IOS_VERSION", @"OfferOTP requires iOS 13.0 or later", nil);
    }
}

- (OtpEnv)otpEnvFromString:(NSString *)envString {
    if ([envString.lowercaseString isEqualToString:@"production"]) {
        return OtpEnvProduction;
    } else if ([envString.lowercaseString isEqualToString:@"sandbox"]) {
        return OtpEnvSandbox;
    } else if ([envString.lowercaseString isEqualToString:@"staging"]) {
        return OtpEnvStaging;
    } else {
        return OtpEnvSandbox;
    }
}

#ifdef RCT_NEW_ARCH_ENABLED
// TurboModule support
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRiskifiedSdkSpecJSI>(params);
}
#endif

@end
