package com.example.tp1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ComputeActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioSum: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var textValue1: TextView
    private lateinit var textValue2: TextView
    private lateinit var textResult: TextView
    private lateinit var editValue1: EditText
    private lateinit var editValue2: EditText
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        // Initialisation des vues
        radioGroup = findViewById(R.id.radioGroup)
        radioSum = findViewById(R.id.radioButton)
        radioSquare = findViewById(R.id.radioButton2)
        textValue1 = findViewById(R.id.textView2)
        textValue2 = findViewById(R.id.textView3)
        textResult = findViewById(R.id.textView5)
        editValue1 = findViewById(R.id.editTextText2)
        editValue2 = findViewById(R.id.editTextText3)
        btnCalculate = findViewById(R.id.button)

        // Gestion de l'affichage selon la sélection du RadioButton
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton -> { // Sum
                    textValue1.text = "Value 1:"
                    textValue1.visibility = View.VISIBLE
                    editValue1.visibility = View.VISIBLE
                    textValue2.visibility = View.VISIBLE
                    editValue2.visibility = View.VISIBLE
                }
                R.id.radioButton2 -> { // Square
                    textValue1.text = "Value:"
                    textValue1.visibility = View.VISIBLE
                    editValue1.visibility = View.VISIBLE
                    textValue2.visibility = View.GONE
                    editValue2.visibility = View.GONE
                }
            }
        }

        // Gestion du calcul
        btnCalculate.setOnClickListener {
            try {
                when {
                    radioSum.isChecked -> {
                        val value1 = editValue1.text.toString().toInt()
                        val value2 = editValue2.text.toString().toInt()
                        val result = value1 + value2
                        textResult.text = result.toString()
                    }
                    radioSquare.isChecked -> {
                        val value = editValue1.text.toString().toInt()
                        val result = value * value
                        textResult.text = result.toString()
                    }
                }
            } catch (e: NumberFormatException) {
                textResult.text = "Invalid Input"
            }
        }
    }
}
