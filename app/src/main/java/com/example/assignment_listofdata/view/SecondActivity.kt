package com.example.assignment_listofdata.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assignment_listofdata.Constants.Companion.CATEGORY
import com.example.assignment_listofdata.Constants.Companion.COUNT
import com.example.assignment_listofdata.Constants.Companion.DESCRIPTION
import com.example.assignment_listofdata.Constants.Companion.ID
import com.example.assignment_listofdata.Constants.Companion.IMAGE
import com.example.assignment_listofdata.Constants.Companion.PRICE
import com.example.assignment_listofdata.Constants.Companion.RATE
import com.example.assignment_listofdata.Constants.Companion.TITLE
import com.example.assignment_listofdata.model.Rating
import com.example.assignment_listofdata.model.Store
import com.example.assignment_listofdata.ui.theme.AssignmentlistOfDataTheme

class SecondActivity : ComponentActivity() {

    private val store: Store by lazy {
        intent?.getSerializableExtra(STORE_ID,) as Store
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentlistOfDataTheme{
               SecondScreen(store)
            }

        }
    }
    companion object {
private const val STORE_ID = "store_id"
        fun newIntent(context: Context, store: Store) =
            Intent(context, SecondActivity::class.java).apply {
                putExtra(STORE_ID,store)

            }

    }

}