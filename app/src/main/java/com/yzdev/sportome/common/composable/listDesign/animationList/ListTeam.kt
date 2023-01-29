@file:OptIn(ExperimentalFoundationApi::class)

package com.yzdev.sportome.common.composable.listDesign.animationList

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.League
import com.yzdev.sportome.common.Team
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemShimmer
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getAllSports
import com.yzdev.sportome.common.getLeaguesByCountry
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.presentation.screens.tutorial.TeamState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed

@Composable
fun ListTeam(
    teamList: TeamState,
    filteredList: List<LocalTeam>,
    showPadding: Boolean = false,
    onSuccess: ()-> Unit,
    clickItem: (LocalTeam)-> Unit
) {
    val modifierCustom = if (showPadding) Modifier.fillMaxWidth().fillMaxHeight(0.9f) else Modifier.fillMaxWidth()

    Box(modifier = modifierCustom) {

        when{
            teamList.isLoading -> {
                Log.e("countries", "is Loading")
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    cells = GridCells.Fixed(count = 2),
                    content = {
                        itemsIndexed(List<String?>(20){null}){ index, item->
                            ItemShimmer(item = null, index = index)
                        }
                    }
                )
            }
            teamList.error.isNotEmpty() -> {
                Log.e("countries", "error ${teamList.error}")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = teamList.error,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.White
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            else -> {
                Log.e("countries", "success -> ${teamList.info}")
                onSuccess()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    cells = GridCells.Fixed(count = 2),
                    content = {
                        itemsIndexed(filteredList){ index, item->
                            ItemTutorialDesign(
                                item = item,
                                index = index
                            ){
                                clickItem(it)
                            }
                        }
                    }
                )
            }
        }
    }
}