package com.example.jetpack1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack1.ui.theme.Jetpack1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack1Theme {
                SimpleCounterBtn()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Jetpack1Theme {
        MyBtn()
    }
}