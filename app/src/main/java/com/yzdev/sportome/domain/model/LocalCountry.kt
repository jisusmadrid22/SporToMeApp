package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.remote.dto.countries.CountriesDtoResponse

@Entity
data class LocalCountry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timeRequest: Long,
    val code: String?,
    val flag: String?,
    val name: String
)

/** mapper*/
fun CountriesDtoResponse.toListLocalCountry(): List<LocalCountry>{
    /*return this.response.map {
        LocalCountry(
            timeRequest = timeToUnix(),
            code = it.code,
            flag = it.flag,
            name = it.name
        )
    }*/

    val listCountries = mutableListOf<LocalCountry>()

    this.response.forEach{
        if (it.code != null){
            listCountries.add(
                LocalCountry(
                    timeRequest = timeToUnix(),
                    code = it.code,
                    flag = it.flag,
                    name = it.name
                )
            )
        }
    }

    return listCountries
}
