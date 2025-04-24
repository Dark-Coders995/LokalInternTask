package com.develop.lokalinterntask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.develop.lokalinterntask.data.api.JobApiImpl
import com.develop.lokalinterntask.data.repository.BookmarkRepository
import com.develop.lokalinterntask.data.repository.JobRepository
import com.develop.lokalinterntask.ui.theme.LokalInternTaskTheme
import com.develop.lokalinterntask.ui.widgets.BottomBar
import com.develop.lokalinterntask.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LokalApp()
        }
    }
}

@Preview
@Composable
fun LokalApp() {
    val jobApi = JobApiImpl()
    val jobRepository = JobRepository(jobApi)
    val bookmarkRepository = BookmarkRepository()
    MainViewModel(jobRepository, bookmarkRepository)

    LokalInternTaskTheme {
        Scaffold(
            bottomBar = {
                BottomBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Text("gdfjhgds")
            }
        }
    }
}