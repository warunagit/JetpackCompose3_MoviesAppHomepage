package com.example.jetpack3_moviesapphomepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.jetpack3_moviesapphomepage.ui.theme.Jetpack3_moviesapphomepageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack3_moviesapphomepageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Modifier.padding(innerPadding)
                    Box(Modifier.fillMaxSize().padding(innerPadding)) {
                        MovieAppHomepage()
                    }
                }
            }
        }
    }
}

@Composable
fun MovieAppHomepage(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarSection()
        Spacer(modifier = Modifier.height(16.dp))
        MovieCategorySection()
        Spacer(modifier = Modifier.height(16.dp))
        NowShowingSection()
    }
}

@Composable
fun SearchBarSection(modifier: Modifier = Modifier) {
    OutlinedTextField(
        value="",
        onValueChange = {},
        placeholder = {
            Text(
                text = "Search Movies"
            )
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
}

@Composable
fun MovieCategorySection(modifier: Modifier = Modifier) {
    val categories = listOf(
        "Action","SiFi","Adventure","Dark","Comedy","Family"
    )

    LazyRow() {
        items(categories){
            CategoryTemplate(it)
        }
    }
}

@Composable
fun CategoryTemplate(category: String) {
    val selected = remember {
        mutableStateOf(false)
    }

    val backgroundColor by animateColorAsState(
        targetValue = if(selected.value) Color.Black else Color.LightGray,
        animationSpec = tween(500)
    )

    Card (
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                selected.value = !selected.value
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 40.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ){
        Text(
            text = category,
            modifier = Modifier.padding(8.dp),
            color = if(selected.value)Color.White else Color.Black
        )
    }
}

@Composable
fun NowShowingSection() {
    Text(text = "Now Showing", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.fillMaxWidth())
    LazyRow {
        items(5){
            MoviePosterItem(
                title = "movie $it"
            )
        }
    }
}

@Composable
fun MoviePosterItem(title: String) {
    val scale = remember{
        Animatable(0.8f)
    }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring()
        )
    }
    Card(
        modifier = Modifier
            .size(
                width = 150.dp,
                height = 150.dp
            )
            .padding(
                end = 8.dp
            )
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(

        )
    ){
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = title,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}