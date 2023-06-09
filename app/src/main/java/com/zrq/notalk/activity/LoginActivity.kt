package com.zrq.notalk.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.Surface
import com.zrq.notalk.ui.dialog.LoadingDialog
import com.zrq.notalk.ui.dialog.PasswordEditText
import com.zrq.notalk.ui.dialog.UsernameEditText
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.White

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoTalkTheme {

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val vm: LoginViewModel = viewModel()
                    initViewModel(vm)

                    if (vm.showLoadingDialog) {
                        LoadingDialog()
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

                            UsernameEditText(
                                vm = vm,
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            PasswordEditText(
                                vm = vm,
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

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Grey,
                                    contentColor = White
                                ),
                                onClick = {
                                    vm.register()
                                }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(4.dp),
                                    text = "注册",
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
