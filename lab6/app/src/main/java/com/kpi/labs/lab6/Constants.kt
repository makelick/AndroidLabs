package com.kpi.labs.lab6

object AuthConstants {
    val SHARED_PREFERENCES_NAME = "AUTH_STATE_PREFERENCE"
    val AUTH_STATE = "AUTH_STATE"

    val SCOPE_PROFILE = "profile"
    val SCOPE_EMAIL = "email"
    val SCOPE_OPENID = "openid"

    val DATA_PICTURE = "picture"
    val DATA_FIRST_NAME = "given_name"
    val DATA_LAST_NAME = "family_name"
    val DATA_EMAIL = "email"

    val CLIENT_ID = "YOUR_CLIENT_ID"
    val CODE_VERIFIER_CHALLENGE_METHOD = "S256"
    val MESSAGE_DIGEST_ALGORITHM = "SHA-256"

    val URL_AUTHORIZATION = "https://accounts.google.com/o/oauth2/v2/auth"
    val URL_TOKEN_EXCHANGE = "https://www.googleapis.com/oauth2/v4/token"
    val URL_AUTH_REDIRECT = "com.kpi.labs.lab6:/oauth2redirect"
}