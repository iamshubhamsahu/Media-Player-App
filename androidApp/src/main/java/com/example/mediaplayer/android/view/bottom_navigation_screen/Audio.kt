package com.example.mediaplayer.android.view.bottom_navigation_screen

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.mediaplayer.android.R

@Composable
fun Audio() {
    val context = LocalContext.current
    var mediaPlayer = MediaPlayer.create(context, R.raw.music)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            mediaPlayer.start()
        })
        {
            Text(text = "Play")
        }
        Button(onClick = {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        })
        {
            Text(text = "stop")
        }


    }
}