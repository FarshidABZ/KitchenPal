package com.kitchenpal.preferences.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kitchenpal.core.designsystem.component.button.FilledButton
import com.kitchenpal.core.designsystem.component.button.OutlineButton
import com.kitchenpal.core.designsystem.theme.Dimens
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import com.kitchenpal.preferences.R

@Composable
internal fun PreferencesRoute(
    preferencesDone: () -> Unit,
    skipClicked: () -> Unit,
    seeAllClicked: (String) -> Unit,
    viewModel: PreferencesViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    PreferencesScreen(
        uiState,
        viewModel::processIntent,
        preferencesDone,
        skipClicked,
        seeAllClicked
    )
}

@Composable
private fun PreferencesScreen(
    viewState: ViewState,
    onAction: (PreferencesEvent) -> Unit,
    preferencesDone: () -> Unit,
    skipClicked: () -> Unit,
    seeAllClicked: (String) -> Unit
) {
    KitchenPalTheme {
        Scaffold(
            topBar = {
                PreferencesTopBar(viewState.progressValue, skipClicked)
            },
            content = { paddingValues ->
                PreferencesContent(paddingValues, viewState, onAction, seeAllClicked)
            })
    }
}

@Composable
private fun PreferencesContent(
    paddingValues: PaddingValues,
    viewState: ViewState,
    onAction: (PreferencesEvent) -> Unit,
    seeAllClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        viewState.sections.forEachIndexed { index, section ->
            PreferencesSection(index, section, onAction, seeAllClicked)
            if (index < viewState.sections.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(
                        top = Dimens.spaceXLarge,
                        start = Dimens.spaceXXLarge,
                        end = Dimens.spaceXXLarge,
                        bottom = Dimens.spaceXXLarge
                    )
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        PreferencesActionButton(viewState, onAction)
    }
}

@Composable
private fun PreferencesActionButton(viewState: ViewState, onAction: (PreferencesEvent) -> Unit) {
    when (viewState.actionButtonState.name) {
        ActionButtonState.NEXT.name -> {
            OutlineButton(
                text = stringResource(id = R.string.next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.spaceXLarge),
            ) {
                onAction(PreferencesEvent.ActionButtonClicked)
            }
        }

        ActionButtonState.DONE.name -> {
            FilledButton(
                text = stringResource(id = R.string.done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.spaceXLarge),
                isEnable = viewState.preferencesSelected,
            ) {
                onAction(PreferencesEvent.ActionButtonClicked)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesTopBar(progress: Float, onActionClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            LinearProgressIndicator(
                { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 46.dp),
                strokeCap = StrokeCap.Round
            )
        },
        actions = {
            Text(
                modifier = Modifier
                    .clickable { onActionClicked() }
                    .padding(16.dp),
                text = stringResource(id = R.string.done),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.background),
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PreferencesSection(
    sectionIndex: Int,
    section: PreferencesUiModel,
    onAction: (PreferencesEvent) -> Unit,
    seeAllClicked: (String) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = Dimens.spaceXLarge)) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, description, seeAll) = createRefs()
            Text(modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(seeAll.start)
                width = Dimension.fillToConstraints
            }, text = section.title, style = MaterialTheme.typography.titleMedium)

            Text(
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom, Dimens.spaceSmall)
                    start.linkTo(parent.start)
                    end.linkTo(seeAll.start)
                    width = Dimension.fillToConstraints
                },
                text = section.description,
                style = MaterialTheme.typography.bodySmall
            )
            section.seeAll?.let {
                Text(
                    modifier = Modifier
                        .constrainAs(seeAll) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .padding(
                            top = Dimens.spaceMedium,
                            bottom = Dimens.spaceMedium,
                            start = Dimens.spaceLarge
                        )
                        .clickable {
                            seeAllClicked(it.id)
                        },
                    text = it.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }


        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.spaceXXXLarge),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(
                (-4).dp
            )
        ) {
            section.chips.forEach {
                PreferenceChip(preference = it) { id ->
                    onAction(PreferencesEvent.SelectChip(id, sectionIndex))
                }
            }
        }
    }
}

@Composable
fun PreferenceChip(preference: Chip, onClick: (String) -> Unit) {
    Row {
        Spacer(modifier = Modifier.width(4.dp))
        FilterChip(
            label = {
                Text(
                    text = preference.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            shape = CircleShape,
            border = BorderStroke(
                1.dp,
                if (preference.isSelected) Color.Transparent else MaterialTheme.colorScheme.onSurfaceVariant
            ),
            selected = preference.isSelected,
            leadingIcon = {
                Image(
                    painter = painterResource(
                        id = if (preference.isSelected)
                            com.kitchenpal.core.designsystem.R.drawable.ic_chip_selected
                        else
                            com.kitchenpal.core.designsystem.R.drawable.ic_google
                    ), contentDescription = ""
                )
            },
            onClick = {

                onClick(preference.id)
            }
        )
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreferencesScreenPreview() {
    KitchenPalTheme {
        PreferencesScreen(ViewState(), {}, {}, {}, {})
    }
}