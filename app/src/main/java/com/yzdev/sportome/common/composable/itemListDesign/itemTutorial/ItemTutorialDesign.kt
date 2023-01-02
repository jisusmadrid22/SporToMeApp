@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.common.composable.itemListDesign.itemTutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.yzdev.sportome.common.Country
import com.yzdev.sportome.common.League
import com.yzdev.sportome.common.Sport
import com.yzdev.sportome.common.Team
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun ItemTutorialDesign(
    item: Sport,
    index: Int,
    clickItem: (Sport)-> Unit
) {

    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 18.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 18.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        onClick = {
            clickItem(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemTutorialDesign(
    item: LocalCountry,
    index: Int,
    clickItem: (LocalCountry)-> Unit
) {

    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 18.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 18.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        onClick = {
            clickItem(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemTutorialDesign(
    item: LocalCompetition,
    index: Int,
    clickItem: (LocalCompetition)-> Unit
) {

    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 18.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 18.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        onClick = {
            clickItem(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemTutorialDesign(
    item: Team,
    index: Int,
    clickItem: (Team)-> Unit
) {

    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 18.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 18.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        onClick = {
            clickItem(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(gray)
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ItemShimmer(
    item: Void?,
    index: Int
) {

    val modifierCustom = if ((index % 2) == 0){
        Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, top = 18.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 18.dp)
    }

    Card(
        modifier = modifierCustom,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .padding(12.dp)
        ) {
            AnimatedShimmerTwoLines()
        }
    }
}