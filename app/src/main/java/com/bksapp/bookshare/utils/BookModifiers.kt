package com.bksapp.bookshare.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.roundedCard(
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = Color.LightGray
): Modifier = this
    .padding(12.dp)
    .clip(RoundedCornerShape(cornerRadius))
    .background(backgroundColor)
    .shadow(4.dp,RoundedCornerShape(cornerRadius), clip = false)

fun Modifier.cardStyle(
    padding: Dp = 8.dp,
    color: Color? = null): Modifier = composed{
        val cardColor = color?: MaterialTheme.colorScheme.surface
    this
        .padding(padding)
        .shadow(2.dp)
        .background(cardColor, shape = MaterialTheme.shapes.medium)
        .padding(padding)

    }


private class BorderNode(var color: Color) : DrawModifierNode, Modifier.Node(){
    override fun ContentDrawScope.draw() {
        drawContent()
        drawRect(color = color, style = Stroke(width = 2.dp.toPx()))
    }
}


private data class BorderElement(val color: Color) : ModifierNodeElement<BorderNode>(){
    override fun create(): BorderNode= BorderNode(color)

    override fun update(node: BorderNode) {
      node.color = color
    }
}

fun Modifier.customBorer(color: Color): Modifier = this.then(BorderElement(color))



