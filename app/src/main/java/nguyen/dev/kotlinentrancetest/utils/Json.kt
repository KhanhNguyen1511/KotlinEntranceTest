package nguyen.dev.kotlinentrancetest.utils

import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


private val convertFactory: Gson = Gson()

fun <T> String?.parse(cls: Class<T>): T? {
    if (isNullOrEmpty()) {
        return null
    }
    return try {
        return convertFactory.fromJson(this, cls)
    } catch (ignore: Exception) {
        null
    }
}

@Throws(JSONException::class)
fun JSONObject.toMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()
    val keysItr: Iterator<String> = this.keys()
    while (keysItr.hasNext()) {
        val key = keysItr.next()
        var value: Any = this.get(key)
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        map[key] = value
    }
    return map
}

@Throws(JSONException::class)
fun JSONArray.toList(): List<Any> {
    val list = mutableListOf<Any>()
    for (i in 0 until this.length()) {
        var value: Any = this[i]
        when (value) {
            is JSONArray -> value = value.toList()
            is JSONObject -> value = value.toMap()
        }
        list.add(value)
    }
    return list
}