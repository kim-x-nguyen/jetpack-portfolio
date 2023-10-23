package com.example.packpub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.packpub.ui.theme.PackpubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackpubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember { mutableStateOf(false) }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Card(modifier = Modifier
            .width(200.dp)
            .padding(12.dp)
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )) {
            // Card content
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CreateProfileImage()
                Divider(thickness = 0.75.dp, color = Color.LightGray)
                CreateProfileDetails()
                CreateShowPortfolioButton(buttonClickedState)
            }
        }
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        //Add elevation to the surface
        shadowElevation = 4.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun CreateProfileDetails(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Kim Nguyen",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Blue,
                modifier = Modifier.padding(5.dp),
            )
            Divider(
                thickness = 0.5.dp,
                color = Color.LightGray,
                modifier = Modifier.padding(5.dp)
            )
            Text(text = "Android Developer",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp),
            )
            Text(text = "@kimnguyen",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp),
            )
        }
    }
}

@Composable
private fun CreateShowPortfolioButton(buttonClickedState: MutableState<Boolean>, modifier: Modifier = Modifier ) {
        ElevatedButton(
            onClick = {
                buttonClickedState.value = !buttonClickedState.value
            },
            modifier = Modifier
                .padding(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        ) {
            Text("Show Portfolio")
        }
        if (buttonClickedState.value) {
            Content()
        } else {
            Box(){
                // Empty box
            }
        }
}

//@Preview(showBackground = true)
@Composable
private fun Content() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(0.5.dp, Color.LightGray),) {
                Portfolio(data = listOf("Android", "Kotlin", "Java", "Python", "C++"))
            }
        }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(data.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                )) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CreateProfileImage(modifier = Modifier.size(50.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = data[index],
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(10.dp),
                        )
                        Text(text = "An exploration of the new Android Jetpack Compose UI toolkit.",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(10.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PackpubTheme {
        CreateBizCard()
    }
}