package com.kitchenpal.feature.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.kitchenpal.core.designsystem.theme.colorHomeBackground


@Composable
fun HomeBackground() {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val curveHeight = (screenHeight.value / 2.2).dp
    val curveHeightValue = curveHeight.value
    val screenWidth = configuration.screenWidthDp.dp

    BezierCurve(
        modifier = Modifier
            .fillMaxWidth()
            .height(curveHeight)
            .rotate(180f),
        points = listOf(
            curveHeightValue - 200,
            curveHeightValue - 300,
            curveHeightValue - 180,
            curveHeightValue,
            curveHeightValue - 50
        ),
        minPoint = 0f,
        maxPoint = curveHeightValue,
        style = BezierCurveStyle.Fill(
            brush = Brush.horizontalGradient(
                listOf(
                    colorHomeBackground,
                    colorHomeBackground
                )
            ),
        ),
    )
}

@Composable
fun BezierCurve(
    modifier: Modifier,
    points: List<Float>,
    minPoint: Float? = null,
    maxPoint: Float? = null,
    style: BezierCurveStyle,
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    Canvas(
        modifier = modifier.onSizeChanged {
            size = it
        },
        onDraw = {
            if (size != IntSize.Zero && points.size > 1) {
                drawBezierCurve(
                    size = size,
                    points = points,
                    fixedMinPoint = minPoint,
                    fixedMaxPoint = maxPoint,
                    style = style,
                )
            }
        },
    )
}

private fun DrawScope.drawBezierCurve(
    size: IntSize,
    points: List<Float>,
    fixedMinPoint: Float? = null,
    fixedMaxPoint: Float? = null,
    style: BezierCurveStyle,
) {
    val maxPoint = fixedMaxPoint ?: points.max()
    val minPoint = fixedMinPoint ?: points.min()
    val total = maxPoint - minPoint
    val height = size.height
    val width = size.width
    val xSpacing = width / (points.size - 1F)
    var lastPoint: Offset? = null
    val path = Path()
    var firstPoint = Offset(0F, 0F)
    for (index in points.indices) {
        val x = index * xSpacing
        val y = height - height * ((points[index] - minPoint) / total)
        if (lastPoint != null) {
            buildCurveLine(path, lastPoint, Offset(x, y))
        }
        lastPoint = Offset(x, y)
        if (index == 0) {
            path.moveTo(x, y)
            firstPoint = Offset(x, y)
        }
    }
    fun closeWithBottomLine() {
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0F, height.toFloat())
        path.lineTo(firstPoint.x, firstPoint.y)
    }

    when (style) {
        is BezierCurveStyle.Fill -> {
            closeWithBottomLine()
            drawPath(
                path = path,
                style = Fill,
                brush = style.brush,
            )
        }

        is BezierCurveStyle.CurveStroke -> {
            drawPath(
                path = path,
                brush = style.brush,
                style = style.stroke,
            )
        }

        is BezierCurveStyle.StrokeAndFill -> {
            drawPath(
                path = path,
                brush = style.strokeBrush,
                style = style.stroke,
            )
            closeWithBottomLine()
            drawPath(
                path = path,
                brush = style.fillBrush,
                style = Fill,
            )
        }
    }
}

private fun buildCurveLine(path: Path, startPoint: Offset, endPoint: Offset) {
    val firstControlPoint = Offset(
        x = startPoint.x + (endPoint.x - startPoint.x) / 2F,
        y = startPoint.y,
    )
    val secondControlPoint = Offset(
        x = startPoint.x + (endPoint.x - startPoint.x) / 2F,
        y = endPoint.y,
    )
    path.cubicTo(
        x1 = firstControlPoint.x,
        y1 = firstControlPoint.y,
        x2 = secondControlPoint.x,
        y2 = secondControlPoint.y,
        x3 = endPoint.x,
        y3 = endPoint.y,
    )
}

sealed class BezierCurveStyle {

    class Fill(val brush: Brush) : BezierCurveStyle()

    class CurveStroke(
        val brush: Brush,
        val stroke: Stroke,
    ) : BezierCurveStyle()

    class StrokeAndFill(
        val fillBrush: Brush,
        val strokeBrush: Brush,
        val stroke: Stroke,
    ) : BezierCurveStyle()
}