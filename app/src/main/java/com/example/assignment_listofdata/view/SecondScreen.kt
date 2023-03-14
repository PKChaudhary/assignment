package com.example.assignment_listofdata.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.assignment_listofdata.model.Store
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.assignment_listofdata.Constants.Companion.CATEGORY
import com.example.assignment_listofdata.Constants.Companion.COUNT
import com.example.assignment_listofdata.Constants.Companion.DESCRIPTION
import com.example.assignment_listofdata.Constants.Companion.PRICE
import com.example.assignment_listofdata.Constants.Companion.RATE
import com.example.assignment_listofdata.Constants.Companion.TITLE

@Composable
fun SecondScreen(store: Store) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    StoreItemDetailHeader(
                        scrollState,
                        store,
                        this@BoxWithConstraints.maxHeight
                    )
                    StoreItemContent(store, this@BoxWithConstraints.maxHeight)
                }
            }

        }
    }
}

@Composable
private fun GreetingTo(name: String) {
    Text(text = "Hello $name!")
}

@Composable
private fun StoreItemDetailHeader(
    scrollState: ScrollState,
    store: Store,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }


    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = rememberImagePainter(
            data = store.image,
        ),
        contentScale = ContentScale.Crop,
        contentDescription = store.description,

    )

}

@Composable
private fun StoreItemContent(store: Store, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Name(store)
        StoreItemDetail(CATEGORY,store.category )
        StoreItemDetail(PRICE,"$ ${store.price}" )
        StoreItemDetail(RATE,"${store.rating.rate}" )
        StoreItemDetail(COUNT,"${store.rating.count}")
        StoreItemDetail(DESCRIPTION,store.description )
    }
}

@Composable
private fun Name(
    store: Store
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            store = store,
            modifier = Modifier.height(32.dp)
        )
    }
}

@Composable
private fun Name(store: Store, modifier: Modifier = Modifier) {
    Text(
        text = store.title,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun StoreItemDetail(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, bottom = 5.dp)) {
//        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.height(24.dp),
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.body1,
        )
    }

}

