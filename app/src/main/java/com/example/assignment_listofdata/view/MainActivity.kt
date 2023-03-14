package com.example.assignment_listofdata.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.assignment_listofdata.model.Rating
import com.example.assignment_listofdata.model.Store
import com.example.assignment_listofdata.model.StoreItem
import com.example.assignment_listofdata.ui.theme.AssignmentlistOfDataTheme
import com.example.assignment_listofdata.viewmodel.MainViewModel
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentlistOfDataTheme {
                // A surface container using the 'background' color from the theme
                Surface( color = MaterialTheme.colors.background
                ) {
//                    Greeting(name = "Android");
                    mainViewModel.getDataList()
                    StoreList(storeList = mainViewModel.storeListResponse)


                }
            }
        }
    }
}

@Composable
fun StoreList(storeList: List<Store>){
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn{
        itemsIndexed(items = storeList){ index, item ->
            StoreListItem(store = item, index, selectedIndex) { i ->
                selectedIndex = i
            }

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun StoreListItem(store: Store, index: Int, selectedIndex: Int, onClick: (Int) -> Unit ) {
    val mContext = LocalContext.current
    var backgroundColor: Color? =null
 if (index == selectedIndex){
      backgroundColor = MaterialTheme.colors.primary

     mContext.startActivity(SecondActivity.newIntent(mContext,store))
 } else {
     backgroundColor = MaterialTheme.colors.background
 }

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = backgroundColor) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = store.image,

                        builder = {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = store.description,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = store.title,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = store.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = store.description,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    AssignmentlistOfDataTheme {
//        Greeting("Android")
//    }
//}