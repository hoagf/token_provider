package com.hw.agile.token.provider.token_provider


import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log

class TokenProvider : ContentProvider() {

    companion object {
        private val TAG = ContentProvider::class.java.simpleName
        const val TOKEN_PREFERENCES = "tokenPreferences"
    }

    private lateinit var prefs: SharedPreferences

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        return if (context != null) {
            prefs = context!!.getSharedPreferences(TOKEN_PREFERENCES, Context.MODE_PRIVATE)
            true
        } else {
            Log.d(TAG, "context is null")
            false
        }
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

    override fun call(method: String, arg: String?, extras: Bundle?): Bundle {
        val bundle = Bundle()
        when (method) {
            TokenProviderConstants.GET_TOKEN_METHOD -> {
                val str: String = getStringPreference()
                bundle.putString(TokenProviderConstants.TOKEN_KEY, str)
            }
            TokenProviderConstants.SAVE_TOKEN_METHOD -> {
                putStringPreference(arg ?: "")
            }
        }

        return bundle
    }

    private fun getStringPreference(): String {
        return prefs.getString(
            TokenProviderConstants.TOKEN_KEY,
            ""
        ) ?: ""
    }

    private fun putStringPreference(str: String) {
        with(prefs.edit()) {
            putString(
                TokenProviderConstants.TOKEN_KEY,
                str
            )
            commit()
        }
    }



}