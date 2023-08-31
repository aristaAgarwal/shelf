package com.example.shelf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.shelf.R
import androidx.fragment.app.viewModels
import com.example.shelf.databinding.FragmentSignInBinding
import com.example.shelf.databinding.FragmentSignUpBinding
import com.example.shelf.ui.activities.MainActivity
import com.example.shelf.ui.viewModel.mainViewModel

class SignUp : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPassword()
        getCountries()
        binding.registerBtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
            activity?.finish()
        }
    }

    fun checkPassword() {
        var newPass = ""
        var newPassConf = ""
        binding.newPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newPass = binding.newPass.text.toString()
                val regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%&()]).{8,}$".toRegex()
                binding.pass.isVisible = !regex.matches(newPass)
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


        binding.newPassConf.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                newPassConf = binding.newPassConf.text.toString()
                if (newPass == newPassConf && newPass != "") {
                    binding.registerBtn.isEnabled = true
                    binding.passMatch.isVisible = false
                } else {
                    binding.registerBtn.isEnabled = false
                    binding.passMatch.isVisible = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    fun getCountries() {
        val countries = mutableListOf<String>()
        val viewModel by viewModels<mainViewModel>()
        viewModel.getCountries()
        viewModel.apiCaller.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.e("printing logs", it.toString())

                countries.add(it.data.AF.country)
            }
        }
        val countAdapter = ArrayAdapter(
            requireContext(), R.layout.spinner_text, countries
        )
        countAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        binding.selectCountry.adapter = countAdapter
    }

}