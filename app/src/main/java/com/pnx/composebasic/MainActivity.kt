package com.pnx.composebasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnx.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// 뷰
@Composable
fun Greeting(name: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Pnx Compose App")}
                , backgroundColor = Color(0xfff25287))
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*do something*/}) {
                Text("클릭")
            }
        }
    ) {
        MyComposableView()
    }
}
@Composable
fun RowItem() {
    // horizontal 리니어 레이아웃
    Row(
        Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .background(Color(0xffeaffd0))
            .fillMaxWidth()
    ) {
        Text(text = "안녕하세영", Modifier.padding(end = 10.dp), color = Color.Red)
        Text(text = "안녕하세영", Modifier.padding(end = 10.dp), color = Color.Green)
        Text(text = "안녕하세영", Modifier.padding(end = 10.dp), color = Color.Blue)
    }
}

@Composable
fun MyComposableView() {
    Log.d("TAG", "MyComposableView")


    // vertical 리니어 레이아웃
    Column(
        Modifier
            .background(Color(0xffF6FBF4))
            .verticalScroll(rememberScrollState())

    ) {
        for( i in 0..30){
            RowItem()
        }
    }

}

// 미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greeting("Android")
    }
}