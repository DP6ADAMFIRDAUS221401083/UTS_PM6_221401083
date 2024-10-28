package com.example.uts_greetingcard

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class previewCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_card)

        val cardType = intent.getStringExtra("CARD_TYPE") ?: ""
        val recipientName = intent.getStringExtra("RECIPIENT_NAME") ?: ""
        val message = intent.getStringExtra("MESSAGE") ?: ""

        val imageViewTemplate = findViewById<ImageView>(R.id.imageViewTemplate)
        val textViewRecipientName = findViewById<TextView>(R.id.textViewRecipientName)
        val textViewMessage = findViewById<TextView>(R.id.textViewMessage)

        textViewRecipientName.text = recipientName
        textViewMessage.text = message

        when (cardType) {
            "birthday" -> imageViewTemplate.setImageResource(R.drawable.birthdaytemplate)
            "wedding" -> imageViewTemplate.setImageResource(R.drawable.weddingtemplate)
            "eid" -> imageViewTemplate.setImageResource(R.drawable.eidtemplate)
        }

        findViewById<Button>(R.id.buttonEdit).setOnClickListener {
            finish() // Kembali ke CardFillActivity
        }

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            saveCardToGallery() // Implementasi simpan ke galeri
        }
    }

    private fun saveCardToGallery() {
        // Kode untuk menyimpan tampilan ke galeri, misalnya dengan mengambil screenshot layout
        // dan menyimpannya sebagai gambar di penyimpanan perangkat.
    }
}
