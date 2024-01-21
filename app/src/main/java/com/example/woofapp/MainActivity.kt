package com.example.woofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.woofapp.ui.theme.WoofAppTheme
import com.example.woofapp.data.Dog
import com.example.woofapp.data.dogs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppLayout()
                }
            }
        }
    }
}

@Composable
fun AppLayout(){
    LazyColumn{
        items(dogs){
            DogCard(
                dog = it,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun DogCard(dog: Dog, modifier:Modifier = Modifier){
    Card(modifier = modifier){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            DogIcon(dog.imageResourceId)
            DogInfo(dogName = dog.name, dogAge = dog.age)
        }
    }
}

@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),
        contentDescription = null,
    )
}

@Composable
fun DogInfo(
    @StringRes dogName:Int,
    dogAge:Int,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.years_old, dogAge)
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    WoofAppTheme(darkTheme = false) {
        AppLayout()
    }
}