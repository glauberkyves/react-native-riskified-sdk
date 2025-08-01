"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;
var _reactNative = require("react-native");
// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;
const RiskifiedSdk = isTurboModuleEnabled ? require("./NativeRiskifiedSdk").default : _reactNative.NativeModules.RiskifiedSdk;
var _default = RiskifiedSdk;
exports.default = _default;
//# sourceMappingURL=index.js.map