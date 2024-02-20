package com.example.testbin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testbin.R
import com.example.testbin.viewmodel.UkhaneTypeViewModel


@Composable
fun UkhaneTypeScreen(onClick: (category : String) -> Unit) {
    val ukhaneTypeViewModel: UkhaneTypeViewModel = hiltViewModel()
    val ukhaneTypeListData: State<List<String>> = ukhaneTypeViewModel.ukhaneTypeList.collectAsState()
    Column {

        Box(
            modifier = Modifier
                .padding(5.dp,15.dp,5.dp,0.dp)
                .border(2.dp, Color.Black),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp,0.dp)

            ) {
                Image(painter = painterResource(id = R.mipmap.ic_cam), contentDescription = "img")

                Text(
                    text = "ABCDEFGHIJ KLMNOPQRST",
                    fontSize = 18.sp,
                    color = Color.Black,
                    style = TextStyle(),
                    modifier = Modifier.padding(5.dp)


                )
                Image(painter = painterResource(id = R.mipmap.ic_cam), contentDescription = "img")


            }

        }
        Box(
            modifier = Modifier
                .padding(5.dp,0.dp,5.dp,5.dp)
                .border(2.dp, Color.Black,),
        ) {
            LazyColumn {
                items(ukhaneTypeListData.value) { item -> ukhaneItem(ukhaneType = item, onClick)
                }

            }
        }
    }
}



@Composable
fun ukhaneItem(ukhaneType: String, onClick: (category: String) -> Unit){

    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .clickable { onClick(ukhaneType) }
        ) {
            Image(painter = painterResource(id = R.mipmap.ic_cam), contentDescription = "img")

            Text(
                text = ukhaneType,
                fontSize = 18.sp,
                color = Color.Black,
                maxLines = 1, overflow = TextOverflow.Ellipsis,
                style = TextStyle(

                ),
                modifier = Modifier.padding(5.dp)


            )


        }
        Divider(color = Color.Black, thickness = 1.dp)
    }
}