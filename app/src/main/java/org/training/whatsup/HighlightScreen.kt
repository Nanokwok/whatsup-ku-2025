package org.training.whatsup

import android.media.metrics.Event
import android.util.EventLog
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import org.training.whatsup.ui.theme.WhatsUpTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import org.training.whatsup.customui.theme.AppTheme
import java.text.SimpleDateFormat

data class EventData(
    val event_name: String? = "",
    val event_venue: String? = "",
    val start_date: Timestamp? = null,
    val end_date: Timestamp? = null
)

@Composable
fun HighlightScreen() {
    val screenContext = LocalContext.current
    val eventList = remember { mutableStateListOf<EventData?>() }

    val onFirebaseQueryFailed = { e:Exception ->
        Toast.makeText(screenContext, e.message,
            Toast.LENGTH_LONG).show()
    }

    val onFirebaseQuerySuccess = { result: QuerySnapshot ->
        if(!result.isEmpty) {
            val resultDocuments = result.documents
            for (document in resultDocuments) {
                val event: EventData? = document.toObject(EventData::class.java)
                eventList.add(event)

                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val start = dateFormatter.format(event?.start_date?.toDate())
                val end = dateFormatter.format(event?.end_date?.toDate())
                Toast.makeText(screenContext,
                    "${event?.event_name} at ${event?.event_venue} (${start} - ${end})",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    getEventsFromFirebase(onFirebaseQuerySuccess, onFirebaseQueryFailed)

    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column ( modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Highlight")
                EventList(events = eventList)
            }
        }
    }
}

private fun getEventsFromFirebase(onSuccess: (QuerySnapshot) -> Unit,
                                  onFailure: (Exception) -> Unit)
{
    val db = Firebase.firestore
    db.collection("events").get()
        .addOnSuccessListener { result -> onSuccess(result) }
        .addOnFailureListener { exception -> onFailure(exception) }
}

@Composable
fun EventItem(event: EventData) {
    val dateFormatter = SimpleDateFormat("dd-MM-yyyy")

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.md_theme_surfaceContainerHigh)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Text(
                text = event.event_name ?: "Unknown Event",
                fontSize = 20.sp,
                color = colorResource(id = R.color.md_theme_primary)
            )
            Text(
                text = event.event_venue ?: "Unknown Venue",
                fontSize = 18.sp,
                color = colorResource(id = R.color.md_theme_secondary)
            )
        Row {
            Text(text = "From: ",
                style = TextStyle(color = colorResource(id = R.color.md_theme_secondary),
                    fontSize = 18.sp)
            )
            Text(text = dateFormatter.format(event?.start_date?.toDate()),
                style = TextStyle(fontSize = 18.sp)
            )
        }
        Row {
            Text(
                text = "To: ",
                style = TextStyle(
                    color = colorResource(id = R.color.md_theme_secondary),
                    fontSize = 18.sp
                )
            )
            Text(
                text = dateFormatter.format(event?.end_date?.toDate()),
                style = TextStyle(fontSize = 18.sp)
            )
        }
        }
    }
}

@Composable
fun EventList(events: List<EventData?>) {
    LazyColumn(contentPadding = PaddingValues(all = 4.dp)) {
        items(items = events.filterNotNull()) {
            EventItem(event = it)
        }
    }
}
