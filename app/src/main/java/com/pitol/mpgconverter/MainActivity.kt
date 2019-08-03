package com.pitol.mpgconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val defaultValue: Double = 1.0;

    private var mViewHolder: ViewHolder = ViewHolder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mViewHolder.editValue = findViewById(R.id.text_input)
        this.mViewHolder.answer = findViewById(R.id.text_answer)
        this.mViewHolder.btn = findViewById(R.id.calculate_btn)

        this.mViewHolder.btn.setOnClickListener{
            calculate()
        }

//        calculate_btn.setOnClickListener {
//            calculate()
//        }

    }



    fun calculate() {
        var value: Double = (this.mViewHolder.editValue.text.toString()).toDouble()

        var kml: Double = (value * 0.425144)

        this.mViewHolder.answer.text = String.format("%.2f", kml)
    }

    private class ViewHolder {
        lateinit var editValue: EditText
        lateinit var answer: TextView
        lateinit var btn: Button
    }
}
