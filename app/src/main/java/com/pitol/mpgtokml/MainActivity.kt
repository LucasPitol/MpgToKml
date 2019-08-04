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
        //this.mViewHolder.btn = findViewById(R.id.calculate_btn)
        this.mViewHolder.switchImg = findViewById(R.id.switch_icon)

        this.mViewHolder.keyBoard.one = findViewById(R.id.one)


        this.mViewHolder.editValue.text = String.format("%d %s", defaultValue, getString(R.string.text_mpg))

        this.mViewHolder.switchImg.setOnClickListener{
            this.mViewHolder = switchConversor(this.mViewHolder)
        }

        this.mViewHolder.keyBoard.one.setOnClickListener{
            addValue(1)
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

        var newValue: String = String.format("%s%d %s", currentValue, input, unit)

        this.mViewHolder.editValue.text = newValue

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
    }
}
