package com.project.bai_app.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.bai_app.R
import com.project.bai_app.databinding.ActivitySignUpBinding
import com.project.bai_app.di.model.repo.MainRepository
import com.project.bai_app.ui.login.LoginActivity
import com.project.bai_app.utils.Helper

class SignUpActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySignUpBinding

    private val viewModel: SignUpViewModel by viewModels {
        SignUpViewModelF(repo = MainRepository())
    }

    private val helper = Helper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bind = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bind.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        bind.signupBtn.setOnClickListener {
            val username = bind.username.text.toString().trim()
            val email = bind.emailReg.text.toString()
            val password = bind.passwordReg.text.toString()
            val accessCode = bind.adminCode.text.toString()

            val error = helper.validateInput(
                username, email, password, accessCode
            )

            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.signUp(
                username, email, password, accessCode
            )
        }
    }

    private fun setupObservers() {
        viewModel.signUpResult.observe(this) { result ->
            result.onSuccess {
                Toast.makeText(this, "Berhasil Daftar Akun", Toast.LENGTH_SHORT).show()

                val intent = if (it.role?.id == 2) {
                    Intent(this, LoginActivity::class.java)
                } else {
                    Intent(this, LoginActivity::class.java)
                }

                startActivity(intent)
                finish()
            }
            result.onFailure {
                Toast.makeText(this, "Gagal Daftar Akun", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

