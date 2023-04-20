package com.example.mediaplayer.android.view.signup_and_login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaplayer.android.navigation.bottom_nav_graph.SetUpBottomNavGraph
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun SignUp(navController: NavController) {
    val context = LocalContext.current
    val phoneNumber = remember { mutableStateOf("") }
    val otp = remember { mutableStateOf("") }
    val verificationID = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SignUp")

        Text(
            modifier = Modifier.clickable { "login_screen" }, text = "Login"
        )

        OutlinedTextField(value = phoneNumber.value, leadingIcon = {

        }, maxLines = 1, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ), label = {
            Text(text = "Mob : +91-XXXXX XXXXX")
        }, onValueChange = { phoneNumber.value = it })

        Button(onClick = {


        }) {
            Text(text = "Submit")

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = otp.value, leadingIcon = {

        }, maxLines = 1, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ), label = {
            Text(text = "Enter your OTP")
        }, onValueChange = { otp.value = it },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
        //    navController.navigate("")

        }) {
            Text(text = "Next Step")

        }

        Spacer(modifier = Modifier.height(5.dp))


    }


}

