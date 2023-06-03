package com.zrq.notalk.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.R
import com.zrq.notalk.entity.BottomItemEntity
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.ui.page.NotePage
import com.zrq.notalk.ui.page.MinePage
import com.zrq.notalk.ui.page.PicPage
import com.zrq.notalk.vm.NoteViewModel
import com.zrq.notalk.vm.MineViewModel
import com.zrq.notalk.vm.PicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {


    private val bottomItems = listOf(
        BottomItemEntity("文章", R.drawable.baseline_event_note_24),
        BottomItemEntity("图片", R.drawable.baseline_insert_photo_24),
        BottomItemEntity("我的", R.drawable.baseline_person_24),
    )
    private var currentIndex by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoTalkTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val homeViewModel: NoteViewModel = viewModel()
                    val mineViewModel: MineViewModel = viewModel()
                    val picViewModel: PicViewModel = viewModel()
                    initViewModel(homeViewModel)
                    initViewModel(mineViewModel)
                    initViewModel(picViewModel)

                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = Color.White
                            ) {
                                bottomItems.forEachIndexed { index, item ->
                                    val isSelect = currentIndex == index
                                    BottomNavigationItem(
                                        selected = isSelect,
                                        onClick = {
                                            currentIndex = index
                                        },
                                        icon = {
                                            Icon(
                                                modifier = Modifier
                                                    .size(20.dp),
                                                painter = painterResource(
                                                    id = item.selectIcon
                                                ),
                                                contentDescription = item.title,
                                                tint = if (isSelect) Grey else Color.Gray
                                            )
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        selectedContentColor = Grey,
                                        unselectedContentColor = Color.Gray
                                    )
                                }
                            }
                        }
                    ) { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            when (currentIndex) {
                                0 -> {
                                    NotePage(homeViewModel)
                                }

                                1->{
                                    PicPage(picViewModel)
                                }

                                2 -> {
                                    MinePage(mineViewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
