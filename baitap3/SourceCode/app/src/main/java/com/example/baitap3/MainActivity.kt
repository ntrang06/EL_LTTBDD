package com.example.baitap3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.baitap3.ui.theme.Baitap3Theme

private val AppBlue = Color(0xFF2196F3)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Baitap3Theme {
                UIComponentsApp()
            }
        }
    }
}

@Composable
fun UIComponentsApp() {
    var screen by rememberSaveable { mutableStateOf("welcome") }
    val listScrollState = rememberScrollState()

    fun goBack() {
        screen = when (screen) {
            "text", "image", "icon", "textfield", "password", "switch", "slider", "rangeSlider", "datePicker", "timePicker", "button", "iconButton", "fab", "checkbox", "radioButton" -> "list"
            else -> "welcome"
        }
    }

    BackHandler(enabled = screen != "welcome") {
        goBack()
    }

    when (screen) {
        "welcome" -> WelcomeScreen(
            onReadyClick = { screen = "list" }
        )

        "list" -> ComponentsListScreen(
            scrollState = listScrollState,
            onComponentClick = { title ->
                screen = when (title) {
                    "Text" -> "text"
                    "Image" -> "image"
                    "Icon" -> "icon"
                    "TextField" -> "textfield"
                    "PasswordField" -> "password"
                    "Switch" -> "switch"
                    "Slider" -> "slider"
                    "RangeSlider" -> "rangeSlider"
                    "DatePicker" -> "datePicker"
                    "TimePicker" -> "timePicker"
                    "Button" -> "button"
                    "IconButton" -> "iconButton"
                    "FloatingActionButton" -> "fab"
                    "Checkbox" -> "checkbox"
                    "RadioButton" -> "radioButton"
                    else -> screen
                }
            }
        )

        "text" -> TextDetailScreen(
            onBackClick = { goBack() }
        )

        "image" -> ImageDetailScreen(
            onBackClick = { goBack() }
        )

        "icon" -> IconDetailScreen(
            onBackClick = { goBack() }
        )

        "textfield" -> TextFieldDetailScreen(
            onBackClick = { goBack() }
        )
        "password" -> PasswordFieldDetailScreen(
            onBackClick = { goBack() }
        )
        "switch" -> SwitchDetailScreen(
            onBackClick = { goBack() }
        )
        "slider" -> SliderDetailScreen(
            onBackClick = { goBack() }
        )
        "rangeSlider" -> RangeSliderDetailScreen(
            onBackClick = { goBack() }
        )
        "datePicker" -> DatePickerDetailScreen(
            onBackClick = { goBack() }
        )
        "timePicker" -> TimePickerDetailScreen(
            onBackClick = { goBack() }
        )
        "button" -> ButtonDetailScreen(
            onBackClick = { goBack() }
        )
        "iconButton" -> IconButtonDetailScreen(
            onBackClick = { goBack() }
        )
        "fab" -> FloatingActionButtonDetailScreen(
            onBackClick = { goBack() }
        )
        "checkbox" -> CheckboxDetailScreen(
            onBackClick = { goBack() }
        )
        "radioButton" -> RadioButtonDetailScreen(
            onBackClick = { goBack() }
        )
    }
}

@Composable
fun WelcomeScreen(onReadyClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Jetpack Compose",
                modifier = Modifier.size(200.dp)
            )

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Jetpack Compose",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Jetpack Compose is a modern UI toolkit " +
                        "for building native Android applications " +
                        "using a declarative programming approach.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }

        Button(
            onClick = onReadyClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppBlue,
                contentColor = Color.White
            )
        ) {
            Text("I'm ready", fontSize = 18.sp)
        }
    }
}

data class UIComponent(
    val title: String,
    val description: String
)

val componentGroups = linkedMapOf(
    "Display" to listOf(
        UIComponent("Text", "Hiển thị văn bản với nhiều kiểu định dạng"),
        UIComponent("Image", "Hiển thị hình ảnh"),
        UIComponent("Icon", "Hiển thị biểu tượng")
    ),
    "Input" to listOf(
        UIComponent("TextField", "Nhập và cập nhật văn bản"),
        UIComponent("PasswordField", "Nhập mật khẩu có che ký tự"),
        UIComponent("Switch", "Bật hoặc tắt một tùy chọn"),
        UIComponent("Slider", "Chọn một giá trị bằng thanh trượt"),
        UIComponent("RangeSlider", "Chọn một khoảng giá trị"),
        UIComponent("DatePicker", "Chọn ngày, tháng, năm"),
        UIComponent("TimePicker", "Chọn giờ và phút")
    ),
    "Buttons & Selection" to listOf(
        UIComponent("Button", "Nút bấm thực hiện một hành động"),
        UIComponent("IconButton", "Nút bấm sử dụng biểu tượng"),
        UIComponent("FloatingActionButton", "Nút hành động nổi"),
        UIComponent("Checkbox", "Chọn hoặc bỏ chọn nhiều tùy chọn"),
        UIComponent("RadioButton", "Chọn một tùy chọn trong một nhóm"),
        UIComponent("FilterChip", "Chọn bộ lọc bằng thẻ nhỏ"),
        UIComponent("DropdownMenu", "Chọn thao tác từ menu thả xuống")
    ),
    "Layout" to listOf(
        UIComponent("Column", "Sắp xếp các phần tử theo chiều dọc"),
        UIComponent("Row", "Sắp xếp các phần tử theo chiều ngang"),
        UIComponent("Box", "Đặt các phần tử chồng lên nhau"),
        UIComponent("Spacer", "Tạo khoảng trống giữa các phần tử"),
        UIComponent("FlowRow", "Tự xuống hàng khi không đủ chiều rộng")
    ),
    "Content" to listOf(
        UIComponent("Card", "Hiển thị nội dung trong một thẻ"),
        UIComponent("LazyColumn", "Danh sách cuộn theo chiều dọc"),
        UIComponent("LazyRow", "Danh sách cuộn theo chiều ngang"),
        UIComponent("LazyVerticalGrid", "Hiển thị danh sách dạng lưới"),
        UIComponent("HorizontalDivider", "Đường phân cách nội dung"),
        UIComponent("Badge", "Hiển thị số lượng hoặc trạng thái nhỏ")
    ),
    "Feedback" to listOf(
        UIComponent("AlertDialog", "Hộp thoại thông báo hoặc xác nhận"),
        UIComponent("Snackbar", "Thông báo ngắn kèm thao tác"),
        UIComponent("CircularProgressIndicator", "Tiến trình dạng vòng tròn"),
        UIComponent("LinearProgressIndicator", "Tiến trình dạng thanh ngang"),
        UIComponent("ModalBottomSheet", "Bảng nội dung xuất hiện từ dưới")
    ),
    "Navigation" to listOf(
        UIComponent("TopAppBar", "Thanh tiêu đề và các thao tác"),
        UIComponent("NavigationBar", "Thanh điều hướng phía dưới"),
        UIComponent("TabRow", "Chuyển nội dung giữa các tab")
    )
)

@Composable
fun ComponentsListScreen(
    scrollState: ScrollState,
    onComponentClick: (String) -> Unit
) {
    val availableComponents = setOf(
        "Text",
        "Image",
        "Icon",
        "TextField",
        "PasswordField",
        "Switch",
        "Slider",
        "RangeSlider",
        "DatePicker",
        "TimePicker",
        "Button",
        "IconButton",
        "FloatingActionButton",
        "Checkbox",
        "RadioButton"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            color = AppBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        componentGroups.forEach { (groupName, components) ->
            ComponentSectionTitle(groupName)

            components.forEach { component ->
                ComponentItem(
                    title = component.title,
                    description = component.description,
                    onClick = if (
                        component.title in availableComponents
                    ) {
                        { onComponentClick(component.title) }
                    } else {
                        null
                    }
                )
            }
        }
    }
}

@Composable
fun ComponentSectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun ComponentItem(
    title: String,
    description: String,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFBBDEFB))
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            )
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

// Dùng chung thanh tiêu đề cho các màn hình chi tiết.
@Composable
fun DetailHeader(
    title: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        TextButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 4.dp)
        ) {
            Text(
                text = "‹",
                fontSize = 36.sp,
                color = AppBlue
            )
        }

        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = AppBlue
        )
    }
}

@Composable
fun TextDetailScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader("Text Detail", onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("The ")

                    withStyle(
                        SpanStyle(
                            textDecoration = TextDecoration.LineThrough
                        )
                    ) {
                        append("quick")
                    }

                    append(" ")

                    withStyle(
                        SpanStyle(
                            color = Color(0xFFB58126),
                            fontSize = 40.sp
                        )
                    ) {
                        append("Brown")
                    }

                    append("\nfox ")

                    withStyle(SpanStyle(letterSpacing = 5.sp)) {
                        append("jumps")
                    }

                    append(" ")

                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("over")
                    }

                    append("\n")

                    withStyle(
                        SpanStyle(
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("the")
                    }

                    append(" ")

                    withStyle(
                        SpanStyle(fontStyle = FontStyle.Italic)
                    ) {
                        append("lazy")
                    }

                    append(" dog.")
                },
                fontSize = 30.sp,
                lineHeight = 54.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFEEEEEE))
            )
        }
    }
}

@Composable
fun ImageDetailScreen(onBackClick: () -> Unit) {
    val imageUrl = "https://s.cmx-cdn.com/giaothongvantaitphcm.edu.vn/wp-content/uploads/2024/06/ky-niem-36-nam-thanh-lap-truong-dai-hoc-giao-thong-van-tai-tphcm-560px.jpg"

    // Đổi khóa để tạo lại bộ tải ảnh khi bấm thử lại.
    var retryCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        DetailHeader("Images", onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            key(retryCount) {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    contentDescription = "Ảnh phong cảnh từ Internet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF2F2F2)),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = AppBlue
                            )
                        }
                    },
                    error = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Không tải được ảnh Internet",
                                color = Color.DarkGray
                            )

                            TextButton(
                                onClick = { retryCount++ }
                            ) {
                                Text("Thử lại")
                            }
                        }
                    }
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = imageUrl,
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )

            Spacer(Modifier.height(28.dp))

            Image(
                painter = painterResource(R.drawable.uth),
                contentDescription = "Ảnh UTH lưu trong ứng dụng",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "In app",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}