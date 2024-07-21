package com.example.first_app.composables.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.first_app.QrTypes
import com.example.first_app.Routes
import com.example.first_app.composables.qrCodeScanner.QrCodeAnalyzer

@Composable
fun ScannerScreen(navController: NavController,type: QrTypes = QrTypes.CHECK) {
    //val navBackStackEntry = navController.currentBackStackEntryAsState().value

    //val typeString = navBackStackEntry?.arguments?.getString("type")
    //val type = if (typeString != null) QrTypes.valueOf(typeString) else QrTypes.CHECK


    val code = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val hasCamPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission.value = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCamPermission.value) {
            AndroidView(factory = { context ->
                val previewView = PreviewView(context)
                val preview = Preview.Builder().build()
                val selector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                preview.setSurfaceProvider(previewView.surfaceProvider)
                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(
                        previewView.width,
                        previewView.height
                    ))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    QrCodeAnalyzer { result ->
                        code.value = result
                        val qrBody = result
                        Log.d("ScannerScreen", "Scanned code: $result")
                        if (type == QrTypes.AUTH) {
                            navController.navigate(Routes.loginScreen+"?qrBody=$qrBody")
                        } else {
                            navController.navigate(Routes.homeScreen+"?qrBody=$qrBody")
                        }
                    }
                )
                try {
                    cameraProviderFuture.get().bindToLifecycle(
                        lifeCycleOwner,
                        selector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    Log.e("ScannerScreen", "Camera binding failed: ${e.message}")
                }
                previewView
            },
                modifier = Modifier.weight(1f)
            )
            val qrBody = code.value

            Text(
                text = "Text: $qrBody",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(60.dp)
            )
        }
    }
}
