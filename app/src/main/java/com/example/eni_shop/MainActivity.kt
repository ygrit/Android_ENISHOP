package com.example.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.eni_shop.datastore.DataStoreManager
import com.example.eni_shop.nav.EniShopNavHost
import com.example.eni_shop.ui.theme.ENISHOPTheme

//logt -> tab
private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var isDarkModeEnabled by rememberSaveable { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                DataStoreManager.isDarkModeEnabled(this@MainActivity).collect {
                    isDarkModeEnabled = it
                }
            }

            ENISHOPTheme(darkTheme = isDarkModeEnabled) {
                EniShopApp()
            }
        }
    }
}


@Composable
fun EniShopApp(modifier: Modifier = Modifier) {
    val navHostController = rememberNavController()
    EniShopNavHost(navHostController = navHostController)
}
















