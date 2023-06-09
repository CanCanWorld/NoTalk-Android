package com.zrq.notalk.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.R
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.ui.theme.White
import com.zrq.notalk.vm.WriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoTalkTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val vm: WriteViewModel = viewModel()
                    initViewModel(vm)
                    Column(
                        modifier = Modifier
                            .background(White)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Grey)
                                .padding(horizontal = 10.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                                contentDescription = null,
                                tint = White,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        vm.activity.finish()
                                    }
                            )

                            Box(
                                modifier = Modifier
                                    .background(Grey)
                                    .clickable {
                                        vm.submit()
                                    }
                                    .padding(
                                        horizontal = 20.dp,
                                        vertical = 4.dp
                                    )
                            ) {
                                Text(
                                    text = "提交",
                                    color = White,
                                    fontSize = 16.sp
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(White)
                                .padding(10.dp),
                        ) {
                            BasicTextField(
                                value = vm.title,
                                onValueChange = {
                                    vm.title = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                ),

                                ) { innerTextField ->
                                Box {
                                    if (vm.title == "") {
                                        Text(
                                            text = "请输入完整标题",
                                            color = Color.Black,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }
                                    innerTextField()
                                    if (vm.titleError) {
                                        Row(
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "标题需大于2字",
                                                color = Color.Red,
                                                fontSize = 14.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_error_24),
                                                contentDescription = null,
                                                tint = Color.Red,
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(White)
                                .padding(10.dp),
                        ) {
                            BasicTextField(
                                value = vm.content,
                                onValueChange = {
                                    vm.content = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    color = Grey
                                )
                            ) { innerTextField ->
                                Box {
                                    if (vm.content == "") {
                                        Text(
                                            text = "来吧，尽情发挥吧~",
                                            color = Grey,
                                            fontSize = 15.sp,
                                        )
                                    }
                                    innerTextField()
                                    if (vm.contentError) {
                                        Row(
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "内容需大于5字",
                                                color = Color.Red,
                                                fontSize = 14.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_error_24),
                                                contentDescription = null,
                                                tint = Color.Red,
                                                modifier = Modifier
                                                    .size(20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
