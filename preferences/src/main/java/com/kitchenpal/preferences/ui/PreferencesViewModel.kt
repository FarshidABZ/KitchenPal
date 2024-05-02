package com.kitchenpal.preferences.ui

import androidx.lifecycle.viewModelScope
import com.kitchenpal.core.common.base.mvibase.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class PreferencesViewModel @Inject constructor() :
    BaseViewModel<PreferencesEvent, ViewState, SingleEvent>() {
    private var currentPage = 1

    private val _viewState = MutableStateFlow(ViewState())
    override val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private fun updateState(newState: ViewState) {
        _viewState.value = newState
    }

    override fun processIntent(intent: PreferencesEvent) {
        when (intent) {
            is PreferencesEvent.SelectChip -> {
                updateState(updateChipSelection(intent.chipId, intent.sectionIndex))
            }

            PreferencesEvent.ActionButtonClicked -> {
                actionButtonClicked()
            }
        }
    }

    private fun updateChipSelection(chipId: String, sectionIndex: Int): ViewState {
        val currentState = viewState.value
        val sections = currentState.sections.toMutableList()
        val selectedSection = sections[sectionIndex]

        val updatedChips = selectedSection.chips.map { chip ->
            if (chip.id == chipId) {
                chip.copy(isSelected = !chip.isSelected)
            } else if (selectedSection.isMultiSelectAvailable) {
                chip
            } else {
                chip.copy(isSelected = false)
            }
        }

        sections[sectionIndex] = selectedSection.copy(chips = updatedChips)
        val preferencesSelected = sections.any { it.chips.any { chip -> chip.isSelected } }
        return currentState.copy(sections = sections, preferencesSelected = preferencesSelected)
    }

    private fun actionButtonClicked() {
        if (currentPage == 2) {
            finishPreferences()
        } else {
            currentPage++
            getNextPage()
        }
    }

    private fun getNextPage() {
        _viewState.value = ViewState(
            getSecondPage(),
            actionButtonState = ActionButtonState.DONE,
            progressValue = 0.75f,
            preferencesPage = 2,
            preferencesSelected = false
        )
    }

    private fun finishPreferences() {
        _viewState.update {
            it.copy(loading = true)
        }

        _viewState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {
            delay(2000) // finish preferences
            _viewState.update {
                it.copy(loading = false)
            }
            sendSingleEvent(SingleEvent.PreferencesSelected)
        }
    }
}