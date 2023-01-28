package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.domain.model.DetailMatchResponse
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchState
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.R
import com.yzdev.sportome.common.getFirstAndLastName
import com.yzdev.sportome.presentation.ui.theme.greenSuccess
import com.yzdev.sportome.presentation.ui.theme.redMain
import com.yzdev.sportome.presentation.ui.theme.yellowCard

@Composable
fun EventLayout(
    stateDetail: DetailMatchState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 6.dp, start = 24.dp, end = 24.dp, bottom = 48.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = Color.Black.copy(alpha = 0.25f),
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color.White)
    ) {
        EventBackground()

        when{
            stateDetail.isLoading ->{
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            stateDetail.error.isNotEmpty() ->{
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
            else ->{
                if (stateDetail.info != null){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            items(stateDetail.info.events.reversed()){event->
                                EventItem(
                                    event = event,
                                    isHome = event.team.name == stateDetail.info.teams.home.name
                                )
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
                            Text(text = "Error")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventItem(
    event: DetailMatchResponse.Event,
    isHome: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isHome){
            Row(modifier = Modifier.weight(2.25f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(end = 6.dp)
                ) {
                    HomeEventInfo(event = event)
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = ImageVector.vectorResource(
                            id = getIconPath(
                                type = event.type,
                                detail = event.detail
                            )
                        ),
                        contentDescription = "",
                        tint = getIconColor(type = event.type, detail = event.detail)
                    )
                }
            }

        }else{

            Row(
                modifier = Modifier.weight(2.25f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End
            ) {

            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 0.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.Black.copy(alpha = 0.25f), shape = CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "${event.time.elapsed}${if (event.time.extra != null) "+${event.time.extra}" else ""}'",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp,
                        fontFamily = QuickSandFont
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (!isHome){
            Row(
                modifier = Modifier
                    .weight(2.25f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {

                Box(
                    modifier = Modifier.fillMaxWidth(0.2f).padding(start = 4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = ImageVector.vectorResource(
                            id = getIconPath(
                                type = event.type,
                                detail = event.detail
                            )
                        ),
                        contentDescription = "",
                        tint = getIconColor(type = event.type, detail = event.detail)
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth().padding(start = 6.dp)
                ) {
                    AwayEventInfo(event = event)
                }
            }

        }else{
            Row(
                modifier = Modifier
                    .weight(2.25f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
            ) {

            }
        }
    }
}

@Composable
fun HomeEventInfo(
    event: DetailMatchResponse.Event,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        when(event.type.lowercase()){
            "goal"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.End
                )
            }
            "subst"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = event.assist.name ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = greenSuccess.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp,
                        fontFamily = QuickSandFont,
                        color = redMain.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.End
                )
            }
            "card"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.End
                )
            }
            "var"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.End
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.End
                )
            }
            else->{

            }
        }
    }
}

@Composable
fun AwayEventInfo(
    event: DetailMatchResponse.Event,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        when(event.type.lowercase()){
            "goal"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Start
                )
            }
            "subst"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = event.assist.name ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = greenSuccess.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.sp,
                        fontFamily = QuickSandFont,
                        color = redMain.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Start
                )
            }
            "card"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Start
                )
            }
            "var"->{
                Text(
                    text = getTitleEvent(type = event.type, detail = event.detail),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = event.player.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        fontFamily = QuickSandFont,
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Start
                )
            }
            else->{

            }
        }
    }
}


private fun setList(): List<String>{
    return listOf(
        "01",
        "10",
        "12",
        "15",
        "23",
        "32",
        "44",
    )
}

private fun getTitleEvent(type: String, detail: String): String{
    return when(type.lowercase()){
        "goal"->{
            AppResource.getString(R.string.goalTitle)
        }
        "subst"->{
            "${AppResource.getString(R.string.subsTitle)} N°${getSubstitutionNumber(detail)}"
        }
        "card"->{
            getCard(detail)
        }
        "var"->{
            getVar(detail)
        }
        else->{
            type
        }
    }
}

private fun getVar(detail: String): String{
    return when(detail.lowercase()){
        "penalty confirmed"->{
            AppResource.getString(R.string.penaltyConfirmed)
        }
        "penalty cancelled"->{
            AppResource.getString(R.string.penaltyCancelled)
        }
        "goal cancelled"->{
            AppResource.getString(R.string.goalCancelled)
        }
        else->{
            detail
        }
    }
}

private fun getCard(detail: String): String{
    return when{
        detail.lowercase().contains("yellow")->{
            if (detail.lowercase().contains("second")){
                AppResource.getString(R.string.secondCardYellow)
            }else{
                AppResource.getString(R.string.yellowCard)
            }
        }
        detail.lowercase().contains("red")->{
            AppResource.getString(R.string.redCard)
        }
        else->{
            ""
        }
    }
}

private fun getSubstitutionNumber(detail: String): Int{
    return when{
        detail.lowercase().contains("1")->{
            1
        }
        detail.lowercase().contains("2")->{
            2
        }
        detail.lowercase().contains("3")->{
            3
        }
        detail.lowercase().contains("4")-> {
            4
        }
        detail.lowercase().contains("5")->{
            5
        }
        detail.lowercase().contains("6")->{
            6
        }
        else->{
            0
        }
    }
}

private fun getIconPath(type: String, detail: String): Int{
    return when(type.lowercase()){
        "goal"->{
            R.drawable.ic_football
        }
        "subst"->{
            R.drawable.ic_swap_subs
        }
        "card"->{
           return when{
               detail.lowercase().contains("yellow")->{
                    if (detail.lowercase().contains("second")){
                        R.drawable.ic_two_card
                    }else{
                        R.drawable.ic_one_card
                    }
               }
               detail.lowercase().contains("red")->{
                    R.drawable.ic_one_card
               }
               else-> R.drawable.ic_one_card
            }
        }
        "var"->{
            R.drawable.ic_var
        }
        else->{
            R.drawable.ic_football
        }
    }
}
@Composable
private fun getIconColor(type: String, detail: String): Color{
    return when(type.lowercase()){
        "goal"->{
            MaterialTheme.colors.primary
        }
        "subst"->{
            MaterialTheme.colors.primary
        }
        "card"->{
            return when{
                detail.lowercase().contains("yellow")->{
                    yellowCard
                }
                detail.lowercase().contains("red")->{
                    redMain
                }
                else-> yellowCard
            }
        }
        "var"->{
            MaterialTheme.colors.primary
        }
        else->{
            MaterialTheme.colors.primary
        }
    }
}