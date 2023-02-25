package com.yzdev.sportome.presentation.screens.detail_match.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.R
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun StatsLayout(
    stateDetail: DetailMatchState
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        when{
            stateDetail.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            stateDetail.error.isNotEmpty() -> {
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
                                text = stateDetail.error,
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
            else -> {
                if (stateDetail.info?.statistics != null){
                    if (stateDetail.info.statistics.isNotEmpty()){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                for (i in 0 until stateDetail.info.statistics.first().statistics.size){
                                    LinearProgressStat(
                                        valueParent = getParentValue(
                                            valueHome = stateDetail.info.statistics.first().statistics[i].value,
                                            valueAway = stateDetail.info.statistics.last().statistics[i].value
                                        ),
                                        valueHome = getValueToInt(stateDetail.info.statistics.first().statistics[i].value),
                                        nameState = stateDetail.info.statistics.first().statistics[i].type,
                                        isPercent = stateDetail.info.statistics.first().statistics[i].value?.contains("%") ?: false
                                    )
                                }

                                Spacer(modifier = Modifier.height(48.dp))
                            }
                        }
                    }else{
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
                                        text = stringResource(id = R.string.statsEmpty),
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
                }else{
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
                                    text = stringResource(id = R.string.erroGeneric),
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
}

@Composable
fun LinearProgressStat(
    valueParent: Int,
    valueHome: Float,
    nameState: String,
    isPercent: Boolean
) {


    val percentHome = (((valueHome.toFloat() * 100) / valueParent.toFloat()) / 100)
    val percentAway = (1 - percentHome)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nameState,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(CircleShape)
                .background(gray)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(percentHome)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 128.dp, bottomStart = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(percentAway)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 128.dp, topEnd = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.White))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (!isPercent) valueHome.toInt().toString() else "${valueHome.toInt().toString()}%",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = if (!isPercent) (valueParent - valueHome).toInt().toString() else "${(valueParent - valueHome).toInt().toString()}%",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

    }
}

@Composable
fun LinearProgressStat(
    valueParent: Int,
    valueHome: Int,
    nameState: String,
    isPercent: Boolean
) {


    val percentHome = (((valueHome.toFloat() * 100) / valueParent.toFloat()) / 100)
    val percentAway = (1 - percentHome)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nameState,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(CircleShape)
                .background(gray)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (percentHome.isNaN()) 0f else percentHome)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 128.dp, bottomStart = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(if (percentAway.isNaN()) 0f else percentAway)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(bottomEnd = 128.dp, topEnd = 128.dp))
                            .background(MaterialTheme.colors.primary)
                    )
                }

            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.White))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (!isPercent) valueHome.toInt().toString() else "${valueHome.toInt().toString()}%",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = if (!isPercent) (valueParent - valueHome).toInt().toString() else "${(valueParent - valueHome).toInt().toString()}%",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

    }
}

fun getParentValue(valueHome: String?, valueAway: String?): Int{
    Log.e("value", "value $valueAway $valueHome")
    var home = 0
    var away = 0

    home = if (((valueHome != null) and (valueHome != "null"))){
        if (valueHome!!.contains("%")){
            valueHome.dropLast(1).toInt()
        }else{
            val f = String.format("%.2f", valueHome.toFloat())
            f.dropLast(3).toInt()
        }
    }else{
        0
    }

    away = if (((valueAway != null) and (valueAway != "null"))){
        if (valueAway!!.contains("%")){
            valueAway.dropLast(1).toInt()
        }else{
            val f = String.format("%.2f", valueAway.toFloat())
            f.dropLast(3).toInt()
        }
    }else{
        0
    }

    return home + away
}

fun getValueToInt(valueHome: String?): Int{
    var home = 0

    home = if (((valueHome != null) and (valueHome != "null"))){
        if (valueHome!!.contains("%")){
            valueHome.dropLast(1).toInt()
        }else{
            val f = String.format("%.2f", valueHome.toFloat())
            f.dropLast(3).toInt()
        }
    }else{
        0
    }

    return home
}

fun getValueToFloat(valueHome: String?): Float{
    var home = 0f

    home = if (((valueHome != null) and (valueHome != "null"))){
        if (valueHome!!.contains("%")){
            valueHome.dropLast(1).toFloat()
        }else{
            val f = String.format("%.2f", valueHome.toFloat())
            f.dropLast(3).toFloat()
        }
    }else{
        0f
    }

    return home
}