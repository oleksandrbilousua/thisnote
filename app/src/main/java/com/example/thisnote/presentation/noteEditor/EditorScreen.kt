package com.example.thisnote.presentation.noteEditor

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.thisnote.R
import com.example.thisnote.data.Note
import com.example.thisnote.presentation.notesList.NotesViewModel
import com.example.thisnote.presentation.notesList.glowingEffect
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditorScreen(
    navController: NavHostController,
    note: Note? = null
) {

    val viewModel: NotesViewModel = hiltViewModel()
    val title = remember { mutableStateOf(note?.noteTitle ?: "") }
    val text = remember { mutableStateOf(note?.noteText ?: "") }
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp)
                    .glowingEffect(Color.Cyan,12.dp,5.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.Cyan),
                onClick = {
                    viewModel.upsertNote(
                        Note(
                            0,
                            title.value,
                            text.value,
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy | HH:mm:ss"))
                        )
                    )
                    navController.navigateUp()
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_save), contentDescription = null, tint = colorResource(id = R.color.background))
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(15.dp)
        ) {

            val focusManager = LocalFocusManager.current

            TextField(
                modifier = Modifier
                    .glowingEffect(Color.White,12.dp,5.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.1f),

                value = title.value,
                onValueChange = { title.value = it },
                placeholder = {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.headlineSmall,
                        color = colorResource(id = R.color.placeholder)
                    )
                },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(id = R.color.text_title),
                    unfocusedTextColor = colorResource(id = R.color.text_title),
                    focusedContainerColor = colorResource(id = R.color.input_background),
                    unfocusedContainerColor = colorResource(id = R.color.input_background),
                    disabledContainerColor = colorResource(id = R.color.input_background),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                textStyle = MaterialTheme.typography.headlineSmall,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            TextField(
                modifier = Modifier
                    .glowingEffect(Color.White,12.dp,5.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = {
                    Text(
                        text = "Put your notes here...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(id = R.color.placeholder)
                    )
                },
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(id = R.color.text_title),
                    unfocusedTextColor = colorResource(id = R.color.text_title),
                    focusedContainerColor = colorResource(id = R.color.input_background),
                    unfocusedContainerColor = colorResource(id = R.color.input_background),
                    disabledContainerColor = colorResource(id = R.color.input_background),
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
            )

        }
    }

}