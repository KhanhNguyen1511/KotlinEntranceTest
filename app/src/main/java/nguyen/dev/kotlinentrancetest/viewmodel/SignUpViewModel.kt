package nguyen.dev.kotlinentrancetest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nguyen.dev.kotlinentrancetest.repository.models.UserSignInReq
import nguyen.dev.kotlinentrancetest.repository.models.UserSignInResp
import nguyen.dev.kotlinentrancetest.repository.models.UserSignUpReq
import nguyen.dev.kotlinentrancetest.repository.models.UserSignUpResp
import nguyen.dev.kotlinentrancetest.repository.repo.UserRepository
import nguyen.dev.kotlinentrancetest.utils.Shared
import nguyen.dev.kotlinentrancetest.utils.parse

class SignUpViewModel: ViewModel() {
    val signUpSuccessLiveData = MutableLiveData<UserSignUpResp?>()
    val signUpFailLiveData = MutableLiveData<String?>()

    val signInSuccessLiveData = MutableLiveData<UserSignInResp?>()
    val signInFailLiveData = MutableLiveData<String?>()

    fun usrSignUp(req: UserSignUpReq) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                UserRepository.signUp(req).collect {
                    val resp = it.data.toString().parse(UserSignUpResp::class.java) ?: UserSignUpResp()
                    signUpSuccessLiveData.postValue(resp)
                }
            }
        } catch (e: Exception) {
            signUpFailLiveData.postValue(e.message)
        }
    }

    fun usrSignIn(req: UserSignInReq) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                UserRepository.signIn(req).collect {
                    val resp = it.data.toString().parse(UserSignInResp::class.java) ?: UserSignInResp()
                    if (resp.accessToken == "") {
                        signInFailLiveData.postValue("Something went wrong")
                    } else {
                        Shared.authToken = resp.accessToken
                        signInSuccessLiveData.postValue(resp)
                    }

                }
            }
        } catch (e: Exception) {
            signInFailLiveData.postValue(e.message)
        }
    }

    fun clear() {
        signUpSuccessLiveData.value = null
        signUpFailLiveData.value = null

        signInSuccessLiveData.value = null
        signInFailLiveData.value = null
    }
}