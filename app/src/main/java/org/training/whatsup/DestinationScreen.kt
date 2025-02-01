package org.training.whatsup

sealed class DestinationScreen(val route : String) {
    object Highlight : DestinationScreen("highlight")
    object NearMe : DestinationScreen("nearMe")
    object MyEvents : DestinationScreen("myEvents")
}