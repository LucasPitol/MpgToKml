package com.pitol.mpgtokml

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val defaultValue: Double = 1.0

    private var mViewHolder: ViewHolder = ViewHolder()

    private var mpgToKml: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        this.mViewHolder.from = findViewById((R.id.from))
        this.mViewHolder.to = findViewById((R.id.to))
        this.mViewHolder.editValue = findViewById(R.id.text_input)
        this.mViewHolder.answer = findViewById(R.id.text_answer)
        this.mViewHolder.btn = findViewById(R.id.calculate_btn)
        this.mViewHolder.switchImg = findViewById(R.id.switch_icon)

        this.mViewHolder.btn.setOnClickListener{
            calculate()
        }

        this.mViewHolder.switchImg.setOnClickListener{
            this.mViewHolder = switchCOnversor(this.mViewHolder)
        }
    }

    private fun switchCOnversor(viewHolder: ViewHolder): ViewHolder{

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

        var value: Double = (this.mViewHolder.editValue.text.toString()).toDouble()

        var answer: Double = 1.0

        var unit: String

        if (this.mpgToKml){

            answer = (value * 0.425144)

            unit = getString(R.string.text_kml)

        } else {

            answer = (value * 0.354006)

            unit = getString(R.string.text_mpg)
        }

        this.mViewHolder.answer.text = String.format("%.2f %s", answer, unit)
    }

    private class ViewHolder {
        lateinit var from: TextView
        lateinit var to: TextView
        lateinit var editValue: EditText
        lateinit var answer: TextView
        lateinit var btn: Button
        lateinit var switchImg: ImageView
    }
}
