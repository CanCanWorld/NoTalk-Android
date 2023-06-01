package com.zrq.notalk.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.ui.widget.HomeWidget
import com.zrq.notalk.ui.widget.MineWidget
import com.zrq.notalk.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoTalkTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val vm: HomeViewModel = viewModel()

                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                backgroundColor = Color.White
                            ) {
                                vm.bottomItems.forEachIndexed { index, item ->
                                    val isSelect = vm.currentIndex == index
                                    BottomNavigationItem(
                                        selected = isSelect,
                                        onClick = {
                                            vm.currentIndex = index
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
                    ) {
                        when (vm.currentIndex) {
                            0 -> {
                                HomeWidget()
                            }

                            1 -> {
                                MineWidget()
                            }
                        }
                    }
                }
            }
        }
    }
}
