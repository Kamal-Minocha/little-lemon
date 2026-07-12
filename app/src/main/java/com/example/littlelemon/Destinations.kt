package com.example.littlelemon

import android.graphics.pdf.content.PdfPageGotoLinkContent

interface Destinations {
    val route: String
}

object HomeDestination: Destinations {
    override val route: String = "Home"
}
object ProfileDestination: Destinations {
    override val route: String = "Profile"
}
object OnBoardingDestination: Destinations {
    override val route: String = "OnBoarding"
}