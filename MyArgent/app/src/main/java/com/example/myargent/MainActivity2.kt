package com.example.myargent

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Initialisation des vues
        val textResult = findViewById<TextView>(R.id.textViewResult)

        // Récupération des données
        val amount = intent.getFloatExtra("amount", 0.0f)
        val conversionType = intent.getStringExtra("conversion")
        val rate = 3.3f
        var resultText = ""

        if (conversionType == "TO_EURO") {
            val result = amount / rate
            resultText = "L’équivalent de $amount DT en Euro est : %.2f €".format(result)
        } else if (conversionType == "TO_DINAR") {
            val result = amount * rate
            resultText = "L’équivalent de $amount Euro en DT est : %.2f DT".format(result)
        }

        textResult.text = resultText
    }
}
