package com.example.retopreferencias

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var textViewScore: TextView
    private lateinit var preferences: SharedPreferences
    private var attempts: Int = 0
    private var secretNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewScore = findViewById(R.id.textViewScore)
        val buttonGuess = findViewById<Button>(R.id.buttonGuess)

        preferences = getSharedPreferences("game_data", Context.MODE_PRIVATE)
        attempts = preferences.getInt("attempts", 0)
        updateScoreTextView()

        secretNumber = generateRandomNumber()

        buttonGuess.setOnClickListener {
            guessNumber()
        }
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(1, 51)
    }

    private fun guessNumber() {
        val et1 = findViewById<EditText>(R.id.et1)
        val guessedNumber = et1.text.toString().toIntOrNull()

        if (guessedNumber != null) {
            attempts++
            if (guessedNumber == secretNumber) {
                Toast.makeText(this, "¡Ganaste! El número es $secretNumber. Intentos: $attempts", Toast.LENGTH_SHORT).show()
                secretNumber = generateRandomNumber()

                finish()
            } else if (guessedNumber < secretNumber) {
                Toast.makeText(this, "El número es mayor. Intentos: $attempts", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "El número es menor. Intentos: $attempts", Toast.LENGTH_SHORT).show()
            }
            updateScoreTextView()
        } else {
            Toast.makeText(this, "Ingresa un número válido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateScoreTextView() {
        textViewScore.text = "Intentos: $attempts"
        val editor = preferences.edit()
        editor.putInt("attempts", attempts)
        editor.apply()
    }
}
