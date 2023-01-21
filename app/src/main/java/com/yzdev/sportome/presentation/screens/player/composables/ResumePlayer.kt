@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.R

@Composable
fun ResumePlayer(

) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        item {
            ResumePlayerTeam()
        }
    }
}

@Composable
private fun ResumePlayerTeam(

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        onClick = {

        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.25f), contentAlignment = Alignment.Center) {
                Box(modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(gray))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Paris Saint Germany",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = QuickSandFont,
                            color = Color.Black
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {

                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
                        contentDescription = ""
                    )
                }

                Text(
                    text = "Pais: Francia",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    )
                )

                Text(
                    text = "Inicio: DD/MM/YYYY",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.information),
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light, fontFamily = QuickSandFont, fontSize = 8.sp),
                        textAlign = TextAlign.End
                    )
                    Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "", tint = Color.Black.copy(alpha = 0.25f))
                }
            }
        }
    }
}