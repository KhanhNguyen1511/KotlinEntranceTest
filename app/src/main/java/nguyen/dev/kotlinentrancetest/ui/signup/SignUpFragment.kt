package nguyen.dev.kotlinentrancetest.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import nguyen.dev.kotlinentrancetest.MainGraphDirections
import nguyen.dev.kotlinentrancetest.R
import nguyen.dev.kotlinentrancetest.databinding.SignUpViewBinding
import nguyen.dev.kotlinentrancetest.repository.models.UserSignInReq
import nguyen.dev.kotlinentrancetest.repository.models.UserSignUpReq
import nguyen.dev.kotlinentrancetest.utils.setHyperText
import nguyen.dev.kotlinentrancetest.utils.viewModel
import nguyen.dev.kotlinentrancetest.viewmodel.SignUpViewModel

class SignUpFragment: Fragment(R.layout.sign_up_view), SignUpView {
    private var _binding: SignUpViewBinding?=null
    val vb get() = _binding!!
    private val signUpVM by lazy { viewModel(SignUpViewModel::class) }
    private var navController: NavController?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpViewBinding.inflate(inflater, container, false)
        val view = vb.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        observeLiveData()
    }

    private fun observeLiveData() {
        signUpVM.signUpSuccessLiveData.observe(this, Observer {
            it?:return@Observer
            startSignIn()
        })

        signUpVM.signUpFailLiveData.observe(this, Observer {
            it?:return@Observer
            Toast.makeText(this.context, "getListFail - $it", Toast.LENGTH_SHORT).show()
        })

        signUpVM.signInSuccessLiveData.observe(this, Observer {
            it?:return@Observer
            this.navController?.navigate(MainGraphDirections.actionGlobalCategoriesListFragment())
        })

        signUpVM.signInFailLiveData.observe(this, Observer {
            it?:return@Observer
            Toast.makeText(this.context, "getListFail - $it", Toast.LENGTH_SHORT).show()
        })
    }

    private fun startSignIn() {
        val reqSignIn = UserSignInReq().apply {
            this.email = vb.textInputEditTextEmail.text.toString()
            this.password = vb.textInputEditTextPassword.text.toString()
        }
        signUpVM.usrSignIn(reqSignIn)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        clearObservers()
    }

    private fun clearObservers() {
        signUpVM.signInSuccessLiveData.removeObservers(this)
        signUpVM.signInFailLiveData.removeObservers(this)
        signUpVM.signUpSuccessLiveData.removeObservers(this)
        signUpVM.signUpFailLiveData.removeObservers(this)
        signUpVM.clear()
    }

    private fun initViews() {
        vb.viewImage.setImageResource(R.mipmap.img_139)
        vb.viewTextTerms.setHyperText(resources.getString(R.string.app_terms))
        vb.viewSignUpBtn.setOnClickListener {
            validateInput()
        }

        vb.textInputEditTextPassword.doAfterTextChanged {
            validatePassword(it.toString())
        }
        vb.textInputEditTextEmail.doAfterTextChanged {
            validateEmail(it.toString())
        }

    }

    private fun validateInput() {
        if(isDataFilled()) {
            val req = UserSignUpReq().apply {
                this.email = vb.textInputEditTextEmail.text.toString()
                this.password = vb.textInputEditTextPassword.text.toString()
            }
            signUpVM.userSignUp(req)
        } else {
            Toast.makeText(this.context, "Make sure all data is filled and have checked over 16", Toast.LENGTH_SHORT).show()
        }

    }


}