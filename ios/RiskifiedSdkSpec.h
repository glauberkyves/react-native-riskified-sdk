#ifdef RCT_NEW_ARCH_ENABLED

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <ReactCommon/RCTTurboModule.h>
#import <memory>

@protocol NativeRiskifiedSdkSpec <NSObject, RCTBridgeModule>
@end

namespace facebook {
namespace react {

class JSI_EXPORT NativeRiskifiedSdkSpecJSI : public ObjCTurboModule {
 public:
  NativeRiskifiedSdkSpecJSI(const ObjCTurboModule::InitParams &params);
};

} // namespace react
} // namespace facebook

#endif
