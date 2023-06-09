package com.zrq.notalk.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.vm.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Description:
 * @author zhangruiqian
 * @date 2023/6/9 15:09
 */
@AndroidEntryPoint
class ImageDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val path = intent.getStringExtra("image_path")

        setContent {
            val vm: ImageViewModel = viewModel()
            vm.imagePath = path ?: ""

            NoTalkTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop,
                        model = vm.imagePath,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}