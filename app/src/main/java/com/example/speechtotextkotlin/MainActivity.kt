package com.example.speechtotextkotlin

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var txtView : TextView? = null
    var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView = findViewById(R.id.txt)
        button = findViewById(R.id.button_speak)

        button?.setOnClickListener {
            speak()
        }
    }

    fun speak(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE)

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"SPEAK NOW")
        startActivityForResult(intent,99)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 99 && resultCode == RESULT_OK){
            txtView?.setText(data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!![0])
        }
    }
}