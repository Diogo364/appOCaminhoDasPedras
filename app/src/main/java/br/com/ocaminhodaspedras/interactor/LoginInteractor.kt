package br.com.ocaminhodaspedras.interactor

import android.app.Application
import android.util.Patterns
import br.com.ocaminhodaspedras.R
import br.com.ocaminhodaspedras.domain.LoginResult
import br.com.ocaminhodaspedras.repository.LoginRepository
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val repo: LoginRepository,
    private val app: Application,
) {

    private fun validateEmail(email: String?): Pair<Boolean, Throwable?> {

        if (email.isNullOrBlank()) return Pair(false, Exception(app.getString(R.string.email_required)))

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(false, Exception(app.getString(R.string.invalid_email)))
        }

        return Pair(true, null)
    }

    private fun validatePassword(password: String?): Pair<Boolean, Throwable?> {

        if (password.isNullOrBlank()) return Pair(false, Exception(app.getString(R.string.password_required)))

        if (password.length < 8) {
            return Pair(false, Exception(app.getString(R.string.password_minimum_length)))
        }

        return Pair(true, null)
    }

    private fun validatePasswordCheck(
        passwordCheck: String?,
        password: String
    ): Pair<Boolean, Throwable?> {
        if (password != passwordCheck){
            return Pair(false, Exception(app.getString(R.string.password_check_not_equal)))
        }
        return Pair(true, null)
    }

    private fun validateLoginInformation(
        email: String?,
        password: String?
    ): Pair<Boolean, Throwable?>{
        val (validEmail, emailException) = validateEmail(email)
        if (!validEmail) {
            return Pair(false, emailException)
        }

        val (validPassword, passwordException) = validatePassword(password)
        if (!validPassword) {
            return Pair(false, passwordException)
        }

        return Pair(true, null)
    }

    private fun validateSignUpInformation(
        email: String?,
        password: String?,
        passwordCheck: String?
    ): Pair<Boolean, Throwable?>{
        val (validEmail, emailException) = validateEmail(email)
        if (!validEmail) {
            return Pair(false, emailException)
        }

        val (validPassword, passwordException) = validatePassword(password)
        if (!validPassword) {
            return Pair(false, passwordException)
        }

        val (validPasswordCheck, passwordCheckException) = validatePasswordCheck(passwordCheck, password!!)
        if (!validPasswordCheck) {
            return Pair(false, passwordCheckException)
        }

        return Pair(true, null)
    }


    suspend fun login(
        email: String?,
        password: String?
    ): LoginResult<Nothing> {
        val (_, ex) = validateLoginInformation(email, password)

        return when (ex) {
            null -> repo.login(email!!, password!!)
            else -> LoginResult.Error(ex.localizedMessage, ex)
        }
    }

    suspend fun signUp(
        email: String?,
        password: String?,
        passwordCheck: String?
    ): LoginResult<Nothing> {
        val (_, ex) = validateSignUpInformation(email, password, passwordCheck)

        return when (ex) {
            null -> repo.signUp(email!!, password!!)
            else -> LoginResult.Error(ex.localizedMessage, ex)
        }
    }

    suspend fun forgotPassword(
        email: String?
    ): LoginResult<Nothing> {
        val(_, ex) = validateEmail(email)

        return when(ex){
            null -> repo.forgotPassword(email!!)
            else -> LoginResult.Error(ex.localizedMessage, ex)
        }
    }
}