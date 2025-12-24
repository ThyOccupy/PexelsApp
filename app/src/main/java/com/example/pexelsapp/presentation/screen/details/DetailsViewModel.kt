package com.example.pexelsapp.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecase.GetPhotoByIdUseCase
import com.example.pexelsapp.presentation.model.PhotoUiEntity
import com.example.pexelsapp.presentation.events.DetailsScreenEvent
import com.example.pexelsapp.presentation.model.toUiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getPhotoById: GetPhotoByIdUseCase
) : ViewModel() {

    private val _photoModel = MutableStateFlow<PhotoUiEntity?>(null)
    val photoModel: StateFlow<PhotoUiEntity?> = _photoModel

    private val _id = MutableStateFlow(0)
    val id: StateFlow<Int> = _id


    fun initData(id: Int) {
        viewModelScope.launch {
            getPhotoById.execute(id).collect { data ->
                _photoModel.value = data.toUiEntity()
            }
        }
    }

    private fun setId(newId: Int) {
        _id.value = newId
    }

    fun onEvent(event: DetailsScreenEvent){
        when(event) {
            is DetailsScreenEvent.onInitEvent -> {
                initData(event.id)
            }
        }
    }
}