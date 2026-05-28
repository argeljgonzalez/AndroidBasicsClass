package com.example.androidbasicsclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androidbasicsclass.ui.login.LoginView
import com.example.androidbasicsclass.ui.navigation.AppNavigation
import com.example.androidbasicsclass.ui.theme.AndroidBasicsClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidBasicsClassTheme {
                var showApp by remember { mutableStateOf(false) }

                if (showApp) {
                    AppNavigation()
                } else {
                    LoginView(
                        onLoginClick = {
                            showApp = true
                        }
                    )
                }
            }
        }
    }
}