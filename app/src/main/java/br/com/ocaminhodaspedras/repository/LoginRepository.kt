package br.com.ocaminhodaspedras.repository

import android.content.Context
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.domain.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext val context: Context
) {

    private fun parseResultError(e: Throwable?): LoginResult.Error {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException -> LoginResult.Error(context.getString(R.string.login_invalid_credentials), e)
            is FirebaseAuthInvalidUserException -> {
                val msg = when (e.errorCode) {
                    "ERROR_USER_DISABLED" -> context.getString(R.string.login_user_disabled)
                    "ERROR_USER_NOT_FOUND" -> context.getString(R.string.login_user_not_found)
                    else -> context.getString(R.string.login_invalid_credentials)
                }
                LoginResult.Error(msg, e)
            }
            else -> LoginResult.Error(context.getString(R.string.login_generic_error), e)
        }
    }

    suspend fun login(email: String, pass:String): LoginResult<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.signInWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult.Success()
            } else {
                parseResultError(op.exception)
            }
            nextStep.resume(res)
        }
    }

    suspend fun signUp(email: String, pass:String): LoginResult<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.createUserWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult.Success()
            } else {
                parseResultError(op.exception)
            }
            nextStep.resume(res)
        }
    }

    suspend fun forgotPassword(email: String): LoginResult<Nothing> = suspendCoroutine { nextStep ->
        val operation = auth.sendPasswordResetEmail(email)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult.Success()
            } else {
                parseResultError(op.exception)
            }
            nextStep.resume(res)
        }
    }

}