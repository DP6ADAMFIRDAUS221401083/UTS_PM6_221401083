package com.example.uts_greetingcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Cek apakah username dan password benar
            if (username == "admin" && password == "admin123") {
                // Jika benar, tampilkan toast dan pindah ke ChooseGreetingActivity
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, chooseGreetingActivity::class.java)
                startActivity(intent)
            } else {
                // Jika salah, tampilkan toast
                Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}