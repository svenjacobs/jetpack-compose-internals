package dev.jorgecastillo.compose.app.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Speakers App") }) },
    ) { contentPadding ->
        val navController = rememberNavController()

        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController,
            startDestination = "speakers"
        ) {
            composable("speakers") {
                SpeakerFeed(
                    onSpeakerClick = { speaker ->
                        navController.navigate("speaker/${speaker.id}") {
                            popUpTo("speakers")
                        }
                    },
                )
            }

            composable(
                "speaker/{speakerId}",
                arguments = listOf(
                    navArgument("speakerId") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStackEntry ->
                val speakerId = requireNotNull(navBackStackEntry.arguments?.getString("speakerId"))

                SpeakerProfileScreen(speakerId)
            }
        }
    }
}
