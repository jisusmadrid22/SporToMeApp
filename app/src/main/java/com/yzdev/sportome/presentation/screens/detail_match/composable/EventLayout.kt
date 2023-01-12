package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun EventLayout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 6.dp, start = 24.dp, end = 24.dp)
            .border(width = 1.dp, color = Color.Black.copy(alpha = 0.25f))
            .background(Color.White)
    ) {
        EventBackground()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            items(setList().sortedDescending()){event->
                EventItem(
                    event = event,
                    isHome = false
                )
            }
        }
    }
}

@Composable
fun EventItem(
    event: String,
    isHome: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isHome){
            Row(modifier = Modifier.weight(1.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                Text(
                    modifier = Modifier.padding(end = 6.dp),
                    text = event,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    ),
                    textAlign = TextAlign.End
                )

                Icon(imageVector = Icons.Rounded.Home, contentDescription = "")
            }

        }else{

            Row(
                modifier = Modifier.weight(1.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End
            ) {

            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .border(width = 1.dp, color = Color.Black.copy(alpha = 0.25f))
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "${event}'",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = QuickSandFont
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (!isHome){
            Row(
                modifier = Modifier
                    .weight(1.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {
                Icon(imageVector = Icons.Rounded.Home, contentDescription = "")

                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = event,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    ),
                    textAlign = TextAlign.Start
                )
            }

        }else{
            Row(
                modifier = Modifier
                    .weight(1.5f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {

            }
        }
    }
}

private fun setList(): List<String>{
    return listOf(
        "01",
        "10",
        "12",
        "15",
        "23",
        "32",
        "44",
    )
}