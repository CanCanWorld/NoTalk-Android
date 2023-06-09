package com.zrq.notalk.ui.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zrq.notalk.R
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.vm.LoginViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 9:57
 */
@Composable
fun PasswordEditText(
    vm: LoginViewModel,
) {
    BasicTextField(
        value = vm.password,
        onValueChange = {
            vm.password = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = Grey
        ),
        maxLines = 1,
        singleLine = true
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Grey, RoundedCornerShape(10.dp))
                .padding(15.dp)
        ) {
            if (vm.password == "") {
                Text(
                    text = "请输入密码",
                    color = Color.Gray,
                    fontSize = 18.sp,
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
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_error_24),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
            }
        }
    }
}