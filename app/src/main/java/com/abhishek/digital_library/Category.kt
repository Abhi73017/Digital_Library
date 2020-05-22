package com.abhishek.digital_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_category.*

class Category : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        Literature_btn.setOnClickListener {
            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "NovelsAndLiterature")
            startActivity(i)
        }

        ml_btn.setOnClickListener {
            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "MachineLearning")
            startActivity(i)
        }

        karlmarx_btn.setOnClickListener {
            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "KarlMarx")
            startActivity(i)
        }

        maths_btn.setOnClickListener {
            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "Mathematics")
            startActivity(i)
        }

        eee_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "EEE")
            startActivity(i)
        }

        programming_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "Programming")
            startActivity(i)
        }

        ece_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "ECE")
            startActivity(i)
        }

        history_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "History")
            startActivity(i)
        }

        civil_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "CivilEngg")
            startActivity(i)
        }

        ds_algo_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "DsAlgo")
            startActivity(i)
        }

        english_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "English")
            startActivity(i)
        }

        chiildren_btn.setOnClickListener {

            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "Children")
            startActivity(i)
        }

        bio_btn.setOnClickListener {
            Toast.makeText(this, "Will be Avaiable soon", Toast.LENGTH_SHORT).show()
        }

        geography_btn.setOnClickListener {
            Toast.makeText(this, "Will be Avaiable soon", Toast.LENGTH_SHORT).show()
        }

        spiritual_btn.setOnClickListener {
            val i = Intent(this, BookView::class.java)
            i.putExtra("category", "Spiritual")
            startActivity(i)
        }

    }
}



