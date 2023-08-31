package com.example.shelf.ui.activities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.shelf.databinding.ActivityBookSummaryBinding
import com.example.shelf.model.ShelfDOItem

class BookSummary : AppCompatActivity() {
    lateinit var binding: ActivityBookSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("bookSummary") as ShelfDOItem
        setContent(product)
    }
    @SuppressLint("SetTextI18n")
    fun setContent(product: ShelfDOItem) {
        loadImage(product.image)
        binding.title.text = product.title
        binding.hits.text = "Hits: ${product.hits}"
        binding.alias.text = "Alias: ${product.alias}"
        binding.updated.text = "Updated on - ${product.lastChapterDate}"
    }

    fun loadImage(imageUrl: String) {
        Glide.with(this).asBitmap().load(imageUrl).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap, transition: Transition<in Bitmap?>?
            ) {
                binding.image.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    }
}