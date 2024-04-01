import 'token_provider_platform_interface.dart';

class TokenProvider {
  Future<String?> getToken() {
    return TokenProviderPlatform.instance.getToken();
  }

  Future<bool?> saveToken(String token) {
    return TokenProviderPlatform.instance.saveToken(token);
  }

  Future<String?> getData() {
    return TokenProviderPlatform.instance.getData();
  }

  Future<bool?> saveData(String data) {
    return TokenProviderPlatform.instance.saveData(data);
  }
}
