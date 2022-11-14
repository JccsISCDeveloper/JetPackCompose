package com.jccsisc.irepcp.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.jccsisc.irepcp.ui.theme.Gray300
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.Purple700
import com.jccsisc.irepcp.utils.SpacerIrep

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.ui
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        HeaderLogin(Modifier.weight(0.3f))
        BodyLogin(
            Modifier
                .fillMaxWidth()
                .weight(0.4f)
        )
        FooterLogin(
            Modifier
                .fillMaxWidth()
                .weight(0.3f)
        )
    }
}

/**
 * Header Login
 * */
@Composable
fun HeaderLogin(modifier: Modifier) {
    Column(
        modifier = Modifier
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
            Column {
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
fun BodyLogin(modifier: Modifier) {
    val email: String by rememberSaveable { mutableStateOf("") }
    val password: String by rememberSaveable { mutableStateOf("") }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            SpacerIrep(size = SPACER_50)
            Email(email)
            SpacerIrep(size = SPACER_10)
            Password(password)
            SpacerIrep(size = SPACER_10)
            RememberUser(title = IREPApp.INSTANCE.getString(R.string.label_remember_user))
        }
    }
}

@Composable
fun Email(email: String) {
    OutlinedTextField(
        value = email,
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple700,
            unfocusedBorderColor = Gray50,
            backgroundColor = Color.White,
        ),
        label = { Text(text = IREPApp.INSTANCE.getString(R.string.label_user)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18)
    )
}

@Composable
fun Password(password: String) {
    var passwordVisibility by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple700,
            unfocusedBorderColor = Gray50,
            backgroundColor = Color.White,
        ),
        label = { Text(text = IREPApp.INSTANCE.getString(R.string.label_password)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18),
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
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = IREPApp.INSTANCE.getString(R.string.label_log_in),
                fontSize = 16.sp,
                modifier = modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "ABINBEV_(V4.2.2)_11_13_Colombia_UAT_SIT",
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}