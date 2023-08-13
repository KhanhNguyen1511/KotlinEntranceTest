package nguyen.dev.kotlinentrancetest.repository.models

import com.google.gson.annotations.SerializedName

data class UserSignInReq(
    @SerializedName("email")
    var email: String="",
    @SerializedName("password")
    var password: String =""
)

data class UserSignInResp(
    @SerializedName("user")
    var user: UserData = UserData(),
    @SerializedName("accessToken")
    var accessToken: String = "",
    @SerializedName("refreshToken")
    var refreshToken: String = ""
)

data class UserData(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("email")
    var email: String = "",
    @SerializedName("firstName")
    var firstName: String = "",
    @SerializedName("lastNAme")
    var lastNAme: String = "",
    @SerializedName("createdAt")
    var createdAt: String ="",
    @SerializedName("updatedAt")
    var updatedAt: String ="",
    @SerializedName("role")
    var role: String = ""
)
