package org.training.whatsup

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogInVeiwActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_veiw)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val signInBtn:Button = findViewById(R.id.signInBtn)
        val signInPanel:CardView = findViewById(R.id.signInPanel)
        val usernameTxt:EditText = findViewById(R.id.usernameTxt)
        val pwdTxt:EditText = findViewById(R.id.pwdTxt)
        val enterBtn:Button = findViewById(R.id.enterBtn)
        signInBtn.setOnClickListener { signInPanel.visibility = View.VISIBLE }
        var username:String; var password:String
        enterBtn.setOnClickListener {
            username = usernameTxt.text.toString()
            password = pwdTxt.text.toString()
            if (username.equals("android")&&password.equals("google"))
            {
                Toast.makeText(this, "Welcome to WhatUp!!", Toast.LENGTH_LONG).show()
                usernameTxt.setText("")
                pwdTxt.setText("")
                signInPanel.visibility = View.GONE
            }
            else Toast.makeText(this, "Please Try Again", Toast.LENGTH_LONG).show()
        }
    }
}