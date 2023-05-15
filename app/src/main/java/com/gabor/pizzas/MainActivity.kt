package com.gabor.pizzas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gabor.pizzas.presentation.MainNavigation
import com.gabor.pizzas.ui.theme.PizzasTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

        setContent {
            PizzasTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainNavigation()
                }
            }
        }
    }


}