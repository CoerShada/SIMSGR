package ru.com.simsgr.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.domain.models.AuthData
import ru.com.simsgr.domain.viewmodels.VMALogin
import ru.com.simsgr.domain.viewmodels.factories.VMALoginFactory
import ru.com.simsgr.R


class LoginActivity : AppCompatActivity() {

    lateinit var viewmodel : VMALogin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewmodel = ViewModelProvider(this, VMALoginFactory(this))[VMALogin::class.java]

        var bLogin: Button = findViewById(R.id.aMainBAuthorisation)
        var bRegister: Button = findViewById(R.id.aMainBRegistration)


        val tvLogin = findViewById<TextView>(R.id.aMainTVLogin)
        val tvPassword = findViewById<TextView>(R.id.aMainTVPassword)


        bLogin.setOnClickListener {
            val authData = AuthData(tvLogin.text.toString(), tvPassword.text.toString())
            viewmodel.login(authData = authData)
        }

        bRegister.setOnClickListener {
            val authData = AuthData(tvLogin.text.toString(), tvPassword.text.toString())
            viewmodel.register(authData = authData)
        }

        viewmodel.user.observe(this){
            if (it!=null){
                viewmodel.saveCurrentUser()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }

        //viewmodel.authAuto()
    }





}