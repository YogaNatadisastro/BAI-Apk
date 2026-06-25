package com.project.bai_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.bai_app.databinding.ActivityLoginBinding
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.ui.adminPage.BaseAdminActivity
import com.project.bai_app.ui.signup.SignUpActivity
import com.project.bai_app.ui.userPage.BaseUserActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelF.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.apply {
            loginBtn.setOnClickListener {
                val username = bind.email.text.toString().trim()
                val password = bind.password.text.toString().trim()

                if (username == "psikolog" && password == "password123") {
                    Toast.makeText(this@LoginActivity, "Login Successfully as Psikolog", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, BaseAdminActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    viewModel.login(username, password)
                }
                Log.d("LoginActivity", "Press bottom is successfully")
            }

            signUpBtn.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.loginResult.observe(this@LoginActivity) { result ->
            result.onSuccess { response ->
                Toast.makeText(this@LoginActivity, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, BaseAdminActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun navigateToMain(roleId: Int) {
       when (roleId) {
           1 -> {
               startActivity(Intent(this, BaseAdminActivity::class.java).apply {
                   flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
               })
           }
           2 -> {
               startActivity(Intent(this, BaseUserActivity::class.java).apply {
                   flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
               })
           }
       }
    }

}