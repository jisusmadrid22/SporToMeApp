package com.yzdev.sportome.presentation.screens.today_match

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.use_case.getAllMatchesWeek.GetAllMatchesWeekUseCase
import com.yzdev.sportome.presentation.screens.tutorial.CountryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayMatchViewModel @Inject constructor(
    private val getAllMatchesWeekUseCase: GetAllMatchesWeekUseCase
): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllMatchesWeekUseCase().onEach { result->
                when(result){
                    is Resource.Error -> {
                        Log.e("week", "error week ${result.message}")
                    }
                    is Resource.Loading -> {
                        Log.e("week", "loading")
                    }
                    is Resource.Success -> {
                        Log.e("week", "success ${result.data}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}