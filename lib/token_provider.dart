import 'token_provider_platform_interface.dart';

class TokenProvider {
  Future<String?> getToken() {
    return TokenProviderPlatform.instance.getToken();
  }
}
