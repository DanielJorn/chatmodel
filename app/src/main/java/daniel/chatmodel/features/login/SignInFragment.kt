package daniel.chatmodel.features.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(R.layout.fragment_sign_in){
    private lateinit var auth: FirebaseAuth
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        viewModel.signInResult.observe(viewLifecycleOwner) { onSignInResult(it) }
        btn_sign_in.setOnClickListener { onSignInClicked() }
        btn_go_to_sign_up.setOnClickListener { goToSignUp() }
    }

    private fun onSignInResult(loginResult: LoginResult) {
        when(loginResult){
            LoginResult.SUCCESS -> goToMain()
            LoginResult.FAILURE -> signInFailed()
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToMain()
        }
    }

    private fun signInFailed() {
        Toast.makeText(activity, "Sign in failed", Toast.LENGTH_LONG).show()
    }

    private fun onSignInClicked() {
        val email = et_sign_in_enter_email.text.toString()
        val password = et_sign_in_enter_password.text.toString()
        viewModel.signIn(email, password)
    }

    private fun goToSignUp() {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun goToMain() {
        Toast.makeText(activity, "Successfully signed in", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_signInFragment_to_main_graph)
    }
 }