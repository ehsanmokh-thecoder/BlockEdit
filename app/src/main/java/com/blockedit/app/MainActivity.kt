package com.blockedit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Grass = Color(0xFF579447)
private val Dirt = Color(0xFF795139)
private val Stone = Color(0xFF45494A)
private val DarkStone = Color(0xFF292D2E)
private val Cream = Color(0xFFF1E6C8)
private val Diamond = Color(0xFF62DED0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BlockEditApp()
            }
        }
    }
}

@Composable
fun BlockEditApp() {
    var selectedTool by remember { mutableStateOf("Trim") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkStone
    ) {
        Column(Modifier.fillMaxSize()) {
            Column(
                Modifier.fillMaxWidth().background(Dirt)
            ) {
                Box(
                    Modifier.fillMaxWidth()
                        .height(8.dp).background(Grass)
                )
                Row(
                    Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("▦", fontSize = 30.sp)
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "BlockEdit",
                            color = Cream,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            "MINECRAFT VIDEO STUDIO",
                            color = Cream,
                            fontSize = 10.sp
                        )
                    }
                    PixelButton("EXPORT", Diamond) {
                        selectedTool = "Export"
                    }
                }
            }

            Column(
                Modifier.weight(1f).padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "MY PROJECT",
                    color = Cream,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    Modifier.fillMaxWidth().weight(1f)
                        .border(3.dp, Grass)
                        .background(Color(0xFF202923))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("🌄", fontSize = 55.sp)
                    Spacer(Modifier.height(8.dp))
                    Text(
                        if (selectedTool == "Export")
                            "EXPORT NOT READY YET"
                        else "YOUR WORLD. YOUR VIDEO.",
                        color = Cream,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Your editing adventure starts here",
                        color = Cream,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(14.dp))
                    PixelButton("＋ ADD VIDEO", Grass) {
                        selectedTool = "Import"
                    }
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    PixelButton("↶", Stone) {
                        selectedTool = "Undo"
                    }
                    Spacer(Modifier.width(12.dp))
                    PixelButton("▶ PLAY", Grass) {
                        selectedTool = "Preview"
                    }
                    Spacer(Modifier.width(12.dp))
                    PixelButton("↷", Stone) {
                        selectedTool = "Redo"
                    }
                }

                Column(
                    Modifier.fillMaxWidth().border(2.dp, Stone)
                        .background(Color(0xFF343839)).padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "TIMELINE",
                            color = Cream,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.weight(1f))
                        Text("00:00 / 00:00", color = Diamond)
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        Modifier.fillMaxWidth().height(46.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(5) { i ->
                            Box(
                                Modifier.weight(1f).fillMaxHeight()
                                    .border(1.dp, Grass)
                                    .background(if (i == 0) Dirt else DarkStone),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    if (i == 0) "+" else "···",
                                    color = Cream
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "VIDEO TRACK • EMPTY",
                        color = Cream,
                        fontSize = 10.sp
                    )
                }

                Text(
                    "CRAFTING TABLE / TOOLS",
                    color = Cream,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )

                val tools = listOf(
                    "Trim", "Split", "Music", "Text", "Effects", "Filters"
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tools) { tool ->
                        Column(
                            Modifier.width(76.dp)
                                .border(
                                    2.dp,
                                    if (selectedTool == tool) Diamond else Stone
                                )
                                .background(
                                    if (selectedTool == tool) Grass else Stone
                                )
                                .clickable { selectedTool = tool }
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                when (tool) {
                                    "Trim" -> "✂"
                                    "Split" -> "⚔"
                                    "Music" -> "♫"
                                    "Text" -> "T"
                                    "Effects" -> "✦"
                                    else -> "◈"
                                },
                                color = Cream,
                                fontSize = 22.sp
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(tool, color = Cream, fontSize = 11.sp)
                        }
                    }
                }

                Text("Selected: $selectedTool", color = Diamond)
            }

            Column {
                Box(
                    Modifier.fillMaxWidth().height(5.dp).background(Grass)
                )
                Box(
                    Modifier.fillMaxWidth().background(Dirt).padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "BLOCKEDIT • BUILD YOUR STORY",
                        color = Cream,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun PixelButton(
    label: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        Modifier.border(2.dp, Color.Black)
            .background(color)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black
        )
    }
}
