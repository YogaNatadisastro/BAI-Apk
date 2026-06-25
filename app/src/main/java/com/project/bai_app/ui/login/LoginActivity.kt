package com.project.bai_app.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
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
import org.koin.core.component.getScopeId
import kotlin.jvm.Throws

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

        setupAction()
        setupObservers()
    }

    private fun setupAction() {
        bind.apply {
            loginBtn.setOnClickListener {
                val username = email.text.toString().trim()
                val password = password.text.toString().trim()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Email dan password tidak boleh kosong",
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.login(username, password)
                Log.d("LoginActivity", "Login rqeuest sent for user: $username")
            }

            signUpBtn.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupObservers(){
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { response ->
                val userId = response.user?.id ?: 0
                val roleId = response.user?.role?.id ?: 1

                val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putInt("User_id", userId)
                editor.putInt("Role_id", roleId)
                editor.apply()

                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()

                navigateToMain(roleId)
            }.onFailure { exception ->
                Toast.makeText(
                    this,
                    "Login gagal: ${exception.localizedMessage ?: "Periksa koneksi anda"}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("LoginActivity", "Login Error: ${exception.message}")
            }
        }
    }

    private fun navigateToMain(roleId: Int) {
       val intent = when (roleId) {
           1 -> Intent(this, BaseAdminActivity::class.java)
           2 -> Intent(this, BaseUserActivity::class.java)
           else -> Intent(this, BaseUserActivity::class.java)
       }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}