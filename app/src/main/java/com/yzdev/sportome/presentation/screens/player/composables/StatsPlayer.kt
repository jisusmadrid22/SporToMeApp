@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun StatsPlayer(

) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.SpaceBetween,
        columns = GridCells.Fixed(count = 2),
        content = {
            item(
                span = { 
                    GridItemSpan(2) 
                }
            ) { 
                Column(modifier = Modifier.fillMaxWidth()) {

                    SelectorLeague(listLeague = listOf("League 1", "League 2", "League 3"))

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    InfoPlayerByLeague()

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            itemsIndexed(listStats()){i, stat->
                ItemStatPlayer(index = i, item = stat)
            }
        }
    )
}

private fun listStats(): List<ListStats>{
    return listOf(
        ListStats(
            "Substitutes",
            listOf(
                "In: 0",
                "Out: 10"
            )
        ),
        ListStats(
            "Shoot",
            listOf(
                "On: 20",
                "Off: 10",
                "Total: 30"
            )
        ),
        ListStats(
            "Substitutes",
            listOf(
                "In: 0",
                "Out: 10"
            )
        ),
        ListStats(
            "Shoot",
            listOf(
                "On: 20",
                "Off: 10",
                "Total: 30"
            )
        ),
        ListStats(
            "Substitutes",
            listOf(
                "In: 0",
                "Out: 10"
            )
        ),
        ListStats(
            "Shoot",
            listOf(
                "On: 20",
                "Off: 10",
                "Total: 30"
            )
        ),
        ListStats(
            "Substitutes",
            listOf(
                "In: 0",
                "Out: 10"
            )
        ),
        ListStats(
            "Shoot",
            listOf(
                "On: 20",
                "Off: 10",
                "Total: 30"
            )
        )
    )
}

data class ListStats(
    val name: String,
    val stats: List<String>
)