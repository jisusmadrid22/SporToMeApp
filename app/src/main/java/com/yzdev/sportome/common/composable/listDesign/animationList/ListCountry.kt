@file:OptIn(ExperimentalFoundationApi::class)

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
import com.yzdev.sportome.common.Sport
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getCountryBySport

@Composable
fun ListCountry(
    sportId: Int,
    listCountry: List<Country>,
    clickItem: (Country)-> Unit
) {

    Box(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.SpaceBetween,
            cells = GridCells.Fixed(count = 2),
            content = {
                itemsIndexed(listCountry){ index, item->
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