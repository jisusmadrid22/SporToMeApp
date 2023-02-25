package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed

@Composable
fun InfoPlayerByLeague(
    infoLeague: InfoPlayerResponse.Response.Statistic?
) {
    val paddingHeight = 4.dp

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 14.dp)
        ) {
            InfoPlayer(
                titleInfo = stringResource(id = R.string.gamesPlayed),
                valueInfo = "${infoLeague?.games?.appearences ?: 0} ${stringResource(id = R.string.matchsTitle)}"
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.minutesPlayed),
                valueInfo = "${infoLeague?.games?.minutes ?: 0} ${stringResource(id = R.string.minAbrev)}"
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.positionGame),
                valueInfo = infoLeague?.games?.position ?: ""
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.captain),
                valueInfo = if (infoLeague?.games?.captain == true) stringResource(id = R.string.yesTitle) else stringResource(id = R.string.noTitle)
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            RatingPlayer(
                titleInfo = stringResource(id = R.string.rating),
                valueInfo = infoLeague?.games?.rating ?: ""
            )
        }
    }
}

@Composable
fun RatingPlayer(
    titleInfo: String,
    valueInfo: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = titleInfo,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black.copy(alpha = 0.25f)
            )
        )

        Text(
            text = valueInfo,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = RobotoCondensed,
                color = MaterialTheme.colors.secondary
            )
        )
    }
}