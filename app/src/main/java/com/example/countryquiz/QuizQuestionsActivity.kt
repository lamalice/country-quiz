package com.example.countryquiz

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView

import androidx.core.content.ContextCompat
import com.example.countryquiz.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener {

    private var mcurrentPosition = 1
    private var mquestionsList : ArrayList<Question>? = null
    private var mselectedOption = 0
    private var muserName : String? = null
    private var mcorrectAnswer: Int = 0

    private lateinit var binding: ActivityQuizQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        muserName = intent.getStringExtra(Constants.USER_NAME)

        mquestionsList = Constants.getQuestions()

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()
        val question = mquestionsList!![mcurrentPosition - 1]
        binding.idImage.setImageResource(question.image)
        binding.progressBar.progress = mcurrentPosition
        binding.tvProgress.text = "$mcurrentPosition/${binding.progressBar?.max}"
        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.buttonSubmit.setOnClickListener(this)

        if (mcurrentPosition == mquestionsList!!.size) {
            binding.buttonSubmit?.text = "FINISH"
        } else {
            binding.buttonSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum: Int){
        defaultOptionView()
        mselectedOption = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }
    override fun onClick(view: View?) {
        when (view?.id){
            R.id.tv_option_one ->{
                selectedOptionView(binding.tvOptionOne, 1)

            }
            R.id.tv_option_two ->{
                selectedOptionView(binding.tvOptionTwo, 2)

            }
            R.id.tv_option_three ->{
                selectedOptionView(binding.tvOptionThree, 3)

            }
            R.id.tv_option_four ->{
                selectedOptionView(binding.tvOptionFour, 4)

            }
            R.id.button_submit ->{
                if (mselectedOption == 0) {
                    mcurrentPosition++

                    when {
                        mcurrentPosition <= mquestionsList!!.size -> {
                            setQuestion()
                        }else ->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, muserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mcorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mquestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    val question = mquestionsList?.get(mcurrentPosition-1)
                    if (question!!.correctAnswer!= mselectedOption) {
                        answerView(mselectedOption, R.drawable.wrong_option_border_bg)
                    }
                    else {
                        mcorrectAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                }
                if (mcurrentPosition == mquestionsList?.size!!){
                    binding.buttonSubmit.text = "Finish"
                }
                else {
                    binding.buttonSubmit.text = "Next Question"
                }

                mselectedOption = 0
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int){
        when (answer){
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}