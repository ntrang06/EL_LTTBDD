package com.example.myapplication.ui.screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

private val Orange = Color(0xFFF47B43)
private val DarkOrange = Color(0xFFE75E4E)

private data class PermissionInfo(
    val icon: String,
    val title: String,
    val description: String,
    val buttonText: String
)

@Composable
fun PermissionScreen(
    onFinished: () -> Unit
) {
    var step by rememberSaveable { mutableIntStateOf(0) }

    val permissions = listOf(
        PermissionInfo(
            "📍",
            "Location",
            "Cho phép ứng dụng truy cập vị trí của bạn khi sử dụng.",
            "Cho phép"
        ),
        PermissionInfo(
            "🔔",
            "Notification",
            "Bật thông báo để nhận cập nhật và nhắc nhở.",
            "Bật thông báo"
        ),
        PermissionInfo(
            "📷",
            "Camera",
            "Cho phép ứng dụng sử dụng camera để quét mã QR.",
            "Bật camera"
        )
    )

    fun nextStep() {
        if (step < 2) {
            step++
        } else {
            onFinished()
        }
    }

    val singlePermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            // Dù đồng ý hay từ chối đều chuyển bước.
            nextStep()
        }

    val locationLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // Android trả về kết quả cấp quyền vị trí.
            nextStep()
        }

    fun requestCurrentPermission() {
        when (step) {
            0 -> {
                locationLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

            1 -> {
                if (Build.VERSION.SDK_INT >= 33) {
                    singlePermissionLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                } else {
                    nextStep()
                }
            }

            2 -> {
                singlePermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

    val info = permissions[step]

    // Màn hình nền giả lập trang đăng nhập.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE9EFE9),
                        Color(0xFFFFE2C4),
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))

            Text(
                "WELCOME",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkOrange
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "Khám phá ứng dụng của chúng tôi",
                fontSize = 15.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange
                )
            ) {
                Text("Đăng nhập")
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Tiếp tục không cần tài khoản")
            }

            Spacer(Modifier.height(36.dp))
        }

        // Dialog tự làm tối nền phía sau.
        Dialog(
            onDismissRequest = {}
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 28.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = info.icon,
                        fontSize = 65.sp
                    )

                    Spacer(Modifier.height(25.dp))

                    Text(
                        text = info.title,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202330)
                    )

                    Spacer(Modifier.height(14.dp))

                    Text(
                        text = info.description,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )

                    Spacer(Modifier.height(34.dp))

                    Button(
                        onClick = { requestCurrentPermission() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange
                        )
                    ) {
                        Text(
                            text = info.buttonText,
                            color = Color.White
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = { nextStep() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Orange
                        )
                    ) {
                        Text("Bỏ qua")
                    }
                }
            }
        }
    }
}
