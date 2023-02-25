package com.yzdev.sportome.presentation.screens.menu.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun ItemMenu(
    item: List<Triple<Int, String, String?>>,
    selected: Boolean,
    navigate: (String)-> Unit
) {
    if (item.size == 1){
        ItemMenuIndividual(
            item = item.first(),
            selected = selected,
            navigate = {
                navigate(it)
            }
        )
    }else{

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemMenuIndividual(
    item: Triple<Int, String, String?>,
    selected: Boolean,
    navigate: (String)-> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = if (selected){
            BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary)
        }else{
            BorderStroke(width = 0.dp, color = Color.Transparent)
        },
        backgroundColor = if (selected) gray else Color.White,
        elevation = 0.dp,
        onClick = {
            navigate(item.third!!)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.height(16.dp),
                imageVector = ImageVector.vectorResource(id = item.first),
                contentDescription = "Menu icono",
                tint = Color.Black
            )

            Spacer(modifier = Modifier.padding(start = 36.dp))

            Text(
                text = item.second,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = RobotoCondensed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
        }
    }
}