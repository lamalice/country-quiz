package com.example.countryquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.countryquiz.R.*
import com.example.countryquiz.databinding.ActivityQuizQuestionsBinding
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity() {

    private var progressBar : ProgressBar? = null
    private var tvProgressBar : TextView? = null
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null

    private lateinit var binding: ActivityQuizQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val questionsList = Constants.getQuestions()

        var currentPosition = 1
        val question = questionsList[currentPosition-1]
        binding.idImage.setImageResource(question.image)
        binding.progressBar.progress = currentPosition
        binding.tvProgress.text = "$currentPosition/${binding.progressBar?.max}"
        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour




    }
}