package com.example.first_app.composables.qrCodeScanner

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import android.graphics.ImageFormat
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val supportedImageFormats = listOf(
        ImageFormat.DEPTH16,
        ImageFormat.DEPTH_POINT_CLOUD,
        ImageFormat.DEPTH_JPEG,
        ImageFormat.FLEX_RGBA_8888,
        ImageFormat.FLEX_RGB_888,
        ImageFormat.JPEG,
        ImageFormat.NV16,
        ImageFormat.NV21,
        ImageFormat.PRIVATE,
        ImageFormat.RAW10,
        ImageFormat.RAW12,
        ImageFormat.RAW_PRIVATE,
        ImageFormat.RAW_SENSOR,
        ImageFormat.RGB_565,
        ImageFormat.UNKNOWN,
        ImageFormat.Y8,
        ImageFormat.YUV_420_888,

    )

    override fun analyze(image: ImageProxy) {
        if (image.format in supportedImageFormats) {
            val bytes = image.planes.first().buffer.toByteArray()

            val rotationDegrees = image.imageInfo.rotationDegrees
            val source = PlanarYUVLuminanceSource(
                bytes,
                image.width,
                image.height,
                0,
                0,
                image.width,
                image.height,
                false
            )

            val binaryBmp = BinaryBitmap(HybridBinarizer(source))
            try {
                val result = MultiFormatReader().apply {
                    setHints(
                        mapOf(
                            DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                                BarcodeFormat.QR_CODE
                            )
                        )
                    )
                }.decode(binaryBmp)
                Log.d("QrCodeAnalyzer", "QR Code detected: ${result.text}")
                onQrCodeScanned(result.text)
            } catch (e: Exception) {
              //  Log.e("QrCodeAnalyzer", "QR Code not detected: ${e.message}")
            }
        } else {
            Log.d("QrCodeAnalyzer", "Unsupported image format: ${image.format}")
        }
        image.close()
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }
}
