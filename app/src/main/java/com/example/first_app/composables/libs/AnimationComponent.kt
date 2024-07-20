package com.example.first_app.composables.libs

import androidx.compose.runtime.Composable
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable

fun AnimationComponent(fileName: String) {
    val composition = rememberLottieComposition(spec = LottieCompositionSpec.Asset("animations/$fileName"))

    LottieAnimation(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
        )
}
