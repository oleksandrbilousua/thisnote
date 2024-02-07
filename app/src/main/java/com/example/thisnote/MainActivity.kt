package com.example.thisnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thisnote.data.Note
import com.example.thisnote.presentation.noteEditor.EditorScreen
import com.example.thisnote.presentation.notesList.NoteListScreen
import com.example.thisnote.presentation.notesList.NotesViewModel
import com.example.thisnote.ui.theme.ThisNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThisNoteTheme {
                NoteNavigator()
            }
        }
    }
}


@Composable
fun NoteNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "home_screen") {
            NoteListScreen(
                onAddClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("note", null)
                    navController.navigate("editor_screen")
                },
                onItemClick = { note ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("note", note)
                    navController.navigate("editor_screen")

                })
        }
        composable(route = "editor_screen") {
            val note: Note? = navController.previousBackStackEntry?.savedStateHandle?.get<Note>("note")
            EditorScreen(
                navController,
                note = note
            )

        }
    }
}

