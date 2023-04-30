package com.kitchenpal.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kitchenpal.R
import com.kitchenpal.ui.component.EmailInputType
import com.kitchenpal.ui.component.PasswordInputType
import com.kitchenpal.ui.component.RoundUserInputField
import com.kitchenpal.ui.theme.KitchenPalTheme

fun Modifier.scrollEnabled(
    enabled: Boolean,
) = nestedScroll(
    connection = object : NestedScrollConnection {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset = if (enabled) Offset.Zero else available
    }
)

@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isImeVisible)
}

@Composable
internal fun LoginScreen(
    viewModel: AuthenticationViewModel,
    navController: NavHostController?
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ConstraintLayout {
                val (image, loginForm) = createRefs()
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(image) {
                            top.linkTo(loginForm.top)
                            bottom.linkTo(loginForm.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                    HeaderView()
                }

                Card(
                    shape = RoundedCornerShape(topStart = 52.dp, topEnd = 52.dp),
                    colors = CardDefaults.cardColors(containerColor = KitchenPalTheme.colors.surface),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                        .constrainAs(loginForm) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                            .background(Color.Transparent)
                    ) {
                        val loginText = "Log in to your account."

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 20.dp),
                            text = loginText,
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp,
                        )
                        Text(
                            text = "Email Address",
                            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                        )

                        RoundUserInputField(modifier = Modifier.fillMaxWidth(),
                            value = viewModel.emailAddressState.value,
                            placeholderText = "Email Address",
                            inputType = EmailInputType(imeAction = ImeAction.Next),
                            onValueChange = {
                                viewModel.onEmailAddressChanged(it)
                            }
                        )

                        Text(
                            text = "Password",
                            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                        )

                        RoundUserInputField(modifier = Modifier.fillMaxWidth(),
                            value = viewModel.passwordState.value,
                            isPassword = true,
                            inputType = PasswordInputType(imeAction = ImeAction.Done),
                            placeholderText = "Password",
                            onValueChange = {
                                viewModel.onPasswordChanged(it)
                            }
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            text = "Forgot Password",
                            textAlign = TextAlign.End,
                        )
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .padding(top = 30.dp, bottom = 34.dp)
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                                text = "Login",
                                color = Color.White,
                            )
                        }

                        val signInText = "Don't have an account? Sign In"

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = signInText,
                            style = TextStyle(
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderView() {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = ImageBitmap.imageResource(id = R.drawable.ic_kitchen_pal),
        contentScale = ContentScale.FillWidth,
        contentDescription = "header_view_login_bg"
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(hiltViewModel(), null)
}