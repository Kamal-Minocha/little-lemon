package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    val hasUserData = hasUserDataInSharedPreferences()
    NavHost(
        navController = navController,
        startDestination = if(hasUserData) HomeDestination.route else OnBoardingDestination.route
    ) {
        composable(OnBoardingDestination.route) { OnBoarding(navController = navController) }
        composable(HomeDestination.route) { Home(navController = navController) }
        composable(ProfileDestination.route) { Profile(navController = navController) }
    }
}

@Composable
fun hasUserDataInSharedPreferences(): Boolean {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE)
    val email = sharedPreferences.getString(EMAIL, "") ?: ""
    return email.isNotBlank()
}