package bensalcie.samples.allaboutcompose_android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bensalcie.samples.allaboutcompose_android.R

private val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.W300),
    Font(R.font.quicksand_regular, FontWeight.W400),
    Font(R.font.quicksand_medium, FontWeight.W500),
    Font(R.font.quicksand_semibold, FontWeight.W600),
    Font(R.font.quicksand_bold, FontWeight.W700),


    )

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

            bodyMedium = TextStyle(
            fontFamily = QuickSand,
    fontWeight = FontWeight.W400,
),
    bodySmall = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W300,
    ),
            titleMedium = TextStyle(
            fontFamily = QuickSand,
    fontWeight = FontWeight.W700,
)


/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)



