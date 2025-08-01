// @flow
import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

// type OtpEnv = 'staging' | 'sandbox' | 'production';

export interface Spec extends TurboModule {
    // module methods
    startBeacon(shopDomain: string, token: string, debug: boolean): Promise<void>;
    logRequest(requestUrl: string): Promise<void>;
    updateSessionToken(token: string): Promise<void>;
    renderOtpWidget(widgetToken: string, envString: string, debug: boolean): Promise<string>;
}

export default TurboModuleRegistry.get<Spec>(
    'RiskifiedSdk',
) as Spec | null;
