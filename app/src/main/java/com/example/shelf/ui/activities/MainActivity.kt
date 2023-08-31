package com.example.shelf.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shelf.R
import com.example.shelf.databinding.ActivityMainBinding
import com.example.shelf.adapter.BooksAdapter
import com.example.shelf.constant.AppPreferences
import com.example.shelf.model.ShelfDO
import com.example.shelf.model.ShelfDOItem
import com.example.shelf.viewModel.mainViewModel

class MainActivity : AppCompatActivity(), BooksAdapter.AppLinkClick, BooksAdapter.GetFav {
    lateinit var binding: ActivityMainBinding
    var shelf: ShelfDO? = null
    var shelfFav = mutableListOf<ShelfDOItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(AppPreferences(this).getUsername() == ""){
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        getBooks()
        var order = false
        binding.switchOrder.setOnCheckedChangeListener { compoundButton, isChecked ->
            order = isChecked
            compoundButton.text = if (isChecked) "on" else "off"
        }
        var title = false
        var hits = false
        var fav = false
        binding.titleF.setOnClickListener {
            title = !title
            if (title) {
                binding.titleF.setCardBackgroundColor(getColor(R.color.teal_700))
                val list: List<ShelfDOItem>? = shelf?.sortedBy { it.title }
                setBooksAdapter(list!!)
            } else {
                binding.titleF.setCardBackgroundColor(getColor(R.color.blue))
                setBooksAdapter(shelf!!)
            }
        }
        binding.hitsF.setOnClickListener {
            hits = !hits
            if (hits) {
                binding.hitsF.setCardBackgroundColor(getColor(R.color.teal_700))
                val list: List<ShelfDOItem>? = shelf?.sortedBy { it.hits }
                setBooksAdapter(list!!)
            } else {
                binding.hitsF.setCardBackgroundColor(getColor(R.color.blue))
                setBooksAdapter(shelf!!)
            }
        }
        binding.favsF.setOnClickListener {
            fav = !fav
            if (fav) {
                binding.hitsF.setCardBackgroundColor(getColor(R.color.teal_700))
                setBooksAdapter(shelfFav)
            } else {
                binding.hitsF.setCardBackgroundColor(getColor(R.color.blue))
                setBooksAdapter(shelf!!)
            }
        }

        binding.logout.setOnClickListener {
            AppPreferences(this).setUsername("")
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    fun getBooks(){
        val viewModel by viewModels<mainViewModel>()
        viewModel.getBooks()
        viewModel.booksApiCaller.observe(this){
            if(it != null){
                shelf = it
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
            BooksAdapter(this, books, this, this)
    }

    override fun onAppLinkClicked(p: ShelfDOItem) {

        val intent = Intent(this, BookSummary::class.java)
        intent.putExtra("bookSummary", p)
        startActivity(intent)
    }

    override fun onclickFav(p: ShelfDOItem, isChecked: Boolean) {
        if(!shelfFav.contains(p)){ shelfFav.add(p) }
        else {
            shelfFav.remove(p)
        }
        if (!isChecked)
            shelfFav.remove(p)
    }

}