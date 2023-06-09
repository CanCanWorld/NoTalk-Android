package com.zrq.notalk.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.zrq.notalk.entity.BottomItemEntity
import com.zrq.notalk.ui.page.MinePage
import com.zrq.notalk.ui.page.NotePage
import com.zrq.notalk.ui.page.PicPage
import com.zrq.notalk.ui.theme.Grey
import com.zrq.notalk.ui.theme.NoTalkTheme
import com.zrq.notalk.utils.Constants.REQUEST_CODE_IMAGE
import com.zrq.notalk.vm.MineViewModel
import com.zrq.notalk.vm.NoteViewModel
import com.zrq.notalk.vm.PicViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.util.Date
import com.zrq.notalk.R


@AndroidEntryPoint
class HomeActivity : BaseActivity() {


    private val bottomItems = listOf(
        BottomItemEntity("文章", R.drawable.baseline_event_note_24),
        BottomItemEntity("图片", R.drawable.baseline_insert_photo_24),
        BottomItemEntity("我的", R.drawable.baseline_person_24),
    )
    private var currentIndex by mutableStateOf(0)
    lateinit var homeViewModel: NoteViewModel
    lateinit var mineViewModel: MineViewModel
    lateinit var picViewModel: PicViewModel

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NoTalkTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 110)
                    homeViewModel = viewModel()
                    mineViewModel = viewModel()
                    picViewModel = viewModel()
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

                                1 -> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_IMAGE) {
                data?.data?.let { image ->
                    val filePathColumns = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor: Cursor? = contentResolver.query(image, filePathColumns, null, null, null)
                    cursor?.let { c ->
                        c.moveToFirst()
                        val columnIndex: Int = c.getColumnIndex(filePathColumns[0])
                        val imagePath: String? = c.getString(columnIndex)
                        Log.d(TAG, "imagePath: $imagePath")
                        imagePath?.let {
                            val saveBitmapToFile = saveBitmapToFile(this, imagePath, "test${Date().time}.png")
                            picViewModel.uploadImage(saveBitmapToFile)
                        }
                        c.close()
                    }
                }
            }
        }
    }

    private fun saveBitmapToFile(context: Context, imagePath: String, filename: String): File {
//        val options = BitmapFactory.Options()
//        options.inJustDecodeBounds = true
//        BitmapFactory.decodeFile(imagePath, options)
//        options.inSampleSize = 4
//        options.inJustDecodeBounds = false

        val bitmap = BitmapFactory.decodeFile(imagePath)
        val file = File(context.externalCacheDir, filename)
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        out.flush()
        out.close()
        return file
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
