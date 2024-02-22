package com.example.testbin.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.testbin.R
import com.example.testbin.model.Ukhane
import com.example.testbin.viewmodel.UkhanaViewModel
import com.example.testbin.viewmodel.UkhaneListViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun UkhanaScreen(ukhanaId : Int){
    val coroutineScope = rememberCoroutineScope()
    val ukhaneViewModel : UkhanaViewModel = hiltViewModel()
    val ukhanaData : State<Ukhane?> = ukhaneViewModel.ukhanaToDisplay.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(15.dp)
                .paint(
                    painter = painterResource(id = R.drawable.frame),
                    contentScale = ContentScale.FillBounds,

                    ),
            contentAlignment = Alignment.Center
        ) {

            ukhaneViewModel.ukhanaToDisplay.let {

                Text(
                    text = ukhanaData.value?.ukhana ?: "AAAAAAA",
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(0.dp, 20.dp),
                    style = MaterialTheme.typography.body1
                )
            }


        }

        Row() {
            Text(
                text = "Previous",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(0.dp, 20.dp)
                    .clickable {
                        coroutineScope.launch {
                            ukhaneViewModel.getPreviousUkhana(ukhaneViewModel.curUkhanaId)
                        }

                    },
                style = MaterialTheme.typography.body1
            )

            Text(
                text = "Next",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(0.dp, 20.dp)
                    .clickable {
                        coroutineScope.launch {
                            ukhaneViewModel.getNextUkhana(ukhaneViewModel.curUkhanaId)
                        }

                    },
                style = MaterialTheme.typography.body1
            )

        }
    }





}