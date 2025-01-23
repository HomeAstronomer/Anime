package com.ang.anime.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ang.anime.data.remote.AnimeApiService
import com.ang.anime.ui.screens.dashboard.DashBoardCompose
import com.ang.anime.ui.screens.detail_screen.DashBoardDetailCompose

import kotlinx.serialization.Serializable


@Serializable
object SplashRoute
@Serializable
object DashBoardRoute


@Serializable
data class DashBoardDetailRoute(val animeId:Int=0)


@Composable
fun NavHostInitializer(navController: NavHostController) {
    NavHost(navController = navController, startDestination = DashBoardRoute) {

        composable<DashBoardRoute>(
            enterTransition = { slideInOutTransition(slideDirection = SlideDirection.RightToLeft).first() },
            exitTransition = { fadeOut(tween(300, 100)) },
            popExitTransition = { slideInOutTransition(slideDirection = SlideDirection.LeftToRight).second() },
            popEnterTransition = { fadeIn() },
        ) {
            DashBoardCompose( navController)
        }


        composable<DashBoardDetailRoute>(
            enterTransition = { slideInOutTransition(slideDirection = SlideDirection.RightToLeft).first() },
            exitTransition = { fadeOut(tween(300, 100)) },
            popExitTransition = { slideInOutTransition(slideDirection = SlideDirection.LeftToRight).second() },
            popEnterTransition = { fadeIn() },
        ) {backStackEntry->
            DashBoardDetailCompose(
                navController,
            )
        }
    }
}


fun slideInOutTransition(
    duration: Int = 400,
    slideDirection: SlideDirection
): Pair<() -> EnterTransition, () -> ExitTransition> {
    return when (slideDirection) {
        SlideDirection.LeftToRight -> {
            {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(duration,easing= FastOutSlowInEasing))
            } to {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(duration,easing= FastOutSlowInEasing))
            }
        }
        SlideDirection.RightToLeft -> {
            {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(duration,easing= FastOutSlowInEasing))
            } to {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(duration,easing= FastOutSlowInEasing))
            }
        }
    }
}

enum class SlideDirection {
    LeftToRight,
    RightToLeft
}