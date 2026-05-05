package com.example.androidbasicsclass.ui.theme.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    object ThirdPartialIDS2 : AppRoute("third_partial_ids2", "IDS2 P3", Icons.Filled.School)
    object FirstPartialPDM1 : AppRoute("first_partial_pdm1", "PDM1 P1", Icons.Filled.PhoneAndroid)
    object SecondPartialPDM1 : AppRoute("second_partial_pdm1", "PDM1 P2", Icons.Outlined.PhoneAndroid)
    object ThirdPartialPDM1 : AppRoute("third_partial_pdm1", "PDM1 P3", Icons.Filled.Smartphone)
    object PersonalInformation : AppRoute("personal_information", "About Me", Icons.Filled.Person)
}

/** Ordered list of all tabs shown in the bottom bar. */
private val TABS = listOf(
    AppRoute.ThirdPartialIDS2,
    AppRoute.FirstPartialPDM1,
    AppRoute.SecondPartialPDM1,
    AppRoute.ThirdPartialPDM1,
    AppRoute.PersonalInformation
)

/**
 * Root composable that hosts the Scaffold with a bottom NavigationBar and
 * a NavHost wired to the five main screens of the app.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                TABS.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label, fontSize = 10.sp) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.ThirdPartialIDS2.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.ThirdPartialIDS2.route) { HomeThirdPartialIDS2View() }
            composable(AppRoute.FirstPartialPDM1.route) { HomeFirstPartialPDM1View() }
            composable(AppRoute.SecondPartialPDM1.route) { HomeSecondPartialPDM1View() }
            composable(AppRoute.ThirdPartialPDM1.route) { HomeThirdPartialPDM1View() }
            composable(AppRoute.PersonalInformation.route) { HomePersonalInformationView() }
        }
    }
}

fun Scaffold(bottomBar: Any, content: Any) {}
