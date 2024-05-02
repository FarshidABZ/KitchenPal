package com.kitchenpal.preferences.ui

import com.kitchenpal.core.common.base.mvibase.MviIntent
import com.kitchenpal.core.common.base.mvibase.MviSingleEvent
import com.kitchenpal.core.common.base.mvibase.MviViewState

sealed interface PreferencesEvent : MviIntent {
    data class SelectChip(val chipId: String, val sectionIndex: Int) : PreferencesEvent
    data object ActionButtonClicked : PreferencesEvent
}

internal sealed interface SingleEvent : MviSingleEvent {
    data object PreferencesSelected : SingleEvent
    data class Failed(val message: String) : SingleEvent
}

internal data class ViewState(
    val sections: List<PreferencesUiModel> = getFirstPage(),
    val progressValue: Float = 0.5f,
    val actionButtonState: ActionButtonState = ActionButtonState.NEXT,
    val preferencesPage: Int = 1,
    val preferencesSelected: Boolean = true,
    val loading: Boolean = false
) : MviViewState

internal enum class ActionButtonState {
    NEXT,
    DONE,
}