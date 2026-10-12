package com.example.baitap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.baitap2.baithuchanh.CalculatorScreen
import com.example.baitap2.baithuchanh.StudentListScreen
import com.example.baitap2.baithuchanh.GradeClassificationScreen
import com.example.baitap2.baithuchanh.ClassScoreBoardScreen
import com.example.baitap2.ui.theme.Baitap2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitap2Theme {
                ClassScoreBoardScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Baitap2Theme {
        ClassScoreBoardScreen()
    }
}