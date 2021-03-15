package br.com.ocaminhodaspedras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btSingInLogin.setOnClickListener {
            login()
        }

        tvForgotPasswordLogin.setOnClickListener {
            val intencaoDeChamada = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intencaoDeChamada)
        }

        btSingUpLogin.setOnClickListener {
            val intencaoDeChamada = Intent(this, SignUpActivity::class.java)
            startActivity(intencaoDeChamada)
        }

    }

    private fun login() {
        val login = etUsernameLogin.text.toString()
        val password = etPasswordLogin.text.toString()

        val auth = FirebaseAuth.getInstance()

        val taskDeLogin = auth.signInWithEmailAndPassword(login, password)

        taskDeLogin.addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {
                Toast.makeText(this, "Bem vindo(a) $login", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Usuario ou senha Invalidos", Toast.LENGTH_LONG).show()
            }
        }

    }

}