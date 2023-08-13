package nguyen.dev.kotlinentrancetest.repository.networking

import android.util.Log
import nguyen.dev.kotlinentrancetest.repository.models.ApiResp
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Response<ResponseBody>.handleResp(): ApiResp {
    val resp = this@handleResp
    val respBody = resp.body()?.string()
    Log.d("handleResp", "JsonBody:\n $respBody")
    val result = ApiResp()

    return try {
        val jsonData = JSONObject(respBody)
        result.apply {
            code = resp.code()
            message = resp.message()
            data = jsonData
        }
        result
    } catch (e: Exception) {
        e.printStackTrace()
        result.apply {
            code = -2
            message = "convert data fail"
        }
        result
    }
}

fun Response<ResponseBody>.handleRespArray(): ApiResp {
    val resp = this@handleRespArray
    val respBody = resp.body()?.string()
    Log.d("handleRespArray", "JsonBody:\n $respBody")
    val result = ApiResp()

    return try {
        val jsonData = JSONArray(respBody)
        result.apply {
            code = resp.code()
            message = resp.message()
            arrData = jsonData
        }
        result
    } catch (e: Exception) {
        e.printStackTrace()
        result.apply {
            code = -2
            message = "convert data fail"
        }
        result
    }
}

fun java.lang.Exception.handleApi(): ApiResp {
    return when (this) {
        is HttpException, is UnknownHostException, is SocketTimeoutException -> {
            val api = ApiResp().apply {
                code = -100
                message = this@handleApi.message.toString()
            }
            api
        }
        else -> {
            val api = ApiResp().apply {
                code = -101
                message = this@handleApi.message.toString()
            }
            api
        }
    }
}
