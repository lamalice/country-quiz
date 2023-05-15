package com.example.countryquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.countryquiz.databinding.ActivityResultBinding


class ResultActivity: AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityResultBinding
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityResultBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)

         val userName = intent.getStringExtra(Constants.USER_NAME)
         val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
         val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

         binding.score.text = "Your score is $correctAnswers out of $totalQuestions"
         binding.name.text = userName

         binding.finishButton.setOnClickListener {
             startActivity(Intent(this, MainActivity::class.java))
         }





     }
     override fun onClick(p0: View?) {
         TODO("Not yet implemented")
     }


 }