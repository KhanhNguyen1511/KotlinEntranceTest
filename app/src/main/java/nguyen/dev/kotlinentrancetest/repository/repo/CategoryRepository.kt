package nguyen.dev.kotlinentrancetest.repository.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nguyen.dev.kotlinentrancetest.repository.networking.RestAuthClient
import nguyen.dev.kotlinentrancetest.repository.networking.handleApi
import nguyen.dev.kotlinentrancetest.repository.networking.handleRespArray

object CategoryRepository {
    fun getListCategory() = flow {
        try {
            val resp = RestAuthClient.rest.getApi("categories").handleRespArray()
            emit(resp)
        } catch (e: Exception) {
            emit(e.handleApi())
        }
    }.flowOn(Dispatchers.IO)

}