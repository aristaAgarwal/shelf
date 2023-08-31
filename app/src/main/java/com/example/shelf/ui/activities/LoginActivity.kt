package com.example.shelf.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.shelf.R
import com.example.shelf.constant.AppPreferences
import com.example.shelf.databinding.ActivityLogin2Binding
import com.example.shelf.ui.fragments.SignUp

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.btn.setOnClickListener {
            if (AppPreferences(this).getPass() == binding.pass.text.toString()){
                AppPreferences(this).setUsername(binding.name.text.toString())
                binding.errorCred.isVisible = false
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            else
            {
                binding.errorCred.isVisible = true
            }
        }
    }

    fun onClick(view: View) {
        when (view.id) {

            R.id.sign_up -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.frameLayout, SignUp())
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }
}