package com.example.mobile

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val SHAREDPREFERENCES = "com.example.mobile.SHAREDPREFERENCES"
    private val REGISTRATED = "com.example.mobile.REGISTRATED"
    private val LOGGEDIN = "com.example.mobile.LOGGEDIN"
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.show()

        val sharedPreferences =
            this.activity?.getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)

        val bottomNavigation: BottomNavigationView? = activity?.findViewById(R.id.bottom_navigation)

        navController = findNavController()

        val currentPayments: CardView = view.findViewById(R.id.current_payments)
        val telephone: CardView = view.findViewById(R.id.telephone)
        val internet: CardView = view.findViewById(R.id.internet)

        //checking if user is registrated
        if (!sharedPreferences!!.contains(REGISTRATED)) {

            //navigate to welcome screen
            navController.navigate(R.id.welcomeFragment)
        }
        else if (!sharedPreferences.getBoolean(LOGGEDIN, false)) {

            //navigate to fragment login
            navController.navigate(R.id.loginFragment)
        }
        else {

            bottomNavigation?.visibility = BottomNavigationView.VISIBLE
            ObjectAnimator.ofFloat(current_payments, View.TRANSLATION_Y, -550f).apply {
                currentPayments.visibility = CardView.VISIBLE
                duration = 1000
                start()
            }
            ObjectAnimator.ofFloat(telephone, View.TRANSLATION_Y, -550f).apply {
                telephone.visibility = CardView.VISIBLE
                duration = 1000
                start()
            }
            ObjectAnimator.ofFloat(internet, View.TRANSLATION_Y, -550f).apply {
                internet.visibility = CardView.VISIBLE
                duration = 1000
                start()
            }
        }
    }



}