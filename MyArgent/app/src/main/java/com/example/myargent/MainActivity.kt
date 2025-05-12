package com.example.myargent

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        val editTextAmount = findViewById<EditText>(R.id.editTextText)
        val btnToEuro = findViewById<Button>(R.id.button)
        val btnToDinar = findViewById<Button>(R.id.button2)

        btnToEuro.setOnClickListener {
            val amountText = editTextAmount.text.toString()
            if (amountText.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un montant", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("amount", amountText.toFloat())
            intent.putExtra("conversion", "TO_EURO")
            startActivity(intent)
        }

        btnToDinar.setOnClickListener {
            val amountText = editTextAmount.text.toString()
            if (amountText.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un montant", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("amount", amountText.toFloat())
            intent.putExtra("conversion", "TO_DINAR")
            startActivity(intent)
        }
    }
}
