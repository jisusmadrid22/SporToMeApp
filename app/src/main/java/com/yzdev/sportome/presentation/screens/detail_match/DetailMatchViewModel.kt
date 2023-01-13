package com.yzdev.sportome.presentation.screens.detail_match

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.use_case.getMatchDetailRemote.GetMatchDetailRemoteUseCase
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMatchViewModel @Inject constructor(
    private val getMatchDetailRemoteUseCase: GetMatchDetailRemoteUseCase
): ViewModel() {

    private val _stateDetail = mutableStateOf(DetailMatchState())
    val stateListDetail: State<DetailMatchState> = _stateDetail

    /** get detail match by id*/
    suspend fun getDetailMatchByID(id: Long){
        delay(10000)
        getMatchDetailRemoteUseCase(id).onEach { result->
            when(result){
                is Resource.Error -> {
                    _stateDetail.value = DetailMatchState(error = result.message ?: AppResource.getString(R.string.erroGeneric))
                }
                is Resource.Loading -> {
                    _stateDetail.value = DetailMatchState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateDetail.value = DetailMatchState(info = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setErrorId(){
        _stateDetail.value = DetailMatchState(error = AppResource.getString(R.string.erroGeneric))
    }

}