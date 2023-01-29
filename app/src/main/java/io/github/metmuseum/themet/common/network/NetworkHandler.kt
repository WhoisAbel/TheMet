package io.github.metmuseum.themet.common.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Checks if a network connection exists.
 */
@Suppress("DEPRECATION")
class NetworkHandler @Inject constructor(private val context: Context) {

    fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

}