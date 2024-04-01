import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'token_provider_platform_interface.dart';

/// An implementation of [TokenProviderPlatform] that uses method channels.
class MethodChannelTokenProvider extends TokenProviderPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('token_provider');

  @override
  Future<String?> getToken() async {
    final token = await methodChannel.invokeMethod<String>('getToken');
    return token;
  }

  @override
  Future<bool?> saveToken(String token) async {
    final result =
        await methodChannel.invokeMethod<bool>('saveToken', {'token': token});
    return result;
  }

  @override
  Future<String?> getData() async {
    final data = await methodChannel.invokeMethod<String>('getData');
    return data;
  }

  @override
  Future<bool?> saveData(String data) async {
    final result =
    await methodChannel.invokeMethod<bool>('saveData', {'data': data});
    return result;
  }
}
