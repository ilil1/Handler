package com.project.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mainText : TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button

    private lateinit var myThread: MyThread
    private lateinit var myHandler: MyHandler

    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)
        mainText = findViewById(R.id.mainText)

        isRunning = true

        myThread = MyThread()
        myThread.start()
        myHandler = MyHandler()

        startButton.setOnClickListener {
            isRunning = true
        }

        stopButton.setOnClickListener {
            isRunning = false
        }
    }

    inner class MyThread: Thread() {
        override fun run() {
            while(isRunning) {
                SystemClock.sleep(1000)
                myHandler.sendEmptyMessage(0)
            }
        }
    }

    inner class MyHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var now = System.currentTimeMillis()
            mainText.text = "메인스레드 값 : $now"
        }
    }
}