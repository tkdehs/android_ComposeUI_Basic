package com.pnx.composebasic

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.pnx.composebasic.ui.theme.ComposeBasicTheme
import com.pnx.composebasic.util.DummyDataProvider
import com.pnx.composebasic.util.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                CardRowItem()
            }
        }
    }
}

@Composable
fun CardRowItem() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(backgroundColor = Color.White, topBar = {MyTabBar()} ) {
            RandomUserListView(randomusers = DummyDataProvider.userList)
        }
    }
}

@Composable
fun MyTabBar() {
    TopAppBar(elevation = 10.dp,
        modifier = Modifier.height(58.dp),
        backgroundColor = MaterialTheme.colors.primary
        ) {
       Text(
           text = stringResource(id = R.string.my_app_name),
           modifier = Modifier
               .padding(8.dp)
               .align(Alignment.CenterVertically),
           fontSize = 18.sp,
           fontWeight = FontWeight.Black
       )
    }
}
@Composable
fun RandomUserListView(randomusers: List<RandomUser>) {
    // 메모리 관리가 들어간 LazyColumn (리사이클러뷰와 비슷함)
    LazyColumn() {
        items(randomusers) {
            RandomUserView(it)
        }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
    elevation = 10.dp,
    shape = RoundedCornerShape(12.dp)) {
        Row(modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            ProfileImage(randomUser.profileImage)
            Column() {
                Text(text = randomUser.name,
                    style = typography.subtitle1)
                Text(text = randomUser.discription,
                    style = typography.body1)

            }
        }
    }
}

@Composable
fun ProfileImage(imgUrl:String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = remember {
        mutableStateOf(null)
    }
    val imageMidofire = Modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object: CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    // 비트맵이 있다면
    bitmap.value?.asImageBitmap()?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageMidofire
        )
    } ?: Image(
        painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageMidofire)
}

// 미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        CardRowItem()
    }
}