package com.hw.agile.token.provider.token_provider


import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.PluginRegistry.Registrar

class TokenProviderPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private var context: Context? = null
    private var channel: MethodChannel? = null
    private var flutterPluginBinding: FlutterPluginBinding? = null
    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
        flutterPluginBinding = binding
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {}
    private fun saveToken(token: String?) {
        val uri = Uri.parse(TokenProviderConstants.URI)
        val cr = context!!.contentResolver
        val auth = TokenProviderConstants.AUTHORITY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            cr.call(
                auth,
                TokenProviderConstants.SAVE_TOKEN_METHOD,
                token,
                null
            )
        } else {
            cr.call(
                uri,
                TokenProviderConstants.SAVE_TOKEN_METHOD,
                token,
                null
            )
        }
    }

    private val token: String?
        get() {
            val uri = Uri.parse(TokenProviderConstants.URI)
            val cr = context!!.contentResolver
            val auth = TokenProviderConstants.AUTHORITY
            val bundle: Bundle? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                cr.call(auth, TokenProviderConstants.GET_TOKEN_METHOD, null, null)
            } else {
                cr.call(uri, TokenProviderConstants.GET_TOKEN_METHOD, null, null)
            }
            return if (bundle != null) bundle.getString(TokenProviderConstants.TOKEN_KEY) else ""
        }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getToken" -> {
                try {
                    result.success(token)
                } catch (e: Exception) {
                    result.error("ERROR", "Get token error", e)
                }
            }
            "saveToken" -> {
                try {
                    saveToken(call.argument("token"))
                    result.success(true)
                } catch (e: Exception) {
                    result.error("ERROR", "Save token error", e)
                }
            }
        }
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        context = flutterPluginBinding!!.applicationContext
        channel = MethodChannel(
            flutterPluginBinding!!.binaryMessenger, "token_provider"
        )
        context = flutterPluginBinding!!.applicationContext
        channel!!.setMethodCallHandler(this)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivity() {
        if (channel == null) {
            return
        }
        channel!!.setMethodCallHandler(null)
        channel = null
        flutterPluginBinding = null
    }

    companion object {
        const val TAG = "TokenProviderPlugin"
        fun registerWith(registrar: Registrar) {
            val plugin = TokenProviderPlugin()
            plugin.context = registrar.context()
            plugin.channel = MethodChannel(registrar.messenger(), "token_provider")
            plugin.channel!!.setMethodCallHandler(plugin)
        }
    }
}