package com.jccsisc.irepcp.login.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jccsisc.irepcp.Constans.SPACER_10
import com.jccsisc.irepcp.Constans.SPACER_20
import com.jccsisc.irepcp.Constans.SPACER_50
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.IREPApp
import com.jccsisc.irepcp.core.MyResult
import com.jccsisc.irepcp.core.enums.StatusEnum
import com.jccsisc.irepcp.login.data.remote.model.request.LoginRequest
import com.jccsisc.irepcp.ui.theme.Gray300
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.Purple200
import com.jccsisc.irepcp.ui.theme.Purple700
import com.jccsisc.irepcp.utils.SetNavbarColor
import com.jccsisc.irepcp.utils.SpacerIrep
import com.jccsisc.irepcp.utils.components.MySimpleCustomDialog
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.ui
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
//    GlobalData.transparentNavBar(false)
    SetNavbarColor(color = Color.White)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            HeaderLogin(Modifier.weight(0.3f))
            BodyLogin(Modifier.fillMaxWidth().weight(0.6f), loginViewModel)
            FooterLogin(Modifier.fillMaxWidth().weight(0.1f))
        }
        GoToDashboard(loginViewModel)
    }
}

/**
 * Header Login
 * */
@Composable
fun HeaderLogin(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_colombia),
            contentDescription = "ic Colombia",
            modifier = Modifier
                .clip(CircleShape)
                .size(28.dp)
                .align(Alignment.Start),
            contentScale = ContentScale.Crop
        )
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SpacerIrep(size = SPACER_50)
                Icon(
                    painter = painterResource(id = R.drawable.ic_login_logo),
                    contentDescription = ""
                )
                SpacerIrep(size = SPACER_20)
                Text(
                    text = IREPApp.INSTANCE.getString(R.string.label_welcome_back),
                    fontSize = 20.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = IREPApp.INSTANCE.getString(R.string.label_lets_log_ing),
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

/**
 * Body Login
 * */
@Composable
fun BodyLogin(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isButtonEnabled: Boolean by loginViewModel.isButtonEmablled.observeAsState(initial = false)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            SpacerIrep(size = SPACER_50)
            Email(email) {
                loginViewModel.onLoginChanged(email = it, password = password)
            }
            SpacerIrep(size = SPACER_10)
            Password(password) {
                loginViewModel.onLoginChanged(email = email, password = it)
            }
            SpacerIrep(size = SPACER_10)
            RememberUser(title = IREPApp.INSTANCE.getString(R.string.label_remember_user))
            val request = LoginRequest(email, password)
            SpacerIrep(size = 40)
            LoginginButton(isButtonEnabled, loginViewModel, request)
        }
    }
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = IREPApp.INSTANCE.getString(R.string.label_user)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple700,
            unfocusedBorderColor = Gray50,
            backgroundColor = Color.White,
        )
    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = IREPApp.INSTANCE.getString(R.string.label_password)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple700,
            unfocusedBorderColor = Gray50,
            backgroundColor = Color.White,
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                R.drawable.ic_pws_visibility
            } else {
                R.drawable.ic_pws_visibility_off
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun LoginginButton(
    isButtonEnabled: Boolean,
    loginViewModel: LoginViewModel,
    request: LoginRequest
) {
    Button(
        onClick = { loginViewModel.doLogin(request) },
        enabled = isButtonEnabled,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            disabledBackgroundColor = Purple200,
            contentColor = Color.White,
            disabledContentColor = Gray300
        )
    ) {
        Text(
            text = IREPApp.INSTANCE.getString(R.string.label_log_in),
            fontSize = 16.sp,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GoToDashboard(loginViewModel: LoginViewModel) {
    val resultLogin by loginViewModel.resultLogin.observeAsState(initial = MyResult(StatusEnum.NONE, null, null))

    when(resultLogin.status) {
        StatusEnum.LOADING -> {
            MySimpleCustomDialog(true) {}
        }
        StatusEnum.SUCCESS -> {
            MySimpleCustomDialog(false) {}

        }
        StatusEnum.ERROR -> {
            MySimpleCustomDialog(false) {}
           Toast.makeText(IREPApp.INSTANCE.applicationContext, "${resultLogin.message}", Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }
}

@Composable
fun RememberUser(title: String) {

    var status by rememberSaveable { mutableStateOf(false) }

    Row {
        Checkbox(
            checked = status,
            onCheckedChange = { status = !status },
            colors = CheckboxDefaults.colors(
                checkmarkColor = Purple700,
                uncheckedColor = Gray300,
                checkedColor = Color.White
            )
        )
        Text(text = title, Modifier.padding(top = 12.dp), color = Color.Gray)
    }
}

/**
 * Footer Login
 * */
@Composable
fun FooterLogin(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = "ABINBEV_(V4.2.2)_11_13_Colombia_UAT_SIT",
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}