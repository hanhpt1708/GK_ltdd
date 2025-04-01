package com.example.thigiuaki.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.thigiuaki.Screen
import com.example.thigiuaki.viewModels.AuthState
import com.example.thigiuaki.viewModels.AuthViewModel

@Composable
fun SignUpPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (val state = authState.value) {
            is AuthState.Authenticated -> navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.SignUpPage.route) { inclusive = true }
            }
            is AuthState.Error -> Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF10272D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 36.sp, color = Color(0xFFE91E63))
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF37474F)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email", color = Color.White.copy(alpha = 0.7f)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White.copy(alpha = 0.7f),
                        focusedContainerColor = Color(0xFF37474F),
                        unfocusedContainerColor = Color(0xFF37474F),
                        focusedIndicatorColor = Color(0xFFE91E63),
                        unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Password", color = Color.White.copy(alpha = 0.7f)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White.copy(alpha = 0.7f),
                        focusedContainerColor = Color(0xFF37474F),
                        unfocusedContainerColor = Color(0xFF37474F),
                        focusedIndicatorColor = Color(0xFFE91E63),
                        unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { authViewModel.signup(email, password) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
                ) {
                    Text("Create Account", fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account? ", fontSize = 16.sp, color = Color.White)
            Text(
                text = "Login",
                fontSize = 16.sp,
                modifier = Modifier.clickable { navController.navigate(Screen.LoginPage.route) },
                color = Color(0xFFE91E63)
            )
        }
    }
}
