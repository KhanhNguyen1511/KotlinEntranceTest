package nguyen.dev.kotlinentrancetest.repository.models

import org.json.JSONArray
import org.json.JSONObject

class ApiResp {
    var reqId: String = ""

    var code: Int = -1

    var message: String = ""

    var data: JSONObject? = null

    var arrData: JSONArray?=null

    constructor()

    constructor(reqId: String, code: Int, message: String, data: JSONObject?, arrData: JSONArray?) {
        this.reqId = reqId
        this.code = code
        this.message = message
        this.data = data
        this.arrData = arrData
    }

}