package com.ghoul.weatherx.ui.theme.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ghoul.weatherx.R
import com.ghoul.weatherx.Weather


@Composable
fun WeatherScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        WeatherMain()
    }
}

@Composable
fun WeatherMain() {
    val weather = Weather()
    val isLightTheme = MaterialTheme.colorScheme.primary
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.size(2.dp))

        Text(
            text = weather.date,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Row(modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
            ReusableImage(
                image = if (isLightTheme== Color.White) {
                    R.drawable.ic_location_light
                } else {
                    R.drawable.ic_location_dark
                },
                contentScale = ContentScale.Fit,
                contentDesc = "Location Icon",
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = weather.location,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .matchParentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ReusableImage(
                    image =
                    if (isLightTheme== Color.White) {
                        R.drawable.ic_world_map_light
                    } else {
                        R.drawable.ic_world_map_dark
                    },
                    contentScale = ContentScale.Fit,
                    contentDesc = "Background",
                    modifier = Modifier.offset(y = (-20).dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReusableImage(
                    image = R.drawable.sun,
                    contentScale = ContentScale.Fit,
                    contentDesc = "Weather img",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 40.dp, bottom = 20.dp)
                )
                Text(text = weather.condition, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.size(4.dp))
                Row(modifier = Modifier.wrapContentSize()) {
                    Text(
                        text = weather.update.first().temp,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.offset(y = (-24).dp)
                    )
                    ReusableImage(
                        image = R.drawable.ic_degree,
                        contentScale = ContentScale.Fit,
                        contentDesc = "Degree Icon",
                        modifier = Modifier
                    )
                }
                Text(
                    text = Weather().description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
        DailyWeatherList(weather)
    }
}

@Composable
fun DailyWeatherList(weather: Weather) {
    LazyRow(
        content = {
            items(weather.update) { weatherUpdate ->
                DailyWeatherItem(weatherUpdate)
            }
        }
    )
}


@Composable
fun DailyWeatherItem(weatherUpdate: Weather.WeatherUpdate) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp), shape = MaterialTheme.shapes.small
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            ReusableImage(
                image = when (weatherUpdate.icon) {
                    "wind" -> R.drawable.ic_moon_cloud_fast_wind
                    "rain" -> R.drawable.ic_moon_cloud_mid_rain
                    "angledRain" -> R.drawable.ic_sun_cloud_angled_rain
                    "thunder" -> R.drawable.ic_zaps
                    else -> R.drawable.ic_moon_cloud_fast_wind
                },
                contentScale = ContentScale.Fit,
                contentDesc = "Weather Icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 4.dp)
            )
            Text(
                text = weatherUpdate.time,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = "${weatherUpdate.temp}°",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ReusableImage(image: Int, contentScale: ContentScale, contentDesc: String, modifier: Modifier) {
    val paintImage: Painter = painterResource(id = image)
    Image(
        painter = paintImage,
        contentDescription = contentDesc,
        contentScale = contentScale,
        modifier = modifier
    )
}

