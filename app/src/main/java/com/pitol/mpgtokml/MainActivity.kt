package com.pitol.mpgtokml

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val defaultValue: Int = 1

    private var mViewHolder: ViewHolder = ViewHolder()

    private var mpgToKml: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mViewHolder.from = findViewById((R.id.from))
        this.mViewHolder.to = findViewById((R.id.to))
        this.mViewHolder.editValue = findViewById(R.id.text_input)
        this.mViewHolder.answer = findViewById(R.id.text_answer)
        this.mViewHolder.switchImg = findViewById(R.id.switch_icon)

        this.mViewHolder.keyBoard.one = findViewById(R.id.one)
        this.mViewHolder.keyBoard.two = findViewById(R.id.two)
        this.mViewHolder.keyBoard.three = findViewById(R.id.three)
        this.mViewHolder.keyBoard.four = findViewById(R.id.four)
        this.mViewHolder.keyBoard.seven = findViewById(R.id.seven)
        this.mViewHolder.keyBoard.eight = findViewById(R.id.eight)
        this.mViewHolder.keyBoard.nine= findViewById(R.id.nine)
        this.mViewHolder.keyBoard.backSpace = findViewById(R.id.back_space)
        //this.mViewHolder.keyBoard.dot = findViewById(R.id.dot)

        this.mViewHolder.editValue.text = String.format("%d %s", defaultValue, getString(R.string.text_mpg))

        this.mViewHolder.switchImg.setOnClickListener{
            this.mViewHolder = switchConversor(this.mViewHolder)
        }

        this.mViewHolder.keyBoard.one.setOnClickListener{
            addValue(1)
        }

        this.mViewHolder.keyBoard.two.setOnClickListener{
            addValue(2)
        }

        this.mViewHolder.keyBoard.three.setOnClickListener{
            addValue(3)
        }

        this.mViewHolder.keyBoard.four.setOnClickListener{
            addValue(4)
        }

        this.mViewHolder.keyBoard.five.setOnClickListener{
            addValue(5)
        }

        this.mViewHolder.keyBoard.six.setOnClickListener{
            addValue(6)
        }

        this.mViewHolder.keyBoard.seven.setOnClickListener{
            addValue(7)
        }

        this.mViewHolder.keyBoard.eight.setOnClickListener{
            addValue(8)
        }

        this.mViewHolder.keyBoard.nine.setOnClickListener{
            addValue(9)
        }

        this.mViewHolder.keyBoard.zero.setOnClickListener{
            addValue(0)
        }

        this.mViewHolder.keyBoard.backSpace.setOnClickListener{
            addValue(10)
        }

        calculate()
    }

    private fun getInput(): Array<String>{
        return (this.mViewHolder.editValue.text.toString()).split(" ").toTypedArray()
    }

    private fun addValue(input: Int){

        var editValue = getInput()

        var currentValue: String = editValue[0]

        var unit: String = editValue[1]

        var newValue: String = ""

        if (input < 10){
            newValue = String.format("%s%d", currentValue, input)
        } else {
            when (input) {
                10 ->
                    if (currentValue.length <= 1) {
                        newValue = "0"
                    } else {
                        newValue = currentValue.dropLast(1)

                    }
            }
        }


        this.mViewHolder.editValue.text = String.format("%s %s", newValue, unit)

        calculate()
    }

    private fun switchConversor(viewHolder: ViewHolder): ViewHolder{

        this.mpgToKml = !this.mpgToKml

        if (this.mpgToKml){
            viewHolder.from.text = getString(R.string.text_mpg_complete)
            viewHolder.to.text = getString(R.string.text_kml_complete)
        } else{
            viewHolder.from.text = getString(R.string.text_kml_complete)
            viewHolder.to.text = getString(R.string.text_mpg_complete)
        }

        calculate()

        return viewHolder
    }

    fun calculate() {

        var inputValue = (this.mViewHolder.editValue.text.toString()).split(" ").toTypedArray()

        var value: Int = inputValue[0].toInt()

        var answer: Double = 1.0

        var unitAns: String

        var unitInput: String

        if (this.mpgToKml){

            answer = (value * 0.425144)

            unitInput = getString(R.string.text_mpg)

            unitAns = getString(R.string.text_kml)

        } else {

            answer = (value * 2.35215)

            unitInput = getString(R.string.text_kml)

            unitAns = getString(R.string.text_mpg)
        }

        this.mViewHolder.editValue.text = String.format("%d %s", value, unitInput)

        this.mViewHolder.answer.text = String.format("%.2f %s", answer, unitAns)
    }

    private class ViewHolder {

        lateinit var from: TextView
        lateinit var to: TextView
        lateinit var editValue: TextView
        lateinit var answer: TextView
        lateinit var btn: Button
        lateinit var switchImg: ImageView

        lateinit var keyBoard: KeyBoard

        init {
            this.keyBoard = KeyBoard()
        }
    }

    private class KeyBoard {
        lateinit var one: ImageView
        lateinit var two: ImageView
        lateinit var three: ImageView
        lateinit var four: ImageView
        lateinit var five: ImageView
        lateinit var six: ImageView
        lateinit var seven: ImageView
        lateinit var eight: ImageView
        lateinit var nine: ImageView
        lateinit var zero: ImageView
        lateinit var backSpace: ImageView
        //lateinit var dot: ImageView

    }
}
