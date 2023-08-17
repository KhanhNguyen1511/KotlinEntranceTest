package nguyen.dev.kotlinentrancetest.repository.networking

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @POST
    suspend fun postApi(
        @Url url: String,
        @Body body: JsonObject?
    ): Response<ResponseBody>

    @GET
    suspend fun getApi(@Url url: String): Response<ResponseBody>
}