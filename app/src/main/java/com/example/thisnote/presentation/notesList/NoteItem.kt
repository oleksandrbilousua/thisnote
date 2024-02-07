package com.example.thisnote.presentation.notesList

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thisnote.R
import com.example.thisnote.data.Note
import com.example.thisnote.ui.theme.ThisNoteTheme

@Composable
fun NoteItem(
    item: Note,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .glowingEffect(Color.White,12.dp,5.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = colorResource(id = R.color.input_background), shape = RectangleShape)
            .padding(12.dp)
            .clickable { onClick.invoke() }

    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.noteTitle,
            color = colorResource(id = R.color.text_title),
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = item.noteDate,
            color = colorResource(id = R.color.placeholder),
            style = MaterialTheme.typography.bodySmall
        )

    }
}


fun Modifier.glowingEffect(
    glowingColor: Color,
    cornersRadius: Dp = 0.dp,
    glowingRadius: Dp = 20.dp,):Modifier = composed {
    drawBehind {
        val canvasSize = size
        drawContext.canvas.nativeCanvas.apply {
            drawRoundRect(
                0f, // Left
                0f, // Top
                canvasSize.width, // Right
                canvasSize.height, // Bottom
                cornersRadius.toPx(), // Radius X
                cornersRadius.toPx(), // Radius Y
                Paint().apply {
                    color = Color.Transparent.toArgb()
                    isAntiAlias = true
                    setShadowLayer(
                        glowingRadius.toPx(),
                        0f, 0f,
                        glowingColor.copy(alpha = 0.85f).toArgb()
                    )
                }
            )
        }
    }
}


