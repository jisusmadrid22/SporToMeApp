package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.getFirstAndLastName
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchState
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun LineupLayout(
    stateDetail: DetailMatchState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(6.dp))

        LineupField()

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Manager",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = QuickSandFont,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Manager 1",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Manager 2",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lineups",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = QuickSandFont,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.5f), horizontalAlignment = Alignment.Start) {
                setLineup().forEach {

                    val names = getFirstAndLastName(it.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = QuickSandFont,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                setLineup().forEach {

                    val names = getFirstAndLastName(it.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = QuickSandFont,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Substitutes",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = QuickSandFont,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.5f), horizontalAlignment = Alignment.Start) {
                setLineup().forEach {

                    val names = getFirstAndLastName(it.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = QuickSandFont,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                setLineup().forEach {

                    val names = getFirstAndLastName(it.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = QuickSandFont,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

    }
}

private fun setLineup(): List<Lineup>{
    return listOf(
        Lineup(
            "Iker Casillas",
            1
        ),
        Lineup(
            "Pepe",
            4
        ),
        Lineup(
            "Di Maria",
            22
        ),
        Lineup(
            "Lionel Messi",
            10
        ),
        Lineup(
            "Cristiano Ronaldo",
            7
        ),
        Lineup(
            "Tony Kross",
            11
        ),
        Lineup(
            "Karim Benzema",
            9
        ),
        Lineup(
            "Iker Casillas",
            1
        ),
        Lineup(
            "Pepe",
            4
        ),
        Lineup(
            "Di Maria",
            22
        ),
        Lineup(
            "Lionel Messi",
            10
        ),
    )
}

data class Lineup(
    val name: String,
    val number: Int
)