package com.zrq.notalk.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.Surface
import androidx.compose.ui.window.Dialog
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.White

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val that = this
            NoTalkTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val vm: LoginViewModel = viewModel()
                    initViewModel(vm)

                    val usernameFocus = remember {
                        FocusRequester()
                    }
                    val passwordFocus = remember {
                        FocusRequester()
                    }

                    val keyboardController = LocalSoftwareKeyboardController.current
                    val focusManager = LocalFocusManager.current

                    if (vm.showLoadingDialog) {
                        Dialog(
                            onDismissRequest = { },
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    Column {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                                .padding(horizontal = 50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = "No Talk",
                                fontSize = 36.sp,
                                fontWeight = FontWeight(800),
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 40.dp),
                                color = Grey
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(usernameFocus),
                                value = vm.username,
                                onValueChange = {
                                    vm.username = it
                                },
                                singleLine = true,
                                label = {
                                    Text(text = "用户名")
                                },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                                keyboardActions = KeyboardActions {
                                    passwordFocus.requestFocus()
                                },
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(passwordFocus),
                                value = vm.password,
                                onValueChange = {
                                    vm.password = it
                                },
                                singleLine = true,
                                label = {
                                    Text(text = "密码")
                                },
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                }
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Grey,
                                    contentColor = White
                                ),
                                onClick = {
                                    vm.login()
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(4.dp),
                                    text = "登录",
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
