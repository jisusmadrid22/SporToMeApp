@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.searcher.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.labelTextFieldByStep
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont


@Composable
fun SearcherField(
    selectedSwitch: Boolean,
    searcherClick: (String)-> Unit
) {

    var textValue by remember {
        mutableStateOf("")
    }
    val textLabel = if (!selectedSwitch) AppResource.getString(R.string.searchCompetition) else AppResource.getString(R.string.searchTeam)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(0.85f),
            value = textValue,
            onValueChange = {
                textValue = it
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                fontFamily = QuickSandFont
            ),
            placeholder = {
                Text(
                    text = textLabel,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    )
                )
            },
            shape = RoundedCornerShape(18.dp),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Card(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            backgroundColor = Color.White,
            shape = CircleShape,
            elevation = 0.dp,
            onClick = {
                searcherClick(textValue)
            }
        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}