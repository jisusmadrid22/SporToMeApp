@file:OptIn(ExperimentalFoundationApi::class)

package com.yzdev.sportome.common.composable.listDesign.animationList

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.common.Country
import com.yzdev.sportome.common.Sport
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemShimmer
import com.yzdev.sportome.common.composable.itemListDesign.itemTutorial.ItemTutorialDesign
import com.yzdev.sportome.common.getCountryBySport
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.presentation.screens.tutorial.CountryState

@Composable
fun ListCountry(
    listCountry: CountryState,
    filteredList: List<LocalCountry>,
    showPadding: Boolean = false,
    onSuccess: ()-> Unit,
    clickItem: (LocalCountry)-> Unit
) {
    val modifierCustom = if (showPadding) Modifier.fillMaxWidth().fillMaxHeight(0.9f) else Modifier.fillMaxWidth()

    Box(modifier = modifierCustom) {

        when{
            listCountry.isLoading -> {
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
            listCountry.error.isNotEmpty() -> {
                Log.e("countries", "error ${listCountry.error}")
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
                Log.e("countries", "success -> ${listCountry.info}")
                onSuccess()
                Column {
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
}