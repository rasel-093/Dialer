package com.example.dialer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dialer.ui.theme.DialerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 101)
        }
        val context = this
        setContent {
            DialerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(context)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    context: Context
) {
    var number by rememberSaveable {
        mutableStateOf("")
    }
    Column(
       // modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.8f).padding(10.dp)
        ) {
            NumberBox(number)
            Button(onClick = { number = ""  }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "delete" )
            }
        }
        DialPadScreen {
            number += it
            println(number)
        }
        Button(onClick = {
            makePhoneCall(context, number)
        }) {
            Icon(imageVector = Icons.Default.Call, contentDescription = "call" )
        }
    }
}

fun makePhoneCall(context: Context, number: String) {
//    val intent = Intent(context, Intent.ACTION_CALL::class.java)
//    intent.setData(Uri.parse("tel:$number"))
    val intent = Intent(Intent.ACTION_CALL).apply {
        data = Uri.parse("tel:$number")
    }
    context.startActivity(intent)
}

@Composable
fun NumberBox(
    number: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(10.dp)
    ) {
        Text(
            text = number,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = Color.DarkGray,
            maxLines = 1
        )
    }
}
