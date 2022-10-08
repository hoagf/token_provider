import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'token_provider_method_channel.dart';

abstract class TokenProviderPlatform extends PlatformInterface {
  /// Constructs a TokenProviderPlatform.
  TokenProviderPlatform() : super(token: _token);

  static final Object _token = Object();

  static TokenProviderPlatform _instance = MethodChannelTokenProvider();

  /// The default instance of [TokenProviderPlatform] to use.
  ///
  /// Defaults to [MethodChannelTokenProvider].
  static TokenProviderPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [TokenProviderPlatform] when
  /// they register themselves.
  static set instance(TokenProviderPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getToken() {
    throw UnimplementedError('getToken() has not been implemented.');
  }

  Future<bool?> saveToken(String token) {
    throw UnimplementedError('getToken() has not been implemented.');
  }
}
