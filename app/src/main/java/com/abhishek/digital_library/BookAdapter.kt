package com.abhishek.digital_library

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.digital_library.model.DatabaseCourse
import kotlinx.android.synthetic.main.booklistview_rv.view.*

class BookAdapter (

    var book_items : List<DatabaseCourse>
): RecyclerView.Adapter<BookAdapter.bookItemViewHolder>(){

    inner class bookItemViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookItemViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.booklistview_rv, parent, false)

        return bookItemViewHolder(view)
    }

    override fun getItemCount(): Int {

        return book_items.size
    }

    override fun onBindViewHolder(holder: bookItemViewHolder, position: Int) {

        holder.itemView.View_btn.setOnClickListener {
            Intent(it.context, pdfReader::class.java).apply {
                putExtra("url", book_items[position].url)
                it.context.startActivity(this)
            }
        }

        holder.itemView.fav_btn.setOnClickListener {
            Toast.makeText(it.context, "This Feature will be available soon", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.apply {
            filename_Item.text = book_items[position].name
            //downloaduri_text.text = book_items[position].url
        }
    }

}