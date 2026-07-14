package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menuItems: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id = id,
        title = title,
        price = price.toDoubleOrNull() ?: 0.0,
        desc = description,
        category = category,
        image = image
    )
}



