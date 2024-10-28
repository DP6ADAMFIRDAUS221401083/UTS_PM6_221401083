package com.example.uts_greetingcard

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class chooseGreetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_greeting)

        findViewById<Button>(R.id.ulangTahunButton).setOnClickListener {
            navigateToCardFill("birthday")
        }

        findViewById<Button>(R.id.pernikahanButton).setOnClickListener {
            navigateToCardFill("wedding")
        }

        findViewById<Button>(R.id.hariRayaButton).setOnClickListener {
            navigateToCardFill("eid")
        }
    }

    private fun navigateToCardFill(cardType: String) {
        val intent = Intent(this, insertTextActivity::class.java)
        intent.putExtra("CARD_TYPE", cardType)
        startActivity(intent)
    }
}