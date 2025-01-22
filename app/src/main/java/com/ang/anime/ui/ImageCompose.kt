package com.ang.anime.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ImageCompose(modifier: Modifier=Modifier,
                 data:Any="",
                 loadingImg:ImageVector= Icons.Default.HourglassBottom,
                 errorImg:ImageVector= Icons.Default.BrokenImage,
                 iconPadding:PaddingValues= PaddingValues(8.dp)
){
    val context= LocalContext.current
    SubcomposeAsyncImage(modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = "",
        loading = {
            Icon(
                imageVector = loadingImg, // Replace with your icon resource
                contentDescription = "Loading Icon", // Replace with your string resource
                modifier = Modifier.matchParentSize().padding(iconPadding)
            )
        },
        error = {
            Icon(
                imageVector = errorImg, // Replace with your icon resource
                contentDescription = "Create Group", // Replace with your string resource
                modifier = Modifier.matchParentSize().padding(iconPadding)
            )
        })
}