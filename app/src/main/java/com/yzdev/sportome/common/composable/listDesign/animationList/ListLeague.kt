@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.yzdev.sportome.common.composable.listDesign.animationList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yzdev.sportome.common.Country
import com.yzdev.sportome.common.League
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getCountryBySport
import com.yzdev.sportome.common.getLeaguesByCountry

@Composable
fun ListLeague(
    listLeague: List<League>,
    clickItem: (League)-> Unit
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceBetween,
            cells = GridCells.Fixed(count = 2),
            content = {
                itemsIndexed(listLeague){ index, item->
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