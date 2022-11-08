package ru.com.simsgr.presentation

import android.annotation.SuppressLint
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


class MainActivity : AppCompatActivity() {

    lateinit var viewmodel : VMAMain
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this, VMAMainFactory(this))[VMAMain::class.java]
        navController = Navigation.findNavController(this, R.id.aMainFCVMain)

        setObservers()
        setListeners()

        viewmodel.getCurrentUser()
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
            viewmodel.logout()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}



