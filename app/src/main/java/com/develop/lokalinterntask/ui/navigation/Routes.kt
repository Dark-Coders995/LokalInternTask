package com.develop.lokalinterntask.ui.navigation

sealed class Routes(val route: String) {
    object BottomBar : Routes("bottomBar")
}