package com.example.countryquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.countryquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val btn_start : Button = findViewById(R.id.btn_start)
        val edit_name : EditText = findViewById(R.id.edit_name)
        btn_start.setOnClickListener {

            if (edit_name.text.isEmpty()){
                Toast.makeText(this, "Please enter your name.", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)

            }
        }




    }


}