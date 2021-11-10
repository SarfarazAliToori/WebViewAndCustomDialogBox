package com.example.webviewkotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import java.net.CookieStore

class MainActivity : AppCompatActivity() {

    val link = "https://www.tutorialspoint.com/index.htm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        title = "MainActivity"
        supportActionBar?.hide()
        myWebView()
    }

    fun myWebView() {
        try {
            webView.webViewClient = WebViewClient()
            webView.loadUrl(link)
            val webViewSetting = webView.settings
            webViewSetting.javaScriptEnabled = true
            val cookies = CookieManager.getInstance().getCookie(link)
            Log.d("COOKIE", "Fist Time Cookies : $cookies")
//            val clearCookie = CookieManager.getInstance().removeAllCookies(null)
//            Log.d("COOKIE", "Clear Cookie : $cookies")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            //super.onBackPressed()
            //mDialogBox()
            customDialog()
        }
    }
// Dialog Box
    fun mDialogBox() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Alert !")
            .setMessage("Are You Really Want to Exit ?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(applicationContext, "You Click Yes Button", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
            .setNegativeButton("No", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(applicationContext, "Ok Enjoy", Toast.LENGTH_SHORT).show()
                }
            })
            .setNeutralButton("Later",object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
                }
            })
    }

    // Custom Dialog Box
    fun customDialog() {
        val mDialogView = View.inflate(this,R.layout.custom_dialog,null)
        val mAlertDialog = AlertDialog.Builder(this)
            .setView(mDialogView).show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // action perform on buttons
        mDialogView.btn_exit.setOnClickListener(View.OnClickListener {
            finish()
            Toast.makeText(applicationContext, "Exit", Toast.LENGTH_SHORT).show()
        })

        mDialogView.btn_later.setOnClickListener(View.OnClickListener {
            mAlertDialog.dismiss()
            Toast.makeText(applicationContext, "Ok Enjoy", Toast.LENGTH_SHORT).show()
        })
    }

    fun hello() {
        val webSetting  = webView.settings
        webSetting.javaScriptEnabled = true
        // improve webview performance
        //webSetting.cacheMode = true

    }
}