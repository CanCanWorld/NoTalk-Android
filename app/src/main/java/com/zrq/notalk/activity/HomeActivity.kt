package com.zrq.notalk.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.LightGrey
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.ui.theme.White
import com.zrq.notalk.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val that = this
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val vm: HomeViewModel = viewModel()
                vm.loadNotes {}

                Column {
                    TextField(
                        value = vm.input,
                        onValueChange = {
                            vm.input = it
                        },
                        placeholder = {
                            Text("请输入内容")
                        },
                        modifier = Modifier.padding(8.dp),
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Grey,
                            containerColor = Color.White
                        )
                    )

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
                                                modifier = Modifier.padding(horizontal = 4.dp),
                                                text = "|",
                                                fontSize = 13.sp,
                                                color = Color.Gray,
                                            )
                                            Text(
                                                text = noteItem.time,
                                                color = Color.Gray,
                                            )
                                        }
                                        Text(
                                            text = noteItem.content,
                                            color = Grey,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
