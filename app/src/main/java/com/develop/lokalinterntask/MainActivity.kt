package com.develop.lokalinterntask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.develop.lokalinterntask.ui.navigation.MainNavGraph
import com.develop.lokalinterntask.ui.theme.LokalInternTaskTheme
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //LokalApp()
            LokalInternTaskTheme {
                MainNavGraph(navController = rememberNavController())
            }
        }
    }
}

