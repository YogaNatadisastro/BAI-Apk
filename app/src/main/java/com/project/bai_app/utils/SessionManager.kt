package com.project.bai_app.utils

import android.content.Context

class SessionManager (context: Context) {

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val PREFS_NAME = "bai_apps_prefs"
        private const val KEY_ACCESS = "access_token"
    }

}