package nguyen.dev.kotlinentrancetest.repository.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("createdAt")
    var createdAt: String = "",
    @SerializedName("updatedAt")
    var updatedAt: String = ""

)

data class ListCategories(
    @SerializedName("array")
    var list: List<Category> = listOf()
)

fun fromJson(json: String): ListCategories {
    return Gson().fromJson(json, ListCategories::class.java)
}