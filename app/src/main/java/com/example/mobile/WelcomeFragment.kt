package com.example.mobile

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        (activity as AppCompatActivity).supportActionBar?.hide()

        button_register.setOnClickListener{
            navController.navigate(R.id.registerFragment)
        }
        button_login.setOnClickListener{
            navController.navigate(R.id.loginFragment)
        }
    }
}