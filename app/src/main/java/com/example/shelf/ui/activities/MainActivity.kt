package com.example.shelf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelf.databinding.ActivityMainBinding
import com.example.shelf.adapter.BooksAdapter
import com.example.shelf.ui.model.ShelfDOItem
import com.example.shelf.ui.viewModel.mainViewModel

class MainActivity : AppCompatActivity(), BooksAdapter.AppLinkClick {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        startActivity(Intent(this, LoginActivity::class.java))


    }

    override fun onResume() {
        super.onResume()
        getBooks()
    }
    fun getBooks(){
        val viewModel by viewModels<mainViewModel>()
        viewModel.getBooks()
        viewModel.booksApiCaller.observe(this){
            if(it != null){
                setBooksAdapter(it)
            }
        }
    }
    fun setBooksAdapter(books: List<ShelfDOItem>) {
        Log.e("Books Adapter", "In here")
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.booksRv.layoutManager = layoutManager
        binding.booksRv.setItemViewCacheSize(books.size)
        binding.booksRv.adapter =
            BooksAdapter(this, books, this)
    }

    override fun onAppLinkClicked(p: ShelfDOItem) {

    }

}