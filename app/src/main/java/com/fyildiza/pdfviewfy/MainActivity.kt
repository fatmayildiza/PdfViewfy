package com.fyildiza.pdfviewfy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnAssets:Button
    private lateinit var btnStorage: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAssets = findViewById(R.id.butAssets)
        btnStorage = findViewById(R.id.butStorage)

        setUpOnClickListener()
    }

    private fun setUpOnClickListener() {
        btnAssets.setOnClickListener {
            val intent = Intent(this, PDFViewActivity::class.java)
            intent.putExtra("ViewType", "assets")
            startActivity(intent)
        }
        btnStorage.setOnClickListener {
            val intent = Intent(this, PDFViewActivity::class.java)
            intent.putExtra("ViewType", "storage")
            startActivity(intent)
        }

    }
}