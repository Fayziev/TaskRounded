package uz.gita.taskrounded.data

import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.taskrounded.BuildConfig
import uz.gita.taskrounded.app.App

object ApiClient {




      var  retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()


    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addLogging()
            .build()
    }

    private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
        HttpLoggingInterceptor.Level.BODY
        if (BuildConfig.LOGGING) {
            addInterceptor(ChuckInterceptor(App.instance))
        }
        return this
    }
}