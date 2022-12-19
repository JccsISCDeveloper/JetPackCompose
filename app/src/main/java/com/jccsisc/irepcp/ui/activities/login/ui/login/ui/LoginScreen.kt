package com.jccsisc.irepcp.ui.activities.login.ui.login.ui

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.core.MyResult
import com.jccsisc.irepcp.core.constants.Constants.SPACER_20
import com.jccsisc.irepcp.core.enums.StatusEnum
import com.jccsisc.irepcp.ui.activities.home.MainActivity
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ShowLottie
import com.jccsisc.irepcp.ui.activities.login.ui.login.data.remote.model.request.LoginRequest
import com.jccsisc.irepcp.ui.theme.Gray300
import com.jccsisc.irepcp.ui.theme.Gray50
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.utils.GlobalData.onLoginClick
import com.jccsisc.irepcp.utils.SetNavbarColor
import com.jccsisc.irepcp.utils.SpacerApp
import com.jccsisc.irepcp.utils.components.loadings.SimpleCircularProgressDialog

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.ui
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
//    GlobalData.transparentNavBar(false)
    SetNavbarColor(color = Color.White)

    val mContext = LocalContext.current as Activity

    var auth by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_8)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderLogin(
                Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            )
            BodyLogin(
                loginViewModel,
                onNavigationToDashboard = {
                    mContext.startActivity(Intent(mContext, MainActivity::class.java))
                    mContext.finish()
                },
                Modifier.weight(0.6f)
            )
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clickable { onLoginClick() },
                contentAlignment = Center
            ) {
                ShowLottie(lottie = R.raw.huella)
            }
            FooterLogin(
                Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            )
        }
//        GoToDashboard(loginViewModel, onNavigationToDashboard)
    }
    onLoginClick()
}


/**
 * Header Login
 * */
@Composable
private fun HeaderLogin(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier, horizontalAlignment = CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_colombia),
            contentDescription = "ic Colombia",
            modifier = Modifier
                .clip(CircleShape)
                .size(dimensionResource(id = R.dimen.padding_28))
                .align(Alignment.Start),
            contentScale = ContentScale.Crop
        )
        Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_login_logo),
                    contentDescription = ""
                )
                SpacerApp(size = SPACER_20)
                Text(
                    text = stringResource(id = R.string.label_welcome_back),
                    style = MaterialTheme.typography.h6,
                    color = Color.Blue
                )
                Text(
                    text = stringResource(id = R.string.label_lets_log_ing),
                    style = MaterialTheme.typography.subtitle2,
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
private fun BodyLogin(
    loginViewModel: LoginViewModel,
    onNavigationToDashboard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isButtonEnabled: Boolean by loginViewModel.isButtonEmablled.observeAsState(initial = false)
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Email(email) {
                loginViewModel.onLoginChanged(email = it, password = password)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Password(password) {
                loginViewModel.onLoginChanged(email = email, password = it)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_6))
            )
            RememberUser(title = stringResource(id = R.string.label_remember_user))
            val request = LoginRequest("email", "password")
            Spacer(modifier = Modifier.fillMaxHeight(0.3f))
            LoginginButton(
                true,
                loginViewModel,
                request,
                onNavigationToDashboard = { onNavigationToDashboard() })
/*            PressIconButton(
                onClick = {},
                icon = {
                    Icon(painter = painterResource(id = R.drawable.ic_pws_visibility_off), contentDescription = "")
                },
                text = { Text(text = "Add to cart") },
                modifier = modifier
            )*/
        }
    }
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = stringResource(id = R.string.label_user),
                style = MaterialTheme.typography.body1
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
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
        label = {
            Text(
                text = stringResource(id = R.string.label_password),
                style = MaterialTheme.typography.body1
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(18),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
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
                    modifier = Modifier.size(dimensionResource(id = R.dimen.padding_26))
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
    request: LoginRequest,
    onNavigationToDashboard: () -> Unit
) {
    Button(
        onClick = {
            loginViewModel.doLogin(request)
            onNavigationToDashboard()
        },
        enabled = isButtonEnabled,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_10)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PrimaryColor,
            disabledBackgroundColor = PrimaryDarkColor,
            contentColor = Color.White,
            disabledContentColor = Gray300
        )
    ) {
        Text(
            text = stringResource(id = R.string.label_log_in),
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_10)),
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center
        )
    }
}

/*@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick, modifier = modifier,
        interactionSource = interactionSource
    ) {
        AnimatedVisibility(visible = isPressed) {
            if (isPressed) {
                Row {
                    icon()
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }
            }
        }
        text()
    }
}*/

@Composable
fun GoToDashboard(loginViewModel: LoginViewModel, onNavigationToDashboard: () -> Unit) {
    val resultLogin by loginViewModel.resultLogin.observeAsState(
        initial = MyResult(StatusEnum.NONE, null, null)
    )

    when (resultLogin.status) {
        StatusEnum.LOADING -> {
            SimpleCircularProgressDialog()
        }
        StatusEnum.SUCCESS -> {
            //todo ir a la siguiente vista
            onNavigationToDashboard()
        }
        StatusEnum.ERROR -> {
            Toast.makeText(LocalContext.current, "${resultLogin.message}", Toast.LENGTH_SHORT)
                .show()
            onNavigationToDashboard()
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
                checkmarkColor = Color.White,
                uncheckedColor = Gray300,
                checkedColor = PrimaryColor
            )
        )
        Text(
            text = title,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_14)),
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
    }
}

/**
 * Footer Login
 * */
@Composable
private fun FooterLogin(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = "ABINBEV_(V4.2.2)_11_13_Colombia_UAT_SIT",
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}