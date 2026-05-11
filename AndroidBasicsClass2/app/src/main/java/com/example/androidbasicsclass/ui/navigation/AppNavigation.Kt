package com.example.androidbasicsclass.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.ComposableFunction1
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidbasicsclass.ui.firstpartialpdm1.homeFirstPartialPDM1.view.HomeFirstPartialPDM1View
import com.example.androidbasicsclass.ui.personalinformation.homePersonalInformation.view.HomePersonalInformationView
import com.example.androidbasicsclass.ui.secondpartialpdm1.homeSecondPartialPDM1.view.HomeSecondPartialPDM1View
import com.example.androidbasicsclass.ui.thirdpartialids2.homeThirdPartialIDS2.view.HomeThirdPartialIDS2View
import com.example.androidbasicsclass.ui.thirdpartialpdm1.homeThirdPartialPDM1.view.HomeThirdPartialPDM1View

sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    object ThirdPartialIDS2 : com.example.androidbasicsclass.ui.navigation.AppNavigation.AppRoute("third_partial_ids2", "IDS2 P3", Icons.Filled.School)
    object FirstPartialPDM1 : com.example.androidbasicsclass.ui.navigation.AppNavigation.AppRoute("first_partial_pdm1", "PDM1 P1", Icons.Filled.PhoneAndroid)
    object SecondPartialPDM1 : com.example.androidbasicsclass.ui.navigation.AppNavigation.AppRoute("second_partial_pdm1", "PDM1 P2", Icons.Outlined.PhoneAndroid)
    object ThirdPartialPDM1 : com.example.androidbasicsclass.ui.navigation.AppNavigation.AppRoute("third_partial_pdm1", "PDM1 P3", Icons.Filled.Smartphone)
    object PersonalInformation : com.example.androidbasicsclass.ui.navigation.AppNavigation.AppRoute("personal_information", "About Me", Icons.Filled.Person)
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
fun AppNavigation(composable: (Any?, () -> Unit?) -> Unit) {
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
            } as @Composable ComposableFunction1<RowScope, Unit> as @Composable ComposableFunction1<RowScope, Unit>
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

@Composable
fun NavigationBarItem(selected: Boolean, onClick: () -> Unit, icon: () -> Icon, label: () -> Text) {
    TODO("Not yet implemented")
}

fun Scaffold(bottomBar: Any, content: Any) {}
