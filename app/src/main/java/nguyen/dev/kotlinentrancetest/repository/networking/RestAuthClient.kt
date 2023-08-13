package nguyen.dev.kotlinentrancetest.repository.networking

import nguyen.dev.kotlinentrancetest.utils.Shared

object RestAuthClient {
    val rest: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Environment.getRetrofit {
            addInterceptor {
                val org = it.request()
                val requestBuilder = org.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization","Bearer ${Shared.authToken}")
                val request = requestBuilder.build()
                it.proceed(request)
            }
        }.create(ApiService::class.java)
    }
}