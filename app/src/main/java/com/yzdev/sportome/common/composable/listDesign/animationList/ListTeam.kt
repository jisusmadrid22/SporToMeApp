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
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.common.League
import com.yzdev.sportome.common.Team
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemShimmer
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getAllSports
import com.yzdev.sportome.common.getLeaguesByCountry
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.presentation.screens.tutorial.TeamState

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
                        Text(text = "Error")
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