@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun SelectorLeagueTeam(
    listLeague: List<LocalCompetition>?,
    selectedLeague : LocalCompetition?,
    selectedLeagueOnChange: (LocalCompetition)-> Unit
) {

    var expandedCard by remember {
        mutableStateOf(false)
    }

    if (listLeague != null){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 0.dp,
            onClick = {
                expandedCard = true
            }
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(gray)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier
                        .fillMaxWidth(0.75f)
                    ) {
                        AutoResizedText(
                            text = selectedLeague?.name ?: "",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                fontFamily = RobotoCondensed,
                                color = Color.Black
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Icon(imageVector = if (!expandedCard) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp, contentDescription = "", tint = Color.Black)
            }

            DropdownMenu(
                expanded = expandedCard,
                onDismissRequest = { expandedCard = false }
            ) {
                listLeague.forEach { label->
                    DropdownMenuItem(
                        onClick = {
                            expandedCard = false
                            selectedLeagueOnChange(label)
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(gray)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(modifier = Modifier
                                .fillMaxWidth()
                            ) {
                                AutoResizedText(
                                    text = label.name ?: "",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}