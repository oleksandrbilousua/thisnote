package com.example.thisnote.presentation.notesList

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thisnote.R
import com.example.thisnote.data.Note

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    onAddClick: () -> Unit,
    onItemClick: (Note) -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .size(50.dp)
                    .glowingEffect(Color.Blue,12.dp,5.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.Blue),
                onClick = { onAddClick.invoke() }) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null, tint = colorResource(id = R.color.background))
            }
        }
    ) {

        val viewModel: NotesViewModel = hiltViewModel()

        val notes: List<Note> by viewModel.notes.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background))
                .padding(15.dp),
        ) {
            items(notes.size) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                    )
                    NoteItem(item = notes[it]) { onItemClick(notes[it]) }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(7.dp)
                    )
                }

            }

        }
    }


}