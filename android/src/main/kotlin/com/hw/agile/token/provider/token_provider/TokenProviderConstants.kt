package com.hw.agile.token.provider.token_provider


class TokenProviderConstants {
    companion object {
        const val TOKEN_KEY = "___fii_token_key___"

        const val URI = "content://com.hw.agile.token.provider.token_provider.TokenProvider/"
        const val AUTHORITY = "com.hw.agile.token.provider.token_provider.TokenProvider"

        const val SAVE_TOKEN_METHOD = "getTokenMethod"
        const val GET_TOKEN_METHOD = "saveTokenMethod"

        const val DATA_KEY = "___fii_data_key___"
        const val SAVE_DATA_METHOD = "getDataMethod"
        const val GET_DATA_METHOD = "saveDataMethod"
    }
}