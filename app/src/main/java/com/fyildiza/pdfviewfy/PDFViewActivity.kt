package com.fyildiza.pdfviewfy

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView

class PDFViewActivity : AppCompatActivity() {
    companion object{
        private const val PDF_SELECTION_CODE = 99
    }
    private lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfview)
        pdfView = findViewById(R.id.pdfViews)

        checkPdfAction(intent)
    }

    private fun checkPdfAction(intent: Intent?) {
        when(intent!!.getStringExtra("ViewType")){
            "assets"->{
                /**here we add PDF in assets*/
                showPdfFromAssets("kotlin.pdf.jar")
            }
            "storage"->{
                selectPdfFromStorage()
            }


    }
}

    private fun selectPdfFromStorage() {
        Toast.makeText(this@PDFViewActivity,
            "Select PDf",
            Toast.LENGTH_SHORT
        ).show()
        val pdfInt = Intent(Intent.ACTION_GET_CONTENT)
        pdfInt.type = "application/pdf"
        pdfInt.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(pdfInt,"Select PDF"), PDF_SELECTION_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE){
            val pdfStorage = data!!.data
            showPdfFromUri(pdfStorage)
        }

    }

    private fun showPdfFromUri(uri: Uri?) {
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    private fun showPdfFromAssets(pdfName: String) {
        pdfView.fromAsset(pdfName)
            .password(null)
            .defaultPage(0)
            .onPageError { page, _ ->
                Toast.makeText(this@PDFViewActivity,
                    "Error at page :$page",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .load()

    }
}

