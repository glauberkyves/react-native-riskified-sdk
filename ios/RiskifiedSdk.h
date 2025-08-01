#import <React/RCTBridgeModule.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "RiskifiedSdkSpec.h"
#import <ReactCommon/RCTTurboModule.h>
#import <memory>
#endif

#ifdef RCT_NEW_ARCH_ENABLED
@interface RiskifiedSdk : NSObject <NativeRiskifiedSdkSpec, RCTTurboModule>
#else
@interface RiskifiedSdk : NSObject <RCTBridgeModule>
#endif

@end
