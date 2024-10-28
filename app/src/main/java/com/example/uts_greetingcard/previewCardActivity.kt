package com.example.uts_greetingcard

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.OutputStream

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
    private fun saveBitmapToGallery(bitmap: Bitmap) {
        val contentResolver = contentResolver
        val fileName = "GreetingCard_${System.currentTimeMillis()}.png"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/GreetingCards")
            }
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            contentResolver.openOutputStream(uri)?.use { outputStream ->
                // Pastikan outputStream tidak null, jika null, blok ini tidak akan dieksekusi
                if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                    Toast.makeText(this, "Kartu berhasil disimpan ke galeri", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Gagal menyimpan kartu", Toast.LENGTH_SHORT).show()
                }
            } ?: run {
                Toast.makeText(this, "Gagal membuka stream penyimpanan", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Gagal menyimpan kartu", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        view.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun saveCardToGallery() {
        // Ambil tampilan FrameLayout yang ingin disimpan
        val cardLayout = findViewById<FrameLayout>(R.id.frameLayoutTemplate)

        // Ambil screenshot dari layout
        val bitmap = getBitmapFromView(cardLayout)

        // Simpan bitmap ke galeri
        if (bitmap != null) {
            saveBitmapToGallery(bitmap)
        } else {
            Toast.makeText(this, "Gagal mengambil gambar kartu", Toast.LENGTH_SHORT).show()
        }
    }

}
