package com.example.mediaplayer.android

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.navigation.NavController
import com.example.mediaplayer.Greeting
import com.example.mediaplayer.android.navigation.bottom_nav_graph.SetUpBottomNavGraph
import com.example.mediaplayer.android.navigation.nav_graph.SetUpNavGraph

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestPermission()
            MyApplicationTheme {
                runtimepermission()
                // SetUpNavGraph()
                SetUpBottomNavGraph()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun runtimepermission() {
    val activity = (LocalContext.current as? Activity)
    checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 101, activity!!)
}

fun checkPermission(permission: String, requestCode: Int, activity: Activity) {
    if (ContextCompat.checkSelfPermission(
            activity,
            permission
        ) == PackageManager.PERMISSION_DENIED
    ) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    } else {
        Toast.makeText(activity, "Permission Already Granted..", Toast.LENGTH_SHORT).show()
    }
}

