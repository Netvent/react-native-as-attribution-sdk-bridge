#import "AsAttributionSdkBridge.h"
#import <React/RCTLog.h>
@import ASAttributionSDK;

@implementation AsAttributionSdkBridge

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(track:(NSString *)eventName)
{
    [ASAttribution trackWithEventName:eventName];
}

RCT_EXPORT_METHOD(trackWithData:(NSString *)eventName data:(NSDictionary<NSString *,id> * _Nonnull)data)
{
    [ASAttribution trackWithEventName:eventName data:data];
}

RCT_EXPORT_METHOD(setLogLevel:(NSString *)logLevel)
{
	dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
    	if ([logLevel isEqualToString:@"OFF"]) {
	        [ASAttribution setLogLevel:ASLogLevelOff];
	    } else if ([logLevel isEqualToString:@"DEBUG"]) {
	        [ASAttribution setLogLevel:ASLogLevelDebug];
	    } else if ([logLevel isEqualToString:@"ERROR"]) {
	        [ASAttribution setLogLevel:ASLogLevelError];
	    } else if ([logLevel isEqualToString:@"WARNING"]) {
	        [ASAttribution setLogLevel:ASLogLevelWarning];
	    }  
	});
}

@end