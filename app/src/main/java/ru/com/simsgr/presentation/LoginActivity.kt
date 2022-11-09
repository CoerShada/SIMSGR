package ru.com.simsgr.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.R
import ru.com.simsgr.domain.models.AuthData
import ru.com.simsgr.domain.viewmodels.VMALogin
import ru.com.simsgr.domain.viewmodels.factories.VMALoginFactory


class LoginActivity : AppCompatActivity() {

    lateinit var viewmodel : VMALogin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewmodel = ViewModelProvider(this, VMALoginFactory(this))[VMALogin::class.java]
        setListeners()
        setObservers()
    }

    override fun onStart() {
        super.onStart()
        viewmodel.authAuto()

    }





    private fun setListeners(){
        val bLogin: Button = findViewById(R.id.aMainBAuthorisation)
        val bRegister: Button = findViewById(R.id.aMainBRegistration)


        val tvLogin = findViewById<TextView>(R.id.aMainTVLogin)
        val tvPassword = findViewById<TextView>(R.id.aMainTVPassword)
        bLogin.setOnClickListener {
            try {
                val authData = AuthData(tvLogin.text.toString(), tvPassword.text.toString())
                viewmodel.login(authData = authData)
            }
            catch (e: Exception){
                Toast.makeText(this, "Ошибка авторизации", Toast.LENGTH_LONG).show()
            }
        }

        bRegister.setOnClickListener {
            try {
                val authData = AuthData(tvLogin.text.toString(), tvPassword.text.toString())
                viewmodel.register(authData = authData)
            }
            catch (e: Exception){
                Toast.makeText(this,
                    "Ошибка регистрации, попробуйте другой логин",
                    Toast.LENGTH_LONG).show()

            }
        }
    }


    private fun setObservers(){
        viewmodel.user.observe(this){
            if (it!=null){
                viewmodel.saveCurrentUser()

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }

}