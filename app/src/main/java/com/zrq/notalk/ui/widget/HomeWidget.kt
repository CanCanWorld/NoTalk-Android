package com.zrq.notalk.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.R
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.LightGrey
import com.zrq.notalk.ui.theme.White
import com.zrq.notalk.vm.HomeViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/1 14:58
 */
@Composable
fun HomeWidget(
    vm: HomeViewModel = viewModel()
) {

    vm.loadNotes {}

    Column(
        modifier = Modifier
            .background(Grey)
    ) {
        BasicTextField(
            value = vm.input,
            onValueChange = {
                vm.input = it
            },
            modifier = Modifier
                .fillMaxWidth()
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .background(LightGrey)
                    .clip(RoundedCornerShape(3.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    colorFilter = ColorFilter.tint(Grey)
                )
                innerTextField()
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(LightGrey)
        ) {

            item {

            }

            items(vm.notes.size) {
                val noteItem = vm.notes[it]
                Column {
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
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
                                    text = noteItem.user.username,
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
        }
    }
}