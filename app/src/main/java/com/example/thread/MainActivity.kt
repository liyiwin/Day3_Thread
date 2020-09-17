package com.example.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Step 1. 開啟線程
        thread.start()
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacksAndMessages(null)
    }

val handler  = Handler(object:Handler.Callback{

    override fun handleMessage(msg: Message): Boolean {
        //Step 4.  當 handler 處理 Message 會調用 這個 callback
        //Step 4.  在這裡接收 非主線程 送出訊息 及更新 UI 畫面
        textView.text = "${msg.what}"

        return true;
    }


})

val thread = Thread (object :Runnable  {

    override fun run() {
        try {
            Thread.sleep(1000) //Step 2. 在非主線程 模擬一段耗時操作,比如請求網絡
            handler.sendEmptyMessage(0) //Step 3. 在非主線程 送出訊息
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


})}