package com.example.jetpack1

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpack1.ui.theme.Jetpack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack1Theme {
                ColumnRowTest2()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jetpack1Theme {
        ColumnRowTest2()
    }
}