package com.zrq.notalk.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zrq.notalk.R
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.White
import com.zrq.notalk.vm.MineViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 9:57
 */
@Composable
fun ChangePwdDialog(vm: MineViewModel) {
    Dialog(
        onDismissRequest = { vm.isShowPasswordDialog = !vm.isShowPasswordDialog },
    ) {
        Column(
            modifier = Modifier
                .width(250.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    value = vm.passwordInput,
                    onValueChange = {
                        vm.passwordInput = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        color = Grey
                    )
                ) { innerTextField ->
                    Box {
                        if (vm.passwordInput == "") {
                            Text(
                                text = "请输入新密码",
                                color = Color.Gray,
                                fontSize = 15.sp,
                            )
                        }
                        innerTextField()
                        if (vm.isPasswordError) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "密码需要6位以上",
                                    color = Color.Red,
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_error_24),
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier
                                        .size(12.dp)
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey)
                    .clickable { vm.changePassword() }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "提交",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}