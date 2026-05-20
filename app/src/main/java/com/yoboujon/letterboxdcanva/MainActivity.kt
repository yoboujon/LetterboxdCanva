package com.yoboujon.letterboxdcanva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.yoboujon.letterboxdcanva.ui.theme.LetterboxdCanvaTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetterboxdCanvaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val items: List<Triple<String, Int, String>> = listOf(
        Triple("User", R.drawable.person_24px, "user"),
        Triple("Create", R.drawable.wall_art_24px, "create"),
    )
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, (label, icon, _) ->
                    NavigationBarItem(
                        icon = { Icon(painterResource(icon), contentDescription = label) },
                        label = { Text(label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        enabled = (label == "User")
                    )
                }
            }
        }
    ) { innerPadding ->
        UserPage(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun UserPage(name: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.padding(12.dp).fillMaxWidth()) {
        Column {
            Text(
                text = "Letterboxd Canva",
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Username") },
                modifier = modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(50.dp)
            )
        }
    }
}
