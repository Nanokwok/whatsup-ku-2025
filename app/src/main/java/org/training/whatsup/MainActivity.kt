package org.training.whatsup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.training.whatsup.ui.theme.WhatsUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            WhatsUpTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
//                    Greeting(
//                        name = "Android"
//                        )
                    MainScreenWithBottomNavBar()
                }
            }
        }
        Toast.makeText(this, "Welcome to WhatsUP!!", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Click Me")
    }
}

@Composable
fun MainScreenWithBottomNavBar() {
    val navController = rememberNavController()
    Scaffold( bottomBar = {
        NavigationBar {
            WhatsUpNavItemInfo().getAllNavItems().forEachIndexed { index, itemInfo ->
                NavigationBarItem( selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = itemInfo.icon, contentDescription = itemInfo.label) },
                    label = { Text(text = itemInfo.label) } )
            }
        }
    } ) {paddingValues -> NavHost(navController,
        startDestination = DestinationScreen.NearMe.route,
        modifier = Modifier.padding(paddingValues)) {
            composable(route = DestinationScreen.NearMe.route) {
                NearMeScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhatsUpTheme {
        MainScreenWithBottomNavBar()
    }
}
