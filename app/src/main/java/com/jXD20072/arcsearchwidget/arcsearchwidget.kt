package com.jXD20072.arcsearchwidget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.unit.ColorProvider


class arcsearchwidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            MyContent(context)
        }
    }

    @Composable
    private fun MyContent(context: Context) {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(52.dp)
                .background(Color.White)
                .cornerRadius(40.dp),
        ) {

            Image(
                provider = ImageProvider(R.drawable.background),
                contentDescription = "bg-image",
                contentScale = ContentScale.Crop,
                modifier = GlanceModifier
                    .fillMaxWidth()
            )

            Row() {
                Box(
                    modifier = GlanceModifier
                        .size(52.dp)
                        .padding(start = 10.dp),
                    contentAlignment = Alignment(
                        horizontal = Alignment.CenterHorizontally,
                        vertical =  Alignment.CenterVertically)
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.arc_logo),
                        contentDescription = "arc-logo",
                        modifier = GlanceModifier
                            .size(34.dp)
                    )
                }

                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment(
                        horizontal = Alignment.Start,
                        vertical = Alignment.CenterVertically)
                ) {
                    // Glance doesn't support shadows natively so we have to add it with xml
                    AndroidRemoteViews(RemoteViews(LocalContext.current.packageName, R.layout.search_box),
                        modifier = GlanceModifier
                            .fillMaxSize()
                    )
                }
            }

            Button(
                text = "",
                modifier = GlanceModifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorProvider(Color.Transparent),
                    contentColor = ColorProvider(Color.Transparent)
                ),
                onClick = fun() {
                    openArc(context)
                }
            )
        }
    }
}

fun openArc(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage("company.thebrowser.arc")
    if (intent != null) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}