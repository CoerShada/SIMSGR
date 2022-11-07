package ru.com.simsgr.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.com.simsgr.R
import ru.com.simsgr.domain.viewmodels.VMAMain
import ru.com.simsgr.domain.viewmodels.factories.VMAMainFactory


class MainActivity : AppCompatActivity() {

    lateinit var viewmodel : VMAMain
    lateinit var navController: NavController

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this, VMAMainFactory(this))[VMAMain::class.java]

        val userTV = findViewById<TextView>(R.id.aMainTVAuthAs)

        viewmodel.user.observe(this){
            userTV.text = "${resources.getString(R.string.authorized_as)}  ${it.login}"
        }
        navController = Navigation.findNavController(this, R.id.aMainFCVMain)
        viewmodel.getCurrentUser()
    }


}



