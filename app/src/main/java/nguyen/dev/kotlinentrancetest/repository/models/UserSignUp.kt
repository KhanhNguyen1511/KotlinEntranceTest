package nguyen.dev.kotlinentrancetest.repository.models

import com.google.gson.annotations.SerializedName

data class UserSignUpReq(
    @SerializedName("firstName")
    var firstName: String="Tester01",
    @SerializedName("lastName")
    var lastName: String="Mr",
    @SerializedName("email")
    var email: String="",
    @SerializedName("password")
    var password: String=""
)


data class UserSignUpResp(
    @SerializedName("id")
    var id: String="",
    @SerializedName("firstName")
    var firstName: String="",
    @SerializedName("lastName")
    var lastName: String="",
    @SerializedName("email")
    var email: String="",
    @SerializedName("createdAt")
    var createdAt: String ="",
    @SerializedName("updatedAt")
    var updatedAt: String ="",
    @SerializedName("role")
    var role: String = ""
)
