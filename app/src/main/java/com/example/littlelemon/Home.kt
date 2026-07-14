package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalInspectionMode
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.Karla
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.MarkaziText
import kotlin.collections.emptyList

@Composable
fun Home(navController: NavHostController, database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(initial = emptyList())

    Column {
        TopAppBar(navController)
        HeroSection(databaseMenuItems)
    }
}

@Composable
fun HeroSection(menuItemsLocal: List<MenuItemRoom>) {
    var searchPhrase by remember { mutableStateOf("") }
    var menuItems = menuItemsLocal
    var selectedCategory by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.green)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.restaurant_name),
            style = MaterialTheme.typography.displayLarge,
            color = LittleLemonColor.yellow,
            fontFamily = MarkaziText
        )
        Text(
            text = stringResource(R.string.restaurant_city),
            style = MaterialTheme.typography.displayMedium,
            color = LittleLemonColor.cloud,
            fontFamily = MarkaziText,
            modifier = Modifier.offset(y = (-15).dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 0.dp)
                .offset(y = (-15).dp)
        ) {
            Text(
                text = stringResource(R.string.restaurant_desc),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f),
                color = LittleLemonColor.cloud
            )
            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = stringResource(R.string.hero_image_description),
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(0.dp))
        TextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder),
                    style = MaterialTheme.typography.bodyMedium,
                    color = LittleLemonColor.charcoal,
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = LittleLemonColor.charcoal
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LittleLemonColor.cloud,
                unfocusedContainerColor = LittleLemonColor.cloud,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LittleLemonColor.charcoal
            )
        )
        if(searchPhrase.isNotEmpty()) {
            menuItems = menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(LittleLemonColor.cloud)
        ) {
            val scrollState = rememberScrollState()

            Text(
                text = stringResource(R.string.order_delivery),
                modifier = Modifier.padding(top = 15.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .horizontalScroll(scrollState)
            ) {
                Button(
                    onClick = {
                        selectedCategory = "starters"
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.category_starters),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Button(
                    onClick = {
                        selectedCategory = "mains"
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.category_mains),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Button(
                    onClick = {
                        selectedCategory = "desserts"
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.category_desserts),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Button(
                    onClick = {
                        selectedCategory = "drinks"
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.category_drinks),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            if(selectedCategory.isNotEmpty()) {
                menuItems = menuItems.filter { it.category.contains(selectedCategory) }
            }
            MenuItems(menuItems)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItems(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Karla
                        )
                        Text(
                            text = menuItem.desc,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = Karla,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(vertical = 5.dp),
                            color = Color(0xFFAFAFAF)
                        )
                        Text(
                            text = stringResource(R.string.price_format).format(menuItem.price),
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = Karla,
                            color = Color(0xFFAFAFAF),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    if (LocalInspectionMode.current) {
                        Image(
                            painter = painterResource(R.drawable.hero_image),
                            contentDescription = stringResource(R.string.menu_item_image_description),
                            modifier = Modifier
                                .size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        GlideImage(
                            model = menuItem.image,
                            contentDescription = stringResource(R.string.menu_item_image_description),
                            modifier = Modifier
                                .size(100.dp),
                            contentScale = ContentScale.Crop,
                            requestBuilderTransform = {
                                it.placeholder(R.drawable.hero_image).error(R.drawable.hero_image)
                            }
                        )
                    }
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HeroSectionPreview() {
//    HeroSection()
//}

@Preview(showBackground = true)
@Composable
fun MenuItemsPreview() {
    MenuItems(items = listOf(
        MenuItemRoom(
            id = 1,
            title = "Greek Salad",
            price = 12.99,
            desc = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago style feta cheese, garnished with crunchy garlic and rosemary croutons.",
            category = "starters",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true"
        )
    ))
}