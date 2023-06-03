package com.zrq.notalk.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zrq.notalk.entity.MineItemEntity
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.LightGrey

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 9:53
 */
@Composable
fun MineItem(
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
            modifier = Modifier.weight(1f),
            color = Grey,
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = LightGrey,
        )
    }
}