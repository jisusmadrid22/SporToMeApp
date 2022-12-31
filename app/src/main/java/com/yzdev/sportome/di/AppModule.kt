package com.yzdev.sportome.di

import android.app.Application
import androidx.room.Room
import com.yzdev.sportome.common.Constant
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.data_source.AppDatabase
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.data.remote.InterceptorClient
import com.yzdev.sportome.data.repository.AppRepositoryImp
import com.yzdev.sportome.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //DB
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    //API
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = Constant.BASE_URL

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(InterceptorClient())
    }.build()

    @Provides
    @Singleton
    fun provideApi(@Named("BaseUrl") baseUrl: String): ApiService{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    //REPOS
    @Provides
    @Singleton
    fun provideAppRepo(api: ApiService, appDb: AppDatabase): AppRepository{
        return AppRepositoryImp(
            dao = appDb.appDao,
            api = api
        )
    }

}