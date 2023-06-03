package com.zrq.notalk.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zrq.notalk.entity.NoteItem
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.White

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 9:54
 */
@Composable
fun HomeNoteItem(noteItem: NoteItem) {
    Column {
        Box(
            modifier = Modifier
                .padding(
                    vertical = 5.dp,
                    horizontal = 10.dp,
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(White)
                .padding(10.dp),
        ) {
            Column() {
                Text(
                    text = noteItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                )
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = noteItem.user?.username ?: "未知用户",
                        color = Grey,
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "|",
                        fontSize = 13.sp,
                        color = Color.Gray,
                    )
                    Text(
                        text = noteItem.time,
                        color = Color.Gray,
                        fontSize = 13.sp,
                    )
                }
                Text(
                    text = noteItem.content,
                    color = Grey,
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }

}