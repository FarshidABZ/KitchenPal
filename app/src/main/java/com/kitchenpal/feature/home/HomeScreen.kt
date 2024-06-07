package com.kitchenpal.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import androidx.constraintlayout.compose.atMost
import androidx.hilt.navigation.compose.hiltViewModel
import com.kitchenpal.R
import com.kitchenpal.core.common.sharemodel.domain.CategorizedRecipes
import com.kitchenpal.core.common.sharemodel.domain.RecipeItemBO
import com.kitchenpal.core.designsystem.component.button.FilledButton
import com.kitchenpal.core.designsystem.theme.Dimens
import com.kitchenpal.core.designsystem.theme.Elevation
import com.kitchenpal.core.designsystem.theme.KitchenPalTheme
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun HomeScreenRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val recipeList by viewModel.recipes.collectAsState(initial = emptyList())
    viewModel.observeRecipes()

    HomeScreen(recipeList)
}

@Composable
fun HomeScreen(recipeList: List<CategorizedRecipes>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeBackground()
        Column {
            HomeFindRecipe(modifier = Modifier.fillMaxWidth())
            LazyColumn(contentPadding = PaddingValues(bottom = 80.dp)) {
                items(recipeList) {
                    RecipeItemList(categorizedRecipes = it)
                }
            }
        }
    }
}

@Composable
fun RecipeItemList(categorizedRecipes: CategorizedRecipes) {
    Text(
        modifier = Modifier.padding(
            start = Dimens.spaceXLarge,
            end = Dimens.spaceXLarge,
            top = Dimens.spaceLarge
        ),
        text = categorizedRecipes.category.value.orEmpty(),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(Dimens.spaceSmall),
        contentPadding = PaddingValues(Dimens.spaceLarge)
    ) {
        with(categorizedRecipes.recipes) {
            items(this) {
                val itemModifier = when (this@with.size) {
                    1 -> Modifier.fillParentMaxWidth()
                    else -> Modifier.width(250.dp)
                }
                RecipeItem(itemModifier, data = it) {
                }
            }
        }
    }
}

@Composable
fun HomeFindRecipe(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.padding(bottom = Dimens.spaceLarge)) {
        val (
            titleText,
            titleImage,
            messageText,
            arrowImage,
            findRecipeButton,
        ) = createRefs()

        Text(
            modifier = Modifier.constrainAs(titleText) {
                top.linkTo(parent.top, Dimens.spaceXXXLarge)
                start.linkTo(parent.start, margin = Dimens.spaceXLarge)
                width = Dimension.fillToConstraints
            },
            text = stringResource(R.string.home_welcome_message_title),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Image(
            modifier = Modifier
                .constrainAs(titleImage) {
                    bottom.linkTo(titleText.bottom)
                    start.linkTo(titleText.end, Dimens.spaceXXSmall)
                }
                .size(width = 15.dp, height = 31.dp),
            painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_ice_cream),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.constrainAs(messageText) {
                top.linkTo(titleText.bottom, Dimens.spaceXXSmall)
                start.linkTo(parent.start, margin = Dimens.spaceXLarge)
                end.linkTo(parent.end, Dimens.spaceXLarge)
                width = Dimension.fillToConstraints
            },
            text = stringResource(R.string.home_welcome_message_desc),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Image(
            modifier = Modifier
                .constrainAs(arrowImage) {
                    top.linkTo(titleText.bottom, Dimens.spaceXXXLarge)
                    end.linkTo(parent.end, Dimens.spaceMedium)
                    height = Dimension.fillToConstraints
                        .atMost(64.dp)
                        .atLeast(56.dp)
                }
                .rotate(4f),
            painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_here_arrow),
            contentDescription = ""
        )

        FilledButton(
            modifier = Modifier.constrainAs(findRecipeButton) {
                start.linkTo(parent.start, Dimens.spaceXLarge)
                end.linkTo(parent.end, Dimens.spaceXLarge)
                width = Dimension.fillToConstraints
                height = Dimension.value(48.dp)
                top.linkTo(arrowImage.bottom, Dimens.spaceMedium)
            },
            elevation = ButtonDefaults.filledTonalButtonElevation(
                defaultElevation = Elevation.Level_4,
                pressedElevation = Elevation.Level_2
            ),
            text = stringResource(R.string.find_recipe),
            leadingIcon = com.kitchenpal.core.designsystem.R.drawable.ic_chef_s_hat,
            backgroundColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
        }
    }
}

@Composable
fun RecipeItem(modifier: Modifier = Modifier, data: RecipeItemBO, onClick: () -> Unit) {
    Card(
        onClick = {
            onClick()
        },
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.Level_2),
        modifier = modifier.height(250.dp)
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dummy_card_back),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                )
            }

            Column(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(Dimens.spaceLarge)
            ) {
                RatingBar()

                Text(
                    text = data.name.orEmpty(),
                    modifier = Modifier
                        .padding(top = Dimens.spaceSmall)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = data.shortDescription.orEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = MaterialTheme.colorScheme.secondary,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_star_filled),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                tint = starsColor
            )
        }
        if (halfStar) {
            Icon(
                painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_star_filled),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = com.kitchenpal.core.designsystem.R.drawable.ic_star_outline),
                contentDescription = null,
                modifier = Modifier.size(10.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHome() {
    KitchenPalTheme {
        HomeScreen(recipeList = emptyList())
    }
}