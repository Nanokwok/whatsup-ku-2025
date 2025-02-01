package org.training.whatsup

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class WhatsUpNavItemInfo(
    val label:String = "",
    val icon:ImageVector = Icons.Filled.Star,
    val route:String = ""
) {
    fun getAllNavItems() : List<WhatsUpNavItemInfo> {
        return listOf(
            WhatsUpNavItemInfo("Highlight", Icons.Filled.Star, DestinationScreen.Highlight.route),
            WhatsUpNavItemInfo("Near Me", Icons.Filled.LocationOn, DestinationScreen.NearMe.route),
            WhatsUpNavItemInfo("My Events", Icons.Filled.Face, DestinationScreen.MyEvents.route)
        )
    }
}
