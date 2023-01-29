package com.yzdev.sportome.presentation.screens.menu.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.R
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun HeaderMenu(
    onChangeTheme: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 2.dp, topEnd = 48.dp, bottomStart = 2.dp, bottomEnd = 2.dp),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .height(38.dp)
                    .padding(start = 12.dp, top = 12.dp)
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_words), contentDescription = "", tint = grayBackground)
            }

            Spacer(modifier = Modifier.padding(top = 24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_sync), contentDescription = "", tint = grayBackground)
                }
            }
        }
    }
}