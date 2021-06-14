package com.surepass.exampleapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.surepass.livenessandroidsdk.ui.InitSDK
import kotlinx.android.synthetic.main.activity_main.*

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //token is needed when using liveness sdk
        val token = "TOKEN"
        val intent = Intent(this, InitSDK::class.java)
        intent.putExtra("token",token)

        startActivityForResult(intent , 10000)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10000 -> {
                val response = data!!.getStringExtra("response");
                responseView.text = response
                Log.d("CONSOLE", "response $response")
            }
        }
    }
}