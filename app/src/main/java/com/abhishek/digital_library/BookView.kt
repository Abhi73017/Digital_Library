package com.abhishek.digital_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhishek.digital_library.model.DatabaseCourse
import com.abhishek.digital_library.model.bookItems
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_book_view.*


class BookView : AppCompatActivity() {
    
    var BookList : MutableList<bookItems> = mutableListOf()
    var databook: MutableList<DatabaseCourse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)
        val intentcategory = intent.getStringExtra("category") ?: return
        
        
        FirebaseDatabase.getInstance().getReference("ebooks/$intentcategory").apply {
            addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                    Toast.makeText(
                        this@BookView,
                        "Database Process Cancelled",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onDataChange(data: DataSnapshot) {
                    
                    if (data.exists()){
                        for (item in data.children){
                            val DATA = item.getValue(DatabaseCourse::class.java)
                            databook.add(DATA!!)
                        }
                        if (loading_progress.visibility == View.VISIBLE){
                            loading_progress.visibility = View.GONE
                        }
                        rcvCall(databook)
                    }else{
                        if (loading_progress.visibility == View.VISIBLE){
                            loading_progress.visibility = View.GONE
                        }
                        Toast.makeText(this@BookView, "No Data Available",
                        Toast.LENGTH_SHORT).show()
                    }
                }

            }
                
            )
        }
    }

     fun rcvCall(databook: MutableList<DatabaseCourse>) {

         val adapter = BookAdapter(databook)
         rv_BookList.adapter = adapter
         rv_BookList.layoutManager = LinearLayoutManager(this@BookView)
    }
}
