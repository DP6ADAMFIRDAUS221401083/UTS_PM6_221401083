package com.example.uts_greetingcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class insertTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_text)

        val cardType = intent.getStringExtra("CARD_TYPE") ?: ""

        findViewById<Button>(R.id.buatKartuButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameField).text.toString()
            val message = findViewById<EditText>(R.id.greetingField).text.toString()

            val intent = Intent(this, PreviewCardActivity::class.java)
            intent.putExtra("CARD_TYPE", cardType)
            intent.putExtra("RECIPIENT_NAME", name)
            intent.putExtra("MESSAGE", message)
            startActivity(intent)
        }
    }
}