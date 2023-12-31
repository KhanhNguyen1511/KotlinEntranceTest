package nguyen.dev.kotlinentrancetest.repository.repo

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nguyen.dev.kotlinentrancetest.repository.models.UserSignInReq
import nguyen.dev.kotlinentrancetest.repository.models.UserSignUpReq
import nguyen.dev.kotlinentrancetest.repository.networking.RestClient
import nguyen.dev.kotlinentrancetest.repository.networking.handleApi
import nguyen.dev.kotlinentrancetest.repository.networking.handleResp

object UserRepository {

    fun signUp(req: UserSignUpReq) = flow {
        try {
            val json = JsonObject().apply {
                this.addProperty("email",req.email)
                this.addProperty("password",req.password)
                this.addProperty("firstName",req.firstName)
                this.addProperty("lastName",req.lastName)
            }
            val resp = RestClient.restClient.postApi("auth/signup",json).handleResp()
            emit(resp)
        } catch (e: Exception) {
            emit(e.handleApi())
        }
    }.flowOn(Dispatchers.IO)

    fun signIn(req: UserSignInReq) = flow {
        try {
            val json = JsonObject().apply {
                this.addProperty("email",req.email)
                this.addProperty("password",req.password)
            }
            val resp = RestClient.restClient.postApi("auth/signin",json).handleResp()
            emit(resp)
        } catch (e: Exception) {
            emit(e.handleApi())
        }
    }.flowOn(Dispatchers.IO)
}