import { NativeModules } from 'react-native';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;
const RiskifiedSdk = isTurboModuleEnabled ? require("./NativeRiskifiedSdk").default : NativeModules.RiskifiedSdk;
export default RiskifiedSdk;
//# sourceMappingURL=index.js.map