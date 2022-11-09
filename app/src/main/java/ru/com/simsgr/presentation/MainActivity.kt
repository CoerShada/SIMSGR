package ru.com.simsgr.presentation

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.com.simsgr.R
import ru.com.simsgr.domain.viewmodels.VMAMain
import ru.com.simsgr.domain.viewmodels.factories.VMAMainFactory
import ru.com.simsgr.presentation.services.MessageReceivingService


class MainActivity : AppCompatActivity() {

    lateinit var viewmodel : VMAMain
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this, VMAMainFactory(this))[VMAMain::class.java]

        setObservers()
        setListeners()
        navController = Navigation.findNavController(this, R.id.aMainFCVMain)
        viewmodel.getCurrentUser()
        startService()
    }

    private fun startService(){
        if (!isServiceRunning(MessageReceivingService::class.java)) {
            startService(
                Intent(this@MainActivity, MessageReceivingService::class.java)
            )

            navController.navigate(R.id.action_menuFragment_to_loadFragment)

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers(){
        val userTV = findViewById<TextView>(R.id.aMainTVAuthAs)

        viewmodel.user.observe(this){
            userTV.text = "${resources.getString(R.string.authorized_as)}  ${it.login}"
        }
    }

    private fun setListeners(){
        val bLogout = findViewById<ImageButton>(R.id.aMainBLogout)
        bLogout.setOnClickListener{
            stopService(
                 Intent(this@MainActivity, MessageReceivingService::class.java)
            )

            viewmodel.logout()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

}



