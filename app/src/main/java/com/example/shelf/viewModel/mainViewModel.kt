package com.example.shelf.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shelf.ui.model.CountryDO
import com.example.shelf.ui.model.ShelfDO
import com.example.shelf.ui.network.RetrofitService
import kotlinx.coroutines.launch

class mainViewModel : ViewModel() {
    val api = RetrofitService().getcountries
    val books = RetrofitService().getBooks
    private val _apiCaller = MutableLiveData<CountryDO>()
    val apiCaller: LiveData<CountryDO>
        get() = _apiCaller
    private val _booksApiCaller = MutableLiveData<ShelfDO>()
    val booksApiCaller: LiveData<ShelfDO>
        get() = _booksApiCaller

    fun getCountries() {
        viewModelScope.launch {
            try {
                val result = api.getCountries()
                _apiCaller.postValue(result.body())
            } catch (e: Exception) {
                Log.e("MainViewModel", e.toString())
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            try {
                val result = books.getBooks()
                _booksApiCaller.postValue(result.body())
            } catch (e: Exception) {
                Log.e("MainViewModel", e.toString())
            }
        }
    }
}
