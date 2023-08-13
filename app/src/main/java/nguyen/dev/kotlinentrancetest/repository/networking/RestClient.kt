package nguyen.dev.kotlinentrancetest.repository.networking

import nguyen.dev.kotlinentrancetest.repository.networking.Environment.getRetrofit

object RestClient {
    val restClient: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit {
            addInterceptor {
                val org = it.request()
                val requestBuilder = org.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                val request = requestBuilder.build()
                it.proceed(request)
            }
        }.create(ApiService::class.java)
    }
}