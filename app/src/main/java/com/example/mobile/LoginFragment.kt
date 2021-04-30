package com.example.mobile

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val SHAREDPREFERENCES = "com.example.mobile.SHAREDPREFERENCES"
    private val PASSWORD = "com.example.mobile.PASSWORD"
    private val USERNAME = "com.example.mobile.USERNAME"
    private val LOGGEDIN = "com.example.mobile.LOGGEDIN"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val navController = findNavController()
        val editUsername:EditText = view.findViewById(R.id.username)
        val editPassword:EditText = view.findViewById(R.id.password)
        val loginConfirm:MaterialButton = view.findViewById(R.id.login_confirm)
        val loginCancel:MaterialButton = view.findViewById(R.id.login_cancel)
        val bottomNavigation:BottomNavigationView? = activity?.findViewById(R.id.bottom_navigation)

        (activity as AppCompatActivity).supportActionBar?.hide()

        bottomNavigation?.visibility = BottomNavigationView.INVISIBLE

        val sharedPreferences =
            this.activity?.getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)

        loginConfirm.setOnClickListener {
            val correctUsername = sharedPreferences?.getString(USERNAME, "")
            val correctPassword = sharedPreferences?.getString(PASSWORD, "")
            val userInputUsername = editUsername.text.toString()
            val userInputPassword = editPassword.text.toString()
            var usernameCorrect = false
            var passwordCorrect = false
            val snackbar: Snackbar

            if(editUsername.text.toString() == correctUsername) {
                usernameCorrect = true
            }
            if(editPassword.text.toString() == correctPassword) {
                passwordCorrect = true
            }

            if(usernameCorrect && passwordCorrect) {
                sharedPreferences?.edit()?.putBoolean(LOGGEDIN, true)?.apply()

                navController.navigate(R.id.homeFragment)
            }
            else if(!usernameCorrect && !passwordCorrect) {
                editUsername.setTextColor(Color.RED)
                editPassword.setTextColor(Color.RED)
                snackbar = Snackbar.make(view.findViewById(R.id.drawer_layout), "username and password are incorrect", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
            else if(!usernameCorrect && passwordCorrect) {
                editUsername.setTextColor(Color.RED)
                snackbar = Snackbar.make(view.findViewById(R.id.drawer_layout), "username is incorrect", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
            else if(!passwordCorrect && usernameCorrect) {
                editPassword.setTextColor(Color.RED)
                snackbar = Snackbar.make(view.findViewById(R.id.drawer_layout), "password is incorrect", Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }

        loginCancel.setOnClickListener {
            navController.navigate(R.id.welcomeFragment)
        }
    }
}