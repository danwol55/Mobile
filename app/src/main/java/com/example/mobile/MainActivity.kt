package com.example.mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var bottomNavigation: BottomNavigationView
    private val SHAREDPREFERENCES = "com.example.mobile.SHAREDPREFERENCES"
    private val LOGGEDIN = "com.example.mobile.LOGGEDIN"

    companion object {
        var paymentsList = mutableListOf<String>()
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        val currentNetwork = connectivityManager?.activeNetwork
        val intent = Intent(
            Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS,
            Uri.parse("com.example.mobile")
        )
        val networkCapabilities = connectivityManager?.getNetworkCapabilities(currentNetwork)
        val networkProperties = connectivityManager?.getLinkProperties(currentNetwork)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.notificationsFragment,
                R.id.paymentsFragment,
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.welcomeFragment
            )
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottom_navigation.setupWithNavController(navController)
        nav_view.setupWithNavController(navController)

        val sharedPreferences = getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)
        if (!sharedPreferences.contains("1")) loadData()
        for (i in 1..12) {
            if (sharedPreferences.contains(i.toString())) {
                paymentsList.add(sharedPreferences.getString(i.toString(), "aaa").toString())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.notifications, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        if(item.itemId==R.id.notifications)
        {
            navController.navigate(R.id.notificationsFragment)
            return true
        }
        if(item.itemId==R.id.logout)
        {
            val sharedPreferences = getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putBoolean(LOGGEDIN, false)?.apply()
            navController.navigate(R.id.loginFragment)
            return true
        }
        else return super.onOptionsItemSelected(item)
    }

    fun loadData(): Unit {
        val sharedPreferences = getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE)
        with(sharedPreferences.edit())
        {
            putString("12", "december 2020")
            putString("11", "november 2020")
            putString("10", "october 2020")
            putString("9", "september 2020")
            putString("8", "august 2020")
            putString("7", "july 2020")
            putString("6", "june 2020")
            putString("5", "may 2020")
            putString("4", "april 2020")
            putString("3", "march 2020")
            putString("2", "february 2020")
            putString("1", "january 2020")
            apply()
        }

    }
}