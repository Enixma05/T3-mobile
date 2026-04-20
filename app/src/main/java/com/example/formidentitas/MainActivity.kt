package com.example.formidentitas

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val rgGender = findViewById<RadioGroup>(R.id.radioGroup)

        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbOlahraga)

        val btn = findViewById<MaterialButton>(R.id.btnTampilkan)

        val tvError = findViewById<TextView>(R.id.tvErrorNama)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val cardHasil = findViewById<MaterialCardView>(R.id.cardHasil)

        btn.setOnClickListener {

            val nama = etNama.text.toString().trim()

            if (nama.isEmpty()) {
                tvError.visibility = TextView.VISIBLE
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvError.visibility = TextView.GONE

            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "-"
            }

            val hobiList = mutableListOf<String>()
            if (cbMembaca.isChecked) hobiList.add("Membaca")
            if (cbCoding.isChecked) hobiList.add("Coding")
            if (cbOlahraga.isChecked) hobiList.add("Olahraga")

            val hobi = if (hobiList.isEmpty()) "-" else hobiList.joinToString(", ")

            val hasil = """
                <b>Nama   :</b> $nama<br>
                <b>Kelamin:</b> $gender<br>
                <b>Hobi   :</b> $hobi
            """.trimIndent()

            tvHasil.text = Html.fromHtml(hasil, Html.FROM_HTML_MODE_LEGACY)
            tvHasil.setTextColor(Color.parseColor("#232578"))
            tvHasil.setTypeface(null, android.graphics.Typeface.NORMAL)
            tvHasil.setBackgroundColor(Color.TRANSPARENT)

            cardHasil.setCardBackgroundColor(Color.parseColor("#E7E8F3"))
            cardHasil.strokeWidth = 2
            cardHasil.strokeColor = Color.parseColor("#4951AD")
        }
    }
}