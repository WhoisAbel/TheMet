package io.github.metmuseum.themet.common.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import timber.log.Timber

fun safeNavigate(navController: NavController, direction: NavDirections) {
    try {
        navController.navigate(direction)
    } catch (e: Exception) {
        Timber.tag("Navigate").d(e)
    }

}
