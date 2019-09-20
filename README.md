# React Native Bridge For AppSamurai Attribution SDK
React Native Bridge For AppSamurai Attribution SDK is a project for React Native users to use AppSamurai Attribution SDK.

[![alt text](https://appsamurai.com/wp-content/uploads/2018/10/as_dark_logotype-8.png "AppSamurai")](https://www.appsamurai.com)

## Getting Started
Add react-native-as-attribution-sdk-bridge to your dependencies with one of the options below(yarn or npm);
``` shell
yarn add react-native-as-attribution-sdk-bridge
```
``` shell
npm -install --save react-native-as-attribution-sdk-bridge
```
Linking library
react-native 0.60+ handles autolinking as it mentioned in [autolinking in react-native](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md).
For react-native 0.60- version auto linking needs to be done to use libraries with native dependencies correctly. Please refer detailed explanation from [Linking Libraries in iOS](https://facebook.github.io/react-native/docs/linking-libraries-ios.html)
``` shell
react-native link react-native-as-attribution-sdk-bridge
```
### iOS Platform Notes
AppSamurai Attribution SDK targets iOS 10 or higher.
Projects those use CocoaPods do not forget to run `pod install`. You need to initialize ASAttributionSDK to use AppSamurai Attribution SDK. 
First, you need to import ASAttributionSDK module in your AppDelegate file;
```objc
@import ASAttributionSDK;
```

Then you need to add the following initialization line to your application’s `didFinishLaunchingWithOptions` method in AppDelegate:
```objc
[ASAttribution initialize:@"your-user-id"];
```
"your-user-id" parameter should be your user id retrieved from AppSamurai User Dashboard, you can use your user id for integration purposes.

### Android Platform Notes
Minimum supported SDK version is 14.
You need to add `multidex` support to Android project. You can do this by adding the following line to app’s gradle.
```groovy
defaultConfig {
    ....
    multiDexEnabled true
    ...
}
```

Add the following dependency to the `dependencies` block in app/build.gradle. Be sure to add the latest release for attribution sdk which is: [ ![Download](https://api.bintray.com/packages/appsamurai/maven/attribution/images/download.svg) ](https://bintray.com/appsamurai/maven/attribution/_latestVersion)
```groovy
implementation 'com.ppsmr.attribution.attributionsdk:attribution:1.0.2'
```

You need to initialize ASAttributionSDK to use AppSamurai Attribution SDK. 
First, you need to import AttributionSDK module in your MainApplication file;
```java
import com.ppsmr.attribution.attributionsdk.AttributionSDK;
```

Then you need to add the following initialization line to your application’s `onCreate` method in MainApplication file:
```java
@Override
public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    AttributionSDK.Instance.initialize("your-user-id");
}
```
"your-user-id" parameter should be your user id retrieved from AppSamurai User Dashboard, you can use your user id for integration purposes.

**For Publishers Supporting Android API Level < 21**

There are dependency problems for API Level lower than 21 when multi dex is enabled. In order to fix these problem, please import androidx.multidex.MultiDexApplication package instead of android.app.Application package in your MainApplication file:
```java
import androidx.multidex.MultiDexApplication;
```

Then extend your MainApplication class with MultiDexApplication instead of Application class:
```java
public class MainApplication extends MultiDexApplication implements ReactApplication {
    ...
}
```

## Usage in React-Native
**Import the module**
``` js
import AsAttributionSdkBridge from 'react-native-as-attribution-sdk-bridge';
```
**Custom Event Tracking**

Using AppSamurai Attribution SDK, you are able to track the frequency of custom events by placing the following code piece into your own application code. You can also attach data to your events. If you are planning to attach data to your event, make sure your map is JSON serializable.
``` js
AsAttributionSdkBridge.track("string-event-name")
AsAttributionSdkBridge.trackWithData(map<String:Object>)
```
Examples:
``` js
// Track event with no event data
AsAttributionSdkBridge.track("user-login")

// Track event with data
var dataMap = {
    "type": "water-bottle",
    "amount": 40.0,
    "currency": "dollar",
    "properties": {
        "color": "blue",
        "size": "L"
    },
    "last-product-views": ["Bags", "Shorts", "Bottles"]
}
AsAttributionSdkBridge.trackWithData("purchase", dataMap)
```

**Log Level**

AppSamurai SDK logging level can be changed with setLogLevel after SDK initialization. Available log levels are "OFF", "DEBUG", "WARNING" and "ERROR". Default log level is "DEBUG".
``` js
AsAttributionSdkBridge.setLogLevel("string-log-level")
```

## Author
App Samurai Mobile Team, mobile@appsamurai.com
## License
Copyright 2018 App Samurai Inc.
[![alt text](https://appsamurai.com/wp-content/uploads/2014/12/web_home_cta_2.png "AppSamurai")](https://www.appsamurai.com)