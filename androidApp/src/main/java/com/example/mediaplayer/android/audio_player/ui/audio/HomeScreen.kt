package com.example.mediaplayer.android.audio_player.ui.audio

import android.widget.MediaController.MediaPlayerControl
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.text.buildSpannedString
import com.example.mediaplayer.android.MyApplicationTheme
import com.example.mediaplayer.android.audio_player.data.model.Audio
import kotlin.math.floor

private val dummmyAudioList = listOf(
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "shu",
        data = "",
        duration = 12345,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "bham",
        data = "",
        duration = 25678,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "sa",
        data = "",
        duration = 8765454,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "hu",
        data = "",
        duration = 23456,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "shu",
        data = "",
        duration = 65788,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "bham",
        data = "",
        duration = 234567,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "sa",
        data = "",
        duration = 8765454,
        title = "Android Programming"
    ),
    Audio(
        uri = "".toUri(),
        displayName = "Kotlin Programming",
        id = 0L,
        artist = "hu",
        data = "",
        duration = 23456,
        title = "Android Programming"
    ),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    progress: Float,
    onProgressChange: (Float) -> Unit,
    isAudioPlaying: Boolean,
    audioList: List<Audio>,
    currentPlayingAudio: Audio?,
    onStart: (Audio) -> Unit,
    onItemClick: (Audio) -> Unit,
    onNext: () -> Unit
) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val animatedHeight by animateDpAsState(
        targetValue = if (currentPlayingAudio == null) 0.dp
        else BottomSheetScaffoldDefaults.SheetPeekHeight
    )

    BottomSheetScaffold(
        sheetContent = {
            currentPlayingAudio?.let { currentPlayingAudio ->
                BottomBarPlayer(
                    progress = progress,
                    onProgressChange = onProgressChange,
                    audio = currentPlayingAudio,
                    isAudioPlaying = isAudioPlaying,
                    onStart = { onStart.invoke(currentPlayingAudio) },
                    onNext = { onNext.invoke() }
                )
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = animatedHeight
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 50.dp)
        ) {
            items(audioList) { audio: Audio ->
                AudioItem(audio = audio, onItemClick = { onItemClick.invoke(audio) })
            }
        }
    }
}

@Composable
fun AudioItem(
    audio: Audio,
    onItemClick: (id:Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick.invoke(audio.id)
            },
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = .5f)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = audio.displayName,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Clip,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = audio.artist,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    color = MaterialTheme
                        .colors
                        .onSurface
                        .copy(alpha = .5f)
                )
            }

            Text(text = timeStampToDuration(audio.duration.toLong()))
            Spacer(modifier = Modifier.size(8.dp))

        }
    }
}

private fun timeStampToDuration(position: Long): String {
    val totalSeconds = floor(position / 1E3).toInt()
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds - (minutes * 60)

    return if (position < 0) "__:__"
    else "%d:%02d".format(minutes, remainingSeconds)
}


@Composable
fun BottomBarPlayer(
    progress: Float,
    onProgressChange: (Float) -> Unit,
    audio: Audio,
    isAudioPlaying: Boolean,
    onStart: () -> Unit,
    onNext: () -> Unit
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtistInfo(
                audio = audio,
                modifier = Modifier.weight(1f),
            )
            MediaPlayerController(
                isAudioPlaying = isAudioPlaying,
                onStart = { onStart.invoke() },
                onNext = { onNext.invoke() })
        }
        Slider(
            value = progress,
            onValueChange = { onProgressChange.invoke(it) },
            valueRange = 0f..100f
        )
    }
}

@Composable
fun MediaPlayerController(
    isAudioPlaying: Boolean,
    onStart: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(56.dp)
            .padding(4.dp)
    ) {
        PlayerIconItem(
            icon = if (isAudioPlaying) Icons.Default.Pause
            else Icons.Default.PlayArrow,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            onStart.invoke()
        }
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            imageVector = Icons.Default.SkipNext,
            contentDescription = null,
            modifier = Modifier.clickable {
                onNext.invoke()
            })
    }
}


@Composable
fun ArtistInfo(
    modifier: Modifier = Modifier, audio: Audio
) {
    Row(
        modifier = modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerIconItem(
            icon = Icons.Default.MusicNote,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface
            ),
        ) {}
        Spacer(modifier = Modifier.size(4.dp))
        Column {
            Text(
                text = audio.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Clip,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = audio.artist,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
        }

    }
}

@Composable
fun PlayerIconItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    border: BorderStroke? = null,
    backgroundColor: Color = MaterialTheme.colors.onSurface,
    color: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape, border = border, modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onClick.invoke()
            }, contentColor = color, color = backgroundColor
    ) {
        Box(
            modifier = Modifier.padding(4.dp), contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomBarPrev() {
    MyApplicationTheme {
        BottomBarPlayer(
            progress = 50f,
            onProgressChange = {},
            audio = dummmyAudioList[0],
            isAudioPlaying = true,
            onStart = { }) {
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPrev() {
    MyApplicationTheme {
        HomeScreen(
            progress = 50f,
            onProgressChange = {},
            isAudioPlaying = true,
            audioList = dummmyAudioList,
            currentPlayingAudio = dummmyAudioList[0],
            onStart = { /*TODO*/ },
            onItemClick = {}
        ) {

        }

    }
}