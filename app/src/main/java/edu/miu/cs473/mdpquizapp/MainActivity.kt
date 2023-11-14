package edu.miu.cs473.mdpquizapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import edu.miu.cs473.mdpquizapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener{
            val selectedId = binding.q1Radio.checkedRadioButtonId
            var q1AnswerGiven = ""
            if(selectedId != -1){
                val radioButton = findViewById<View>(selectedId) as RadioButton
                q1AnswerGiven = radioButton.text.toString()
            }

            val q2AnswersGiven = mutableSetOf<String>()
            if(binding.q2op1.isChecked){
                q2AnswersGiven.add(binding.q2op1.text.toString())
            }
            if(binding.q2op2.isChecked){
                q2AnswersGiven.add(binding.q2op2.text.toString())
            }
            if(binding.q2op3.isChecked){
                q2AnswersGiven.add(binding.q2op3.text.toString())
            }
            if(binding.q2op4.isChecked){
                q2AnswersGiven.add(binding.q2op4.text.toString())
            }

            val q1Answer = "Readability"
            val q2Answers = listOf("x == 5","x in 1..5","x is Int")
            Log.d("q1AnswersGiven: ", q1AnswerGiven)
            Log.d("q2AnswersGiven: ", q2AnswersGiven.toString())

            var score = 0
            if(q1AnswerGiven == q1Answer){
                score += 50
            }
            if (q2AnswersGiven.containsAll(q2Answers)) {
                score += 50
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Quiz Result")
            builder.setMessage("Congratulations! You submitted on the ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())}, you achieved ${score}%")
            builder.show()
        }

        binding.resetBtn.setOnClickListener{
            binding.q1Radio.clearCheck()
            binding.q2op1.isChecked = false
            binding.q2op2.isChecked = false
            binding.q2op3.isChecked = false
            binding.q2op4.isChecked = false
        }


    }
}