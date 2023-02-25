package com.yzdev.sportome.domain.model

import com.yzdev.sportome.data.remote.dto.match.predictions.*


data class PredictionsResponse(
    val comparison: Comparison?,
    val h2h: List<H2h?>?,
    val league: League?,
    val predictions: Predictions?,
)

fun PredictionsResponseDto.toListPredictionResponse(): List<PredictionsResponse?>{
    return if (this.response != null){
        this.response.map {
            if (it != null){
                PredictionsResponse(
                    comparison = it.comparison,
                    h2h = it.h2h,
                    league = it.league,
                    predictions = it.predictions
                )
            }else{
                null
            }
        }
    }else{
        emptyList<PredictionsResponse>()
    }
}
