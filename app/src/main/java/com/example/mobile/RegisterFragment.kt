package com.example.mobile

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val SHAREDPREFERENCES = "com.example.mobile.SHAREDPREFERENCES"
    private val REGISTRATED = "com.example.mobile.REGISTRATED"
    private val PASSWORD = "com.example.mobile.PASSWORD"
    private val USERNAME = "com.example.mobile.USERNAME"
    private val LOGGEDIN = "com.example.mobile.LOGGEDIN"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val navController = findNavController()

        (activity as AppCompatActivity).supportActionBar?.hide()

        val sharedPreferences =
            this.activity?.getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)

        val editUsername: EditText = view.findViewById(R.id.username1)
        val editPassword1: EditText = view.findViewById(R.id.password1)
        val editPassword2: EditText = view.findViewById(R.id.password2)
        val registerUser: MaterialButton = view.findViewById(R.id.register_user)
        val cancelRegistration: MaterialButton = view.findViewById(R.id.cancel_registration)
        val registerInfo: TextView = view.findViewById(R.id.register_info)
        val checkBox1: CheckBox = view.findViewById(R.id.checkbox_password1)
        val checkBox2: CheckBox = view.findViewById(R.id.checkbox_password2)
        var correctPassword: String

        checkBox1.setOnClickListener {
            if(checkBox1.isChecked) editPassword1.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            if(!checkBox1.isChecked) editPassword1.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editPassword1.setSelection(editPassword1.length())
        }
        checkBox2.setOnClickListener {
            if(checkBox2.isChecked) editPassword2.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            if(!checkBox2.isChecked) editPassword2.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editPassword2.setSelection(editPassword2.length())
        }

        registerUser.setOnClickListener {
            when {
                password1.text.length < 5 -> {
                    registerInfo.setText(R.string.short_password)
                    editPassword1.setTextColor(Color.RED)
                }
                editPassword1.text.toString() == editPassword2.text.toString() -> {
                    correctPassword = editPassword1.text.toString()
                    sharedPreferences?.edit()?.putBoolean(LOGGEDIN, true)?.apply()
                    sharedPreferences?.edit()?.putBoolean(REGISTRATED, true)?.apply()
                    sharedPreferences?.edit()?.putString(USERNAME, editUsername.text.toString())?.apply()
                    sharedPreferences?.edit()?.putString(PASSWORD, correctPassword)?.apply()
                    navController.navigate(R.id.homeFragment)
                }
                else -> {
                    registerInfo.setText(R.string.different_passwords)
                    editPassword1.setTextColor(Color.RED)
                    editPassword2.setTextColor(Color.RED)
                }
            }
        }
        cancelRegistration.setOnClickListener {
            navController.navigate(R.id.welcomeFragment)
        }
    }
}