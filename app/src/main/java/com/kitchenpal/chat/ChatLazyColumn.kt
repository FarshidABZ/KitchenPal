package com.kitchenpal.chat

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kitchenpal.ui.theme.KitchenPalTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatLazyColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    items: List<ChatListViewType>,
    onItemClickListener: ((ChatListViewType?) -> Unit)? = null
) {
    val listState = rememberLazyListState()

    LazyColumn(modifier = modifier, state = listState, verticalArrangement = verticalArrangement) {
        itemsIndexed(items) { index, item ->
            Box(
                modifier = Modifier
                    .padding(bottom = KitchenPalTheme.dimens.spaceXLarge)
                    .animateItemPlacement(
                        tween(300, -100)
                    )
            ) {
                when (item) {
                    is ChatListViewType.IncomingTextMessage ->
                        IncomingTextMessageView(text = item.message, onItemClickListener)

                    is ChatListViewType.IncomingImage -> CircularDrawableImage(
                        drawableId = item.drawableId!!,
                        48.dp,
                        onItemClickListener
                    )

                    is ChatListViewType.IncomingInteractionMessage -> TODO()
                    is ChatListViewType.OutgoingInteractionMessage -> TODO()
                    is ChatListViewType.OutgoingTextMessage -> TODO()
                }
            }
        }
    }
}

@Composable
fun CircularDrawableImage(
    drawableId: Int,
    imageSize: Dp = 64.dp,
    onItemClickListener: ((ChatListViewType?) -> Unit)? = null
) {
    val modifier = remember(onItemClickListener) {
        val clickableModifier = if (onItemClickListener != null) {
            Modifier.clickable {
                onItemClickListener(null)
            }
        } else {
            Modifier
        }

        Modifier
            .size(imageSize)
            .clip(CircleShape)
            .graphicsLayer(
                alpha = 1f,
                shape = CircleShape
            )
            .then(clickableModifier)
    }

    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun IncomingTextMessageView(
    text: String,
    onItemClickListener: ((ChatListViewType?) -> Unit)? = null
) {
    val shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = 16.dp,
        bottomStart = 4.dp
    )
    val padding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)

    val colors = KitchenPalTheme.colors

    val modifier = remember(onItemClickListener) {
        val clickableModifier = if (onItemClickListener != null) {
            Modifier.clickable {
                onItemClickListener(ChatListViewType.IncomingTextMessage(text))
            }
        } else {
            Modifier
        }

        Modifier
            .border(BorderStroke(1.dp, colors.inverseOnSurface), shape = shape)
            .clip(shape = shape)
            .then(clickableModifier)
            .padding(padding)
    }

    Text(text = text, modifier = modifier)
}