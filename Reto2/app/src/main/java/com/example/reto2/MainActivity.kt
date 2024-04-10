package com.example.reto2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et1 = findViewById<EditText>(R.id.et1)
        val et2 = findViewById<EditText>(R.id.et2)
        val r1 = findViewById<RadioButton>(R.id.r1)
        val r2 = findViewById<RadioButton>(R.id.r2)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            if (r1.isChecked)
                tv1.text = "Bienvenido ${et1.text.toString()} ${et2.text.toString()}\n" +
                        "Género: ${r1.text.toString()}"
            if (r2.isChecked)
                tv1.text = "Bienvenida ${et1.text.toString()} ${et2.text.toString()}\n" +
                        "Género: ${r2.text.toString()}"
        }
    }
}
