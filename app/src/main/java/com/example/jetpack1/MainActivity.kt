package com.example.jetpack1

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.example.jetpack1.ui.theme.Jetpack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack1Theme {

                MyShowHideEx1()
            }
        }
    }
}

@Composable
fun MyTextEx() {

    // text
    Text(
        text = "안녕하세요 텍스트 예제입니다.",
        fontSize = 30.sp,
        color = Color.Red,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(30.dp),
        style = TextStyle(background = Color.Blue)
    )
}

@Composable
fun MyBtn() {
    val context = LocalContext.current

    Button(onClick = {
        Log.d("Main", "onClick")
        Toast.makeText(context, "클릭완료", Toast.LENGTH_LONG).show()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow,
            contentColor = Color.Blue
        ),
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
        ) {
            Text(
                text = "버튼버튼 버튼입니다 버튼버튼버튼버튼",
                Modifier.padding(vertical = 50.dp),
                fontSize = 30.sp,
                color = Color.Red
            )
    }
}

@Composable
fun SimpleCounterBtn() {
    var count by remember { mutableStateOf(0) }

    Button(onClick = {
        count++
    },
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Count : $count",
            fontSize = 50.sp
        )
    }
}

@Composable
fun ColumnTest1() {
    Text(
        text = "안녕하세요1",
        fontSize = 30.sp
    )
    Text(
        text = "반갑습니다1",
        fontSize = 30.sp,
        modifier = Modifier.padding(top = 50.dp)
    )
    Text(
        text = "반갑습니다2",
        fontSize = 30.sp,
        modifier = Modifier.padding(top = 100.dp)
    )
    // -> 비효율적임

    // column -> 세로로 줄 세워서 배치
    // spacer / divider
}

@Composable
fun ColumnTest2() {
    Column(
        modifier = Modifier.padding(30.dp)
    ) {
        Text(
            text = "안녕하세요1",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(30.dp))
        Divider(
            thickness = 4.dp,
            color = Color.Blue
        )
        Text(
            text = "안녕하세요2",
            fontSize = 30.sp
        )
        Text(
            text = "안녕하세요3",
            fontSize = 30.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField1() {
    var textState : String by remember {
        mutableStateOf("Hello")
    }
    
    TextField(
        value = textState,
        onValueChange = {
            // textState = "이것은 변화"
            textState = it
        },
        label = {
            Text(text = "입력하는 공간")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField2() {
    var textState : String by remember {
        mutableStateOf("Hello")
    }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            // textState = "이것은 변화"
            textState = it
        },
        label = {
            Text(text = "입력하는 공간")
        }
    )
}

// TextField 부분에 뭔가 입력하고
// 버튼을 클릭하면
// 밑에 Text부분에 입력한 값이 나오도록 하는 기능
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField3() {
    // 입력한 부분
    var textState : String by remember {
        mutableStateOf("")
    }

    // 결과값 부분
    var enteredText : String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, // 세로로 중앙정렬
        horizontalAlignment = Alignment.CenterHorizontally // 가로로 중앙정렬
    ) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.fillMaxWidth()

        )

        Button(
            onClick = {
               enteredText = textState
        },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "입력하기")
        }

        Text(
            text = "결과값 텍스트 : ${enteredText}"
        )
    }
}

@Composable
fun MyImageTest1() {
    Image(
        painter = painterResource(id = R.drawable.bok),
        contentDescription = "bok"
    )
}

// https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230306_241%2F1678084896699VbyT8_JPEG%2F39045842907841710_242237626.jpg&type=a340
@Composable
fun MyImageTest2() {
    AsyncImage(model = "https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20230306_241%2F1678084896699VbyT8_JPEG%2F39045842907841710_242237626.jpg&type=a340",
        contentDescription = "사랑하는 라이언",
        modifier = Modifier.fillMaxSize()
    )
}

// Box : 적당히 레이아웃 항목을 겹쳐서 내가 원하는 위치에 배치할 수 있음
@Composable
fun BoxExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Blue)
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Text(text = "왼쪽 위")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Green)
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(text = "중앙 위")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray)
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Text(text = "오른쪽 위")
        }

        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(text = "중앙 왼쪽")
        }
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "중앙 중앙")
        }
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = "중앙 오른쪽")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Blue)
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(text = "왼쪽 아래")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Green)
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "중앙 아래")
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray)
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "오른쪽 아래")
        }
    }
}

@Composable
fun RowTest() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly, // 가로로 동일한 간격
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Item1",
            style = TextStyle(background = Color.Blue),
            fontSize = 30.sp
        )
        Text(
            text = "Item2",
            style = TextStyle(background = Color.Red),
            fontSize = 30.sp

        )
        Text(
            text = "Item3",
            style = TextStyle(background = Color.Green),
            fontSize = 30.sp
        )
    }
}

/* 명함 만들기 */
@Composable
fun ColumnRowTest1() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Gray)
    ) {
        Text(
            text = "안녕하세요",
            color = Color.Blue,
            fontSize = 20.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            // horizontalArrangement = Arrangement.SpaceAround
            // horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "왼쪽")
            Text(text = "중앙")
            Text(text = "오른쪽")
        }

        Text(
            text = "반갑습니다",
            color = Color.Red,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ColumnRowTest2() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Cyan)
            .border(
                border = BorderStroke(5.dp, color = Color.Blue)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bok),
                contentDescription = "개복치 이미지",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }

        Text(
            text = "개발자 조은채",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 50.dp)
        )

        Text(
            text = "Android Developer",
            fontSize = 15.sp,
            modifier = Modifier.padding(10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "이메일",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "choeunchae@ajou.ac.kr",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp),
                color = Color.Blue
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "연락처",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "010-8628-3463",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp),
            )
        }

    }
}

@Composable
fun CardTest(txt: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(10.dp),
        // 그림자 효과
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = txt,
                fontSize = 30.sp,
                color = Color.Black
            )
        }
    }
}

// WebView
@Composable
fun MyWebView(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            loadUrl(url)
        }
    })
}

/* Surface : 컨텐츠를 담아놓는 컨테이너
 * A surface container using the 'background' color from the theme
 * Text / Button / Box / Surface */
@Composable
fun MySurface1() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = Color.Red,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 20.dp
    ) {
        Button(
            onClick = {  },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Green
            )
            ) {
            Text(text = "클릭해보세요")
        }
    }
}

@Composable
fun MySurface2() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray,
        border = BorderStroke(2.dp, Color.Red),
        contentColor = Color.Blue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(200.dp),
                color = Color.Red
            ) {
                Text(text = "This is Jetpack compose")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "This is Jetpack Compose Ex"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldEx() {
    Scaffold(
        topBar = {
            MyTopBar()
        },
        floatingActionButton = {
            MyFloatingActionButton()
        },
        bottomBar = {
            MyBottomBar()
        }
    ) { paddingValues ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            Text(text = "this is content")

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        title = {
            Text(text = "Main")
        },

        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        },
        actions = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Btn")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color.Red)
    )
}

@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Default.Menu, contentDescription = "Menu")
    }
}

@Composable
fun MyBottomBar() {
    BottomAppBar(
        containerColor = Color.Red
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Home, contentDescription = "home")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    }
}

/* 많은 수의 항목이나 길이를 알 수 없는 목록 표시할 때
*  Column을 사용하면 모든 항목이 구성되어서 성능 문제가 발생할 수 있음
*  따라서 지연 목록 즉 lazy column을 사용한다.*/
@Composable
fun MyLazyColumnEx() {
    val textList = listOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    )
    
    LazyColumn {
        items(textList) { item ->
            Text(
                text = item,
                fontSize = 60.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MyLazyRowEx() {
    val textList = listOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    )

    LazyRow {
        items(textList) { item ->
            Text(
                text = item,
                fontSize = 100.sp,
                modifier = Modifier.clickable {
                    println("Clicked item : $item")
                }
            )
        }
    }
}

@Composable
fun MyProcessIndicator() {
    var progress by remember { mutableStateOf(0.0f) }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (progress < 1.0f) // progress의 최대 사이즈 설정 -> 초과하지 않도록
                progress += 0.1f
        }) {
            Text(
                text = "행복게이지",
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.size(30.dp)) // 약간의 여백 주기

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.height(10.dp),
            color = Color.Red,
            trackColor = Color.Cyan
        )


        CircularProgressIndicator(
            progress = progress,
            color = Color.Red
        )
    }
}

@Composable
fun MyTextArea1() {

    Column() {
        Text(
            text = "안녕",
            fontSize = 100.sp,
            color = Color.Red
        )
        Text(
            text = "나는",
            fontSize = 100.sp,
            color = Color.Gray
        )
        Text(
            text = "누구야",
            fontSize = 100.sp,
            color = Color.Green
        )
    }
}

@Composable
fun MyTextArea2() {
    Column {
        MyTextFormat1("안녕", 100.sp, Color.Red)
        MyTextFormat1("나는", 100.sp, Color.Green)
        MyTextFormat1("누구야", 100.sp, Color.Gray)
    }
}

@Composable
fun MyTextFormat1(text : String, fontSize : TextUnit, color : Color) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color
    )
}

@Composable
fun MyTextArea3() {
    MyTextFormat2 {
        Text(
            text = "안녕",
            fontSize = 100.sp,
            color = Color.Red
        )
    }
}

@Composable
fun MyTextFormat2(content : @Composable () -> Unit) {
    Column {
        content()
        content()
        content()
        content()
        content()
    }
}

// 버튼을 클릭하면 새로운 버튼이 나오는 것
@Composable
fun MyShowHideEx1() {
    var isButtonVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
            isButtonVisible = !isButtonVisible //true면 false , false면 true 값이 들어가도록 설정
            println(isButtonVisible)
            }
        ) {
            if(isButtonVisible) {
                Text(
                    text = "숨기기",
                    fontSize = 50.sp
                )
            } else {
                Text(
                    text = "보이기",
                    fontSize = 50.sp
                )
            }
        }

        if (isButtonVisible) {
            Button(onClick = { }) {
                Text(
                    text = "짠짠짠",
                    fontSize = 50.sp
                )
            }
        }
    }
}

@Composable
fun MyShowHideEx2() {
    var switchState by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier.padding(20.dp)
    ) {
        Switch (
            checked = switchState,
            onCheckedChange = { checked ->
                switchState = checked
            }
        )

        Text(
            text = if (switchState) "ON" else "OFF",
            fontSize = 100.sp
        )

        if (switchState) {
            Button(onClick = {}) {
                Text(
                    text = "짠짠짠",
                    fontSize = 50.sp
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jetpack1Theme {
//        Column() {
//            CardTest("1")
//            CardTest("2")
//            CardTest("3")
//            CardTest("4")
//        }
//        MyWebView("https://www.daum.net/")
        MyShowHideEx2()
    }
}