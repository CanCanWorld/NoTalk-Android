package com.zrq.notalk.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.vm.MineViewModel
import com.zrq.notalk.R
import com.zrq.notalk.ui.dialog.ChangePwdDialog
import com.zrq.notalk.ui.item.MineItem
import com.zrq.notalk.ui.theme.LightGrey
import com.zrq.notalk.ui.theme.White

@Composable
fun MinePage(
    vm: MineViewModel
) {
    Box {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey)
                    .padding(start = 20.dp, bottom = 15.dp, top = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pic),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = 4.dp,
                            color = Color.White.copy(0.4f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(4.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = vm.username,
                        fontSize = 24.sp,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "uid: ${vm.uid}",
                        fontSize = 13.sp,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .background(LightGrey)
                    .padding(horizontal = 15.dp, vertical = 30.dp)
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                ) {
                    vm.mineItem1.forEach {
                        MineItem(item = it)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                ) {
                    vm.mineItem2.forEach {
                        MineItem(item = it)
                    }
                }
            }
        }

        if (vm.isShowPasswordDialog) {
            ChangePwdDialog(vm)
        }
    }
}

