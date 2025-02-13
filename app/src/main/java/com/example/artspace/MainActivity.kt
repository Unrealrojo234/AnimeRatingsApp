package com.example.artspace

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            ArtGalleryApp(modifier = Modifier)
                        }
                    }
                )
            }
        }
    }
}

// Define a data class to hold the state and the update function
data class SharedState(
    val value: Int,
    val updateValue: (Int) -> Unit // Function to update the state
)

// Define the CompositionLocal
val LocalSharedState = staticCompositionLocalOf<SharedState> {
    error("SharedState not provided! Wrap your composable in a CompositionLocalProvider.")
}

val animes = arrayOf(
    arrayOf(R.drawable.highschooldxd, "HighSchool DxD", "9.7", R.string.highdchooldxd),
    arrayOf(R.drawable.highschoolofthedead, "High School Of The Dead", "9.0", R.string.highSchoolOfTheDead),
    arrayOf(R.drawable.freezing, "Freezing", "8.7", R.string.Freezing),
    arrayOf(R.drawable.prisonschool, "Prison School", "9.9", R.string.PrisonSchool),
    arrayOf(R.drawable.domestichgirlfriend, "Domestic Girlfriend", "10.0", R.string.DomesticGirlfriend),
    arrayOf(R.drawable.demonkingdiamao, "Demon King Diamao", "8.6", R.string.DemonKingDiamao),
    arrayOf(R.drawable.monstergirldoctor, "Monster Girl Doctor", "9.2", R.string.MonsterGirlDoctor),
    arrayOf(R.drawable.monstermusume, "Monster Musume", "8.3", R.string.MonsterMusume),
    arrayOf(R.drawable.blade_of_the_immortal, "Blade of The Immortal", "8.5", R.string.BladeofTheImmortal),
    arrayOf(R.drawable.harem_in_the_labyrinth_of_another_world_1, "Harem In The Labyrinth Of Another World", "8.6", R.string.HaremInTheLabyrinthOfAnotherWorld)
)

@Composable
fun Carousel(modifier: Modifier = Modifier, imageId: Int, name: String, ratings: String, descriptionId: Int) {
    Card(
        modifier = Modifier
            .width(300.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = imageId),
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = name,
                style = TextStyle(
                    fontSize = 24.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val annotedString = buildAnnotatedString {
                    append("Ratings ")

                    withStyle(style = SpanStyle(color = Color.Yellow, fontSize = 16.sp)){
                        append("✪")
                    }
                }
                Text(
                    text = annotedString,
                    modifier = Modifier
                )

                Text(
                    modifier = Modifier,
                    color = colorResource(id = R.color.yellow_green),
                    text = ratings
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    text = "Description",
                    color = Color.DarkGray
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = descriptionId)
                )
            }
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    next: Boolean,
    isEnabled: Boolean
) {
    val sharedState = LocalSharedState.current

    Button(
        onClick = {
        /*TODO*/

            // Update the shared state from this component
            if(next){
                if(sharedState.value in 0..<animes.size-1){
                    sharedState.updateValue(sharedState.value +1)

                }

            }else{
                if(sharedState.value in 1..animes.size){
                    sharedState.updateValue(sharedState.value -1)

                }


            }
        },
        enabled = isEnabled,
        modifier = Modifier
            .width(120.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.yellow_green)),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun ScrollButtons(modifier: Modifier = Modifier) {
    val sharedState = LocalSharedState.current

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(text = "Previous", next = false, isEnabled =if(sharedState.value < 1)false else true)
        ActionButton(text = "Next", next = true, isEnabled = if(sharedState.value > animes.size-2)false else true)
    }
}

@Composable
fun ArtGalleryApp(modifier: Modifier = Modifier) {
    // Create mutable state and update function
    var sharedValue by remember { mutableIntStateOf(0) }
    val updateValue = { newValue: Int ->
        sharedValue = newValue
    }

    // Provide the shared state and update function
    CompositionLocalProvider(
        LocalSharedState provides SharedState(sharedValue, updateValue)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                // Display the current shared state
//                DisplaySharedState()

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier)

                    val sharedState = LocalSharedState.current


                        val currentIndex = sharedState.value
                        val imageId = animes[currentIndex][0] as Int
                        val name = animes[currentIndex][1] as String
                        val ratings = animes[currentIndex][2] as String
                        val descriptionId = animes[currentIndex][3] as Int

                        Carousel(
                            imageId = imageId,
                            name = name,
                            ratings = ratings,
                            descriptionId = descriptionId
                        )

                    Spacer(modifier = Modifier.padding(bottom = 100.dp))
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.goodblue),
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // Fixed buttons at the bottom
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                ScrollButtons()
            }
        }
    }
}


