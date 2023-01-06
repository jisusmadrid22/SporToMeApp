@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.yzdev.sportome.common.composable.listDesign.animationList

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yzdev.sportome.common.Sport
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemShimmer
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getAllSports
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.presentation.screens.tutorial.CountryState

@Composable
fun ListSports(
    listSport: List<Sport> = getAllSports(),
    clickItem: (Sport)-> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceBetween,
            cells = GridCells.Fixed(count = 2),
            content = {
                itemsIndexed(listSport){ index, item->
                    ItemTutorialDesign(
                        item = item,
                        index = index
                    ){
                        clickItem(item)
                    }
                }
            }
        )
    }
}