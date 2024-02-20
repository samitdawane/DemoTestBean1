package com.example.testbin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testbin.api.UkhaneApi
import com.example.testbin.screens.UkhanaScreen
import com.example.testbin.screens.UkhaneListScreen
import com.example.testbin.screens.UkhaneTypeScreen
import com.example.testbin.ui.theme.TestBinTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var ukhaneApi: UkhaneApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TestBinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    App()

                }
            }
        }
    }

}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "ukhaneType", ){

        composable("ukhaneType"){
            UkhaneTypeScreen(){
                navController.navigate("ukhaneList/${it}")
            }
        }


        composable("ukhaneList/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )){
            var category = it.arguments!!.getString("category")
            UkhaneListScreen(category!!){
                navController.navigate("ukhana/${it}")
            }
        }

        composable("ukhana/{ukhanaId}", arguments = listOf(
            navArgument("ukhanaId"){
                type = NavType.IntType
            }
        )){
            var ukhanaId = it.arguments!!.getInt("ukhanaId")
            UkhanaScreen(ukhanaId)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun animationExUI() {
    var animType by remember {
        mutableStateOf("TrL")//TrL, TrR,bounce,fadeIn,FadeOut
    }
    var count by remember {
        mutableStateOf(0)//TrL, TrR,bounce,fadeIn,FadeOut
    }
    AnimatedContent(
        targetState = count,
        transitionSpec = if(animType.equals("TrL")) { {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 500)
            ) with
                    slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500)
                    )
        } }else if(animType.equals("TrR")) { {
        slideIntoContainer(
            towards = AnimatedContentScope.SlideDirection.Right,
            animationSpec = tween(durationMillis = 500)
        ) with
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500)
                )
    }} else { {
            scaleIn(animationSpec = tween(durationMillis = 800)) with
                    ExitTransition.None
        }} ,
        contentAlignment = Alignment.Center
    ) { targetCount ->


        Text(modifier = Modifier.fillMaxWidth(),
            text = "$targetCount",
            fontSize = 36.sp,
            style = TextStyle(textAlign = TextAlign.Center)

        )


    }

    Spacer(modifier = Modifier.height(height = 12.dp))

    Button(onClick = { animType="TrL"
    count++
    }) {
        Text(text = "Right to Left")
    }

    Button(onClick = { animType="TrR"
    count++
    }) {
        Text(text = "Left to Right")
    }

    Button(onClick = { animType="scale"
        count++
    }) {
        Text(text = "Scale")
    }
}




