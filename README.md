# react-native-riskified-sdk

Riskified's react native wrapper for mobile SDK's 

## Installation

```sh
npm install @riskified/react-native-riskified-sdk
```

## Usage

```js
import RiskifiedSdk from '@riskified/react-native-riskified-sdk';

// On main page load
RiskifiedSdk.startBeacon(
  shopDomain: string,
  token: string,
  debug: boolean
): Promise<void>;

// On significant events and page visits
RiskifiedSdk.logRequest(
  requestUrl: string
): Promise<void>;

// If session ID updates for the user
RiskifiedSdk.updateSessionToken(
  newToken: string
): Promise<void>;

// To render OTP widget
RiskifiedSdk.renderOtpWidget(
  widgetToken: string,
  environment: 'sandbox' | 'staging' | 'production',
  debug: boolean
): Promise<string>;

// example for rendering the OTP widget
RiskifiedSdk.renderOtpWidget('token123', 'sandbox', true)
    .then((challengeAccessToken : string) => {
        // handle success verification
    })
    .catch((error) => {
        if (error.code === 'timeout') {
            // handle timeout
        } else if (error.code === 'widget_closed') {
            // handle close
        } else {
            // handle other errors
        }
    });

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
