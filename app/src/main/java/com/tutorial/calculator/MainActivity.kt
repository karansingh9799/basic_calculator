package com.tutorial.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showSplashScreen by remember {
                mutableStateOf(true)
            }

            LaunchedEffect(Unit){
                delay(1700)
                showSplashScreen = false
                }
            if (showSplashScreen){
                splashScreen()
            }
            else{
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {


    var value1 by remember { mutableStateOf("") }
    var value2 by remember {  mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var InputValue by remember { mutableStateOf(false) }
    var Operator by remember {
        mutableStateOf("")
    }

    fun addNumber(num : String){
        if(!InputValue) {
            value1+=num
        }else {
            value2+=num
        }
    }
    fun operator (op : String){
        if(value1.isNotEmpty()){
            Operator = op
            InputValue = true
        }
    }
    fun calculator() {
        val n1 = value1.toDouble()
        val n2 = value2.toDouble()

        result = when (Operator){
            "+" -> (n1+n2).toString()
            "*" -> (n1*n2).toString()
            "-" -> (n1-n2).toString()
            "/" -> (n1/n2).toString()
            else -> "error"
        }

    }
    val expression = value1 + Operator + value2

    fun clearAll() {
        value1 = ""
        value2 = ""
        Operator = ""
        result = ""
        InputValue = false
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121212)),

        contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = " Calculator",
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    shadow = Shadow(
                        color = Color.Cyan,
                        blurRadius = 20f
                    ),
                    fontWeight = FontWeight.Bold,
                    fontSize = 52.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = if(result.isNotEmpty() ) result else expression ,
                    onValueChange = { },
                readOnly = true,
                label = {
                    Text(
                        text = "Enter your Value ",
                        style = TextStyle(
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                            shadow = Shadow(
                                color = Color.LightGray,
                                blurRadius = 5f
                            ),
                            color = Color.LightGray,
                            fontWeight = FontWeight.Bold,
                            )

                    ) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                )

                Spacer(modifier = Modifier.height(60.dp))
                        Row(modifier = Modifier.padding(4.dp)) {
                            Button(onClick = {addNumber("1")}) {
                                Text(text = "1")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(onClick = {addNumber("2")}) {
                                Text(text = "2")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(onClick = {addNumber("3")}) {
                                Text(text = "3")
                            }
                        }
            Spacer(modifier = Modifier.height(25.dp))
                Row(modifier = Modifier.padding(4.dp)) {
                    Button(onClick = { addNumber("4") }) {
                        Text(text = "4")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { addNumber("5") }) {
                        Text(text = "5")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { addNumber("6") }) {
                        Text(text = "6")
                    }
                }
            Spacer(modifier = Modifier.height(25.dp))
                Row (modifier = Modifier.padding(4.dp)){
                        Button(onClick = { addNumber("7") }) {
                            Text(text = "7")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { addNumber("8")  }) {
                            Text(text = "8")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { addNumber("9") }) {
                            Text(text = "9")
                        }
                    }
            Spacer(modifier = Modifier.height(25.dp))
                Row(modifier = Modifier.padding(4.dp)) {
                    Button(onClick = { operator("+") }) {
                        Text(text = "+")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { addNumber("0") }) {
                        Text(text = "0")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = {  operator("-") }) {
                        Text(text = "-")
                    }
                }
            Spacer(modifier = Modifier.height(25.dp))
                Row(modifier = Modifier.padding(4.dp)) {
                    Button(onClick = { operator("*") }) {
                        Text(text = "*")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { operator("/") }) {
                        Text(text = "/")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { calculator() }) {
                        Text(text = "=")
                    }
                }
            Spacer(modifier = Modifier.height(25.dp))

                    Button(onClick = { clearAll() } ,
                        modifier = Modifier.width(156.dp)) {
                    Text(text = "C" ,
                        style = TextStyle(fontSize = 20.sp )
                    )
            }
        }
    }
}

@Composable
fun splashScreen(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray) ,
    horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.logo ) , contentDescription = null ,
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Calculator" ,
            color  = Color.White ,
        style = TextStyle(
            fontWeight = FontWeight.Bold ,
            fontSize = 32.sp
        )
        )
        
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
        Calculator()
}
