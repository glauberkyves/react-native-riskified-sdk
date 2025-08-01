import type { TurboModule } from 'react-native';
export interface Spec extends TurboModule {
    startBeacon(shopDomain: string, token: string, debug: boolean): Promise<void>;
    logRequest(requestUrl: string): Promise<void>;
    updateSessionToken(token: string): Promise<void>;
    renderOtpWidget(widgetToken: string, envString: string, debug: boolean): Promise<string>;
}
declare const _default: Spec | null;
export default _default;
//# sourceMappingURL=NativeRiskifiedSdk.d.ts.map