package nguyen.dev.kotlinentrancetest.repository.models

import com.google.gson.annotations.SerializedName

data class UserSignUpReq(
    @SerializedName("firstName")
    var firstName: String="Ngo Dinh",
    @SerializedName("lastName")
    var lastName: String="Khanh Nguyen",
    @SerializedName("email")
    var email: String="",
    @SerializedName("password")
    var password: String=""
)


data class UserSignUpResp(
    @SerializedName("id")
    var id: Int= 0,
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
