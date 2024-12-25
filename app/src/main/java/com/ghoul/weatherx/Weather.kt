package com.ghoul.weatherx

data class Weather(
    val date: String = "2, November 2023",
    val description: String = "Cooling down with a chance of rain Sunday, Monday & Tuesday.",
    val condition: String = "Sunny",
    val location: String = "Kharar, Punjab",
    val update: List<WeatherUpdate> = weatherList
) {
    data class WeatherUpdate(
        val time: String,
        val temp: String,
        val icon: String
    )
}

val weatherList = arrayListOf(
    Weather.WeatherUpdate("03:00:00", "30.0", "sun"),
    Weather.WeatherUpdate("04:30:00", "26.6", "sun"),
    Weather.WeatherUpdate("06:00:00", "27.8", "sun"),
    Weather.WeatherUpdate("07:30:00", "29.3", "angledRain"),
    Weather.WeatherUpdate("08:00:00", "25.5", "wind"),
)