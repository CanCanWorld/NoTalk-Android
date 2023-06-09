package com.zrq.notalk.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.zrq.notalk.R
import com.zrq.notalk.ui.item.HomeNoteItem
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.LightGrey
import com.zrq.notalk.vm.NoteViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/1 14:58
 */
@Composable
fun NotePage(
    vm: NoteViewModel
) {

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = vm.isRefresh)
    vm.loadNotes {}

    Box(
        modifier = Modifier
            .background(Grey)
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                vm.isRefresh = true
                vm.loadNotes{}
            }
        ) {
        Column {
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
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(LightGrey)
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

                items(vm.notes.size) {
                    HomeNoteItem(noteItem = vm.notes[it])
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(30.dp))
                .clickable {
                    vm.navToNote()
                }
                .shadow(elevation = 10.dp)
                .background(Grey)
                .padding(10.dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.baseline_create_24),
                contentDescription = "创建",
                tint = LightGrey
            )
        }
        }

    }
}