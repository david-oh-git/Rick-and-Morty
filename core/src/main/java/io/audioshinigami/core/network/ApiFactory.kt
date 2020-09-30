package io.audioshinigami.core.network

import io.audioshinigami.core.BuildConfig
import io.audioshinigami.core.network.services.RickAndMortyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val API_BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     *  Provider method for [HttpLoggingInterceptor] for HTTP client
     *
     *  @return Instance of http interceptor
     */
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     *  Provides instance of [OkHttpClient]
     *
     *  @return Instance of http client
     */
    private fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Provider method for [Retrofit]
     *
     * @return Instance of retrofit
     */
    private fun provideRetrofitBuilder() =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient(provideHttpLoggingInterceptor()))
            .build()

    fun provideRickAndMortyService() =
        provideRetrofitBuilder().create(RickAndMortyService::class.java)
}
