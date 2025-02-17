package org.training.whatsup

import android.view.Surface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import org.training.whatsup.ui.theme.WhatsUpTheme
import androidx.compose.material3.Text
import org.training.whatsup.customui.theme.AppTheme

@Composable
fun MyEventsScreen() {
    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column (modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Events to Attend")
            }
        }
    }
}