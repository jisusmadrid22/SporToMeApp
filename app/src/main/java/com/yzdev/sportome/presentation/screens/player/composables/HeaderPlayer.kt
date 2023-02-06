@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun HeaderPlayer(
    listSeasons: List<String> = listOf("2000-2001", "2012-2013", "2013-2014", "2015-2016", "2016-2017", "2017-2018")
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listSeasons.last())}
    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp.dp

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height * 0.3f)
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Text(
                text = "Neymar\nda silva santos Junior",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Brasil",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.White.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Delantero",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.White.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(end = 8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
                onClick = {
                    expanded = true
                }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedText,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.White.copy(alpha = 0.5f)
                        ),
                        textAlign = TextAlign.End
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listSeasons.forEach {label->
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedText = label
                            }) {
                                Text(
                                    text = label,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = RobotoCondensed
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "",
                        tint = Color.White.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}