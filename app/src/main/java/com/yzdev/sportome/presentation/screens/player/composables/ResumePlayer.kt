@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Constant
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.common.dateToUnix
import com.yzdev.sportome.common.unixToDateTimeSA
import com.yzdev.sportome.presentation.screens.player.CareerPlayerState
import com.yzdev.sportome.presentation.screens.player.PlayerResumeState
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun ResumePlayer(
    playerInfo: PlayerResumeState,
    navigateToInfoTeam: () -> Unit,
    careerPlayer: CareerPlayerState
) {
    when{
        playerInfo.isLoading ->{
            LoadingItemPlayer()
        }
        playerInfo.error.isNotEmpty() ->{
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
                        .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = playerInfo.error,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                fontFamily = RobotoCondensed,
                                color = blackLight
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        else ->{
            if (playerInfo.info != null){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    item {
                        ResumePlayerTeam(
                            playerInfo = playerInfo,
                            navigateToInfoTeam = {
                                navigateToInfoTeam()
                            },
                            careerPlayer = careerPlayer
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    item {
                        ResumeInfoPlayer(
                            playerInfo = playerInfo
                        )
                    }
                }
            }else {
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
                                text = stringResource(id = R.string.errorPlayerInfo),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = RobotoCondensed,
                                    color = blackLight
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingItemPlayer(

) {
    val height = LocalConfiguration.current.screenWidthDp.dp * 0.5f
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
            ) {
                AnimatedShimmerTwoLines()
            }
        }

        item{
            Spacer(modifier = Modifier.height(12.dp))
        }

        item{
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
            ) {
                AnimatedShimmerTwoLines()
            }
        }
    }
}

@Composable
private fun ResumeInfoPlayer(playerInfo: PlayerResumeState) {
    val paddingHeight = 4.dp


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 14.dp)
        ) {
            InfoPlayer(
                titleInfo = stringResource(id = R.string.dateBirth),
                valueInfo = unixToDateTimeSA(dateToUnix(playerInfo.info?.response?.first()?.player?.birth?.date ?: "")) ?: ""
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.city),
                valueInfo = playerInfo.info?.response?.first()?.player?.birth?.place ?: ""
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.age),
                valueInfo = (playerInfo.info?.response?.first()?.player?.age ?: 0).toString()
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.weight),
                valueInfo = (playerInfo.info?.response?.first()?.player?.weight ?: 0).toString()
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.height),
                valueInfo = (playerInfo.info?.response?.first()?.player?.height ?: 0).toString()
            )

            Spacer(modifier = Modifier.height(paddingHeight))

            InfoPlayer(
                titleInfo = stringResource(id = R.string.injure),
                valueInfo = if (playerInfo.info?.response?.first()?.player?.injured == true) AppResource.getString(R.string.yesTitle) else AppResource.getString(R.string.noTitle)
            )
        }
    }
}

@Composable
fun InfoPlayer(
    titleInfo: String,
    valueInfo: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
                fontSize = 10.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            )
        )
    }
}

@Composable
private fun ResumePlayerTeam(
    playerInfo: PlayerResumeState,
    navigateToInfoTeam: ()-> Unit,
    careerPlayer: CareerPlayerState
) {

    val teamInfo = playerInfo.info?.response?.first()?.statistics?.first { it?.league?.country != Constant.COUNTRY_WORLD }
    val teamCareer = if (!careerPlayer.info?.response.isNullOrEmpty()) if (!careerPlayer.info?.response?.first()?.transfers.isNullOrEmpty()) careerPlayer.info?.response?.first()?.transfers?.find { it.teams.inTeam.name == teamInfo?.team?.name } else null else null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
        enabled = (playerInfo.info != null),
        onClick = {
            navigateToInfoTeam()
        }
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.25f), contentAlignment = Alignment.Center) {
                Box(modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(gray))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = teamInfo?.team?.name ?: "",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {

                            },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
                        contentDescription = "",
                        tint = MaterialTheme.colors.secondary
                    )
                }

                Text(
                    text = stringResource(R.string.countryTitle) + ": ${teamInfo?.league?.country}",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    )
                )

                Text(
                    text = stringResource(R.string.dateInitTitle) + ": ${teamCareer?.let { dateToUnix(it.date) }?.let { unixToDateTimeSA(it) }}",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        fontFamily = RobotoCondensed,
                        color = Color.Black
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.information),
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light, fontFamily = RobotoCondensed, fontSize = 8.sp),
                        textAlign = TextAlign.End
                    )
                    Icon(modifier = Modifier.size(14.dp), imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription = "", tint = Color.Black.copy(alpha = 0.25f))
                }
            }
        }
    }
}