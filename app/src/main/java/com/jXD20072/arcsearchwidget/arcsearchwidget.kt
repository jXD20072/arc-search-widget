package com.jXD20072.arcsearchwidget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider


class arcsearchwidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            // create your AppWidget here
            MyContent(context)
        }
    }



    @Composable
    private fun MyContent(context: Context) {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .height(60.dp)
                .cornerRadius(40.dp),
        ) {

            Image(provider = ImageProvider(R.drawable.background),
                contentDescription = "bg-image", contentScale = ContentScale.Crop,
                modifier = GlanceModifier
                    .fillMaxWidth()
            )

            Row() {
                Box(modifier = GlanceModifier
                    .fillMaxHeight()
                    .width(70.dp),
                    contentAlignment = Alignment(
                        horizontal = Alignment.CenterHorizontally,
                        vertical =  Alignment.CenterVertically)
                ) {
                    Image(provider = ImageProvider(R.drawable.arc_logo),
                        contentDescription = "arc-logo",
                        modifier = GlanceModifier
                            .fillMaxSize()
                            .padding(15.dp)
                    )
                }

                Box(modifier = GlanceModifier
                    .fillMaxSize()
                    .padding(end = 10.dp),
                    contentAlignment = Alignment(
                        horizontal = Alignment.Start,
                        vertical = Alignment.CenterVertically)
                ) {
                    Box(modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(Color(236, 236, 236))
                        .cornerRadius(30.dp),
                        contentAlignment = Alignment(
                            horizontal = Alignment.Start,
                            vertical = Alignment.CenterVertically)
                    ) {}

                    Text(text = "Search", style = TextStyle(
                        color = ColorProvider(Color(164, 164, 164, 255)),
                        fontFamily = FontFamily("Inter"),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    ), modifier = GlanceModifier
                        .padding(start = 15.dp)
                    )
                }
            }

            Button("", modifier = GlanceModifier
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Ensure it's a new task
        context.startActivity(intent)
    }
}