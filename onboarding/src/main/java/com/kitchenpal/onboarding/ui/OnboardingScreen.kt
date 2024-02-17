package com.kitchenpal.onboarding.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.kitchenpal.core.designsystem.component.ListIndicator
import com.kitchenpal.core.designsystem.component.button.FilledButton
import com.kitchenpal.core.designsystem.component.button.OutlineButton
import com.kitchenpal.core.designsystem.theme.Dimens
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.onboarding.R
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
fun OnboardingRoute(
    onOnboardingDone: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    OnboardingScreen(
        uiState,
        viewModel.pageCount,
        viewModel::onNextButtonClicked,
        viewModel::onBackButtonClicked,
        onOnboardingDone,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    uiState: OnboardingUIModel,
    onboardingPageCount: Int,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onOnboardingDone: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    KitchenPalTheme {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = {}, actions = {
                Text(
                    modifier = Modifier
                        .clickable { onOnboardingDone() }
                        .padding(16.dp),
                    text = if (uiState.navigationTextId == 0) "" else stringResource(id = uiState.navigationTextId),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
                navigationIcon = {
                    if (uiState.navigationIconId != 0) {
                        Image(
                            modifier = Modifier
                                .clickable { onBackClicked() }
                                .size(40.dp)
                                .padding(Dimens.spaceSmall),
                            painter = painterResource(id = uiState.navigationIconId),
                            contentDescription = ""
                        )
                    }
                }
            )
        }) { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val (pager, indicator, button) = createRefs()

                HorizontalPager(
                    modifier = Modifier.constrainAs(pager) {
                        top.linkTo(parent.top, margin = Dimens.spaceXXXLarge)
                        bottom.linkTo(indicator.top, margin = Dimens.spaceXXXLarge)
                        height = Dimension.fillToConstraints
                    },
                    state = pagerState,
                    userScrollEnabled = false,
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val (image, title, message) = createRefs()
                        with(uiState) {
                            val painter = rememberAsyncImagePainter(model = imageId)
                            val imageState = painter.state

                            val transition by animateFloatAsState(
                                targetValue = if (imageState is AsyncImagePainter.State.Success) 1f else 0f,
                                label = ""
                            )

                            Image(
                                painter = painter,
                                contentDescription = "custom transition based on painter state",
                                modifier = Modifier
                                    .scale(.8f + (.2f * transition))
                                    .alpha(transition)
                                    .graphicsLayer { rotationX = (1f - transition) * 5f }
                                    .alpha(min(1f, transition / .2f))
                                    .constrainAs(image) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(title.top, margin = Dimens.spaceSmall)
                                        height = Dimension.fillToConstraints
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            Text(
                                text = stringResource(id = titleId),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.constrainAs(title) {
                                    top.linkTo(image.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(message.top, margin = Dimens.spaceSmall)
                                    width = Dimension.fillToConstraints
                                }
                            )

                            Text(
                                modifier = Modifier
                                    .padding(horizontal = Dimens.spaceXXXXLarge)
                                    .constrainAs(message) {
                                        top.linkTo(title.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                        width = Dimension.fillToConstraints
                                    },
                                text = stringResource(id = messageId),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                ListIndicator(
                    modifier = Modifier.constrainAs(indicator) {
                        bottom.linkTo(button.top, margin = 36.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    count = onboardingPageCount,
                    size = 8.dp,
                    spacer = 8.dp,
                    selectedIndex = uiState.currentPageIndex
                )

                if (uiState.currentPageIndex == onboardingPageCount - 1) {
                    FilledButton(modifier = Modifier
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom, margin = Dimens.spaceXXXXLarge)
                        }
                        .padding(horizontal = Dimens.spaceXLarge)
                        .fillMaxWidth(),
                        text = stringResource(id = R.string.lets_go)) {
                        scope.launch {
                            onOnboardingDone()
                        }
                    }
                } else {
                    OutlineButton(
                        modifier = Modifier
                            .constrainAs(button) {
                                bottom.linkTo(parent.bottom, margin = Dimens.spaceXXXXLarge)
                            }
                            .padding(horizontal = Dimens.spaceXLarge)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.next)
                    ) {
                        scope.launch {
                            onNextClicked()
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    name = "phone",
    device = Devices.PIXEL_4,
    backgroundColor = 0xFFFFFFFF,
    showBackground = true
)
@Composable
private fun Onboarding() {
    KitchenPalTheme {
        val state = OnboardingUIModel(
            1,
            R.string.onboarding_title_1,
            R.string.onboarding_message_1,
            R.drawable.onboarding_1,
            com.kitchenpal.core.designsystem.R.drawable.ic_arrow_left,
            R.string.onboarding_navigation_action_text
        )

        OnboardingScreen(state, 3, {}, {}, {})
    }
}


