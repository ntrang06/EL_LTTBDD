
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.screens.PermissionScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {

                // Mỗi lần mở app sẽ bắt đầu lại
                // từ màn hình xin quyền đầu tiên.
                var completed by remember {
                    mutableStateOf(false)
                }

                if (!completed) {
                    PermissionScreen(
                        onFinished = {
                            completed = true
                        }
                    )
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Đã hoàn thành xử lý quyền!",
            style = MaterialTheme.typography.titleLarge
        )
    }
}
