package com.example.shelf.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shelf.R
import com.example.shelf.model.ShelfDOItem
import java.math.RoundingMode
import java.text.DecimalFormat

class BooksAdapter(
    var context: Context,
    var product: List<ShelfDOItem>,
    var onAppLinkClick: AppLinkClick
) : RecyclerView.Adapter<BooksAdapter.RACItemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RACItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.books_item_layout, parent, false
        )
        this.context = parent.context
        return RACItemHolder(view)
    }

    override fun getItemCount() = product.size

    inner class RACItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        var image = view.findViewById<ImageView>(R.id.image)
        var title = view.findViewById<TextView>(R.id.title)
        var hits = view.findViewById<TextView>(R.id.hits)
        var bookItem = view.findViewById<CardView>(R.id.book_item)
        var star = view.findViewById<CheckBox>(R.id.star)
        @SuppressLint("SetTextI18n")
        fun bindItem(product: ShelfDOItem) {

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            loadImage(product.image, image)
            title.text = product.title
            hits.text = "Hits: " + product.hits
            Log.e("CartAdapter", product.toString())
            star.setOnCheckedChangeListener{compoundButton, isChecked ->
                if (isChecked)
                    star.setTextColor(Color.BLUE)
            }
            bookItem.setOnClickListener {
                onAppLinkClick.onAppLinkClicked(product)
            }
        }
    }

    fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap, transition: Transition<in Bitmap?>?
            ) {
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }

    override fun onBindViewHolder(holder: RACItemHolder, position: Int) {
        holder.bindItem(product[position])
    }

    interface AppLinkClick {
        fun onAppLinkClicked(p: ShelfDOItem)
    }
}