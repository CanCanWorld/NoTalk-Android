package com.zrq.notalk.ui.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zrq.notalk.R
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.LightGrey
import com.zrq.notalk.vm.PicViewModel

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/3 10:39
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PicPage(vm: PicViewModel) {
    vm.getList()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGrey)
    ) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
            items(vm.images.size) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    model = vm.images[it].path,
                    contentDescription = null,
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(30.dp))
                .clickable {
                    vm.jumpToPhoto()
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