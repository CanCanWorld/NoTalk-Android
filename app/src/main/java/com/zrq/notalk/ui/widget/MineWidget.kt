package com.zrq.notalk.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.vm.MineWidgetVM
import com.zrq.notalk.R
import com.zrq.notalk.entity.MineItemEntity
import com.zrq.notalk.ui.theme.LightGrey

@Composable
fun MineWidget(
    vm: MineWidgetVM = viewModel()
) {
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
                    text = "用户名",
                    fontSize = 20.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "188888888",
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
                    MineItemWidget(item = it)
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
                    MineItemWidget(item = it)
                }
            }
        }
    }
}

@Composable
fun MineItemWidget(
    item: MineItemEntity
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                item.onClick()
            }
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = Grey
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = item.title,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = LightGrey,
        )
    }
}