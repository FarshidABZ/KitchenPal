package com.kitchenpal.onboarding.ui

import androidx.lifecycle.ViewModel
import com.kitchenpal.onboarding.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {
    val pageCount = 3
    private var currentPageIndex = 0

    private val _uiState = MutableStateFlow(
        OnboardingUIModel(
            0,
            R.string.onboarding_title_1,
            R.string.onboarding_message_1,
            R.drawable.onboarding_1,
            0, R.string.onboarding_navigation_action_text
        )
    )

    val uiState: StateFlow<OnboardingUIModel> get() = _uiState

    fun onNextButtonClicked() {
        currentPageIndex = _uiState.value.currentPageIndex + 1
        _uiState.update {
            it.copy(
                currentPageIndex = currentPageIndex,
                titleId = getOnboardingTitle(),
                messageId = getOnboardingMessage(),
                imageId = getOnboardingImage(),
                navigationIconId = getNavigationIconId(),
                navigationTextId = getNavigationTextId()
            )
        }
    }

    fun onBackButtonClicked() {
        currentPageIndex = _uiState.value.currentPageIndex - 1
        _uiState.update {
            it.copy(
                currentPageIndex = currentPageIndex,
                titleId = getOnboardingTitle(),
                messageId = getOnboardingMessage(),
                imageId = getOnboardingImage(),
                navigationIconId = getNavigationIconId(),
                navigationTextId = getNavigationTextId()
            )
        }
    }

    private fun getOnboardingTitle(): Int {
        return when (currentPageIndex) {
            0 -> R.string.onboarding_title_1
            1 -> R.string.onboarding_title_2
            else -> R.string.onboarding_title_3
        }
    }

    private fun getOnboardingMessage(): Int {
        return when (currentPageIndex) {
            0 -> R.string.onboarding_message_1
            1 -> R.string.onboarding_message_2
            else -> R.string.onboarding_message_3
        }
    }

    private fun getOnboardingImage(): Int {
        return when (currentPageIndex) {
            0 -> R.drawable.onboarding_1
            1 -> R.drawable.onboarding_2
            else -> R.drawable.onboarding_3
        }
    }

    private fun getNavigationIconId(): Int {
        return when (currentPageIndex) {
            0 -> 0
            1 -> com.core.designsystem.R.drawable.ic_arrow_left
            else -> com.core.designsystem.R.drawable.ic_arrow_left
        }
    }

    private fun getNavigationTextId(): Int {
        return when (currentPageIndex) {
            0, 1 -> R.string.onboarding_navigation_action_text
            else -> 0
        }
    }
}

data class OnboardingUIModel(
    val currentPageIndex: Int,
    val titleId: Int,
    val messageId: Int,
    val imageId: Int,
    val navigationIconId: Int,
    val navigationTextId: Int
)