package daniel.chatmodel.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.features.login.LoginResult

class LoginViewModel: ViewModel() {
    private val auth = Firebase.auth

    private val mutableSignInResult = MutableLiveData<LoginResult>()
    val signInResult: LiveData<LoginResult> = mutableSignInResult

    private val mutableSignUpResult = MutableLiveData<LoginResult>()
    val signUpResult: LiveData<LoginResult> = mutableSignUpResult

    fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onSignInResult(it) }
    }

    fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { onSignUpResult(it) }
    }

    private fun onSignInResult(task: Task<AuthResult>) {
        if (task.isSuccessful){
            mutableSignInResult.value = LoginResult.SUCCESS
        } else {
            mutableSignInResult.value = LoginResult.FAILURE
        }
    }

    private fun onSignUpResult(task: Task<AuthResult>) {
        if (task.isSuccessful){
            mutableSignUpResult.value = LoginResult.SUCCESS
        } else {
            mutableSignUpResult.value = LoginResult.FAILURE
        }
    }

}