package com.example.shelf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.shelf.R
import com.example.shelf.constant.AppPreferences
import com.example.shelf.databinding.FragmentSignInBinding
import com.example.shelf.ui.activities.MainActivity

class SignIn : Fragment() {

   lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding?.signUp?.setOnClickListener(){
            val transaction = parentFragmentManager.beginTransaction()
            val fragment = SignUp()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.btn.setOnClickListener {
            if (AppPreferences(requireContext()).getPass() == binding.pass.text.toString()){
                AppPreferences(requireContext()).setUsername(binding.name.text.toString())
                binding.errorCred.isVisible = false
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            else
            {
                binding.errorCred.isVisible = true
            }
        }
    }
}