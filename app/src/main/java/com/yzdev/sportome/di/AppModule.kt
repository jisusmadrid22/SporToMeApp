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
import com.yzdev.sportome.domain.use_case.favoriteCompetition.CompetitionUseCaseFormat
import com.yzdev.sportome.domain.use_case.favoriteCompetition.deleteFavoriteCompetition.DeleteFavoriteCompetitionUseCase
import com.yzdev.sportome.domain.use_case.favoriteCompetition.getAllFavoriteCompetition.GetAllLocalFavoriteCompetitionUseCase
import com.yzdev.sportome.domain.use_case.favoriteCompetition.getLocalFavoriteCompetitionById.GetFavoriteCompetitionUseCase
import com.yzdev.sportome.domain.use_case.favoriteCompetition.insertFavoriteCompetition.InsertFavoriteCompetitionUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.TeamUseCaseFormat
import com.yzdev.sportome.domain.use_case.favoriteTeam.deleteFavoriteTeam.DeleteFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.getAllFavoriteTeam.GetAllLocalFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.getLocalFavoriteTeamById.GetFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.insertFavoriteTeam.InsertFavoriteTeamUseCase
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

    //USES CASES FORMAT
    @Provides
    @Singleton
    fun provideCompetitionUseCases(repository: AppRepository): CompetitionUseCaseFormat {
        return CompetitionUseCaseFormat(
            insertFavoriteCompetition = InsertFavoriteCompetitionUseCase(repository),
            getAllLocalFavoriteCompetitionUseCase = GetAllLocalFavoriteCompetitionUseCase(repository),
            getFavoriteCompetitionUseCase = GetFavoriteCompetitionUseCase(repository),
            deleteFavoriteCompetitionUseCase = DeleteFavoriteCompetitionUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTeamUseCases(repository: AppRepository): TeamUseCaseFormat {
        return TeamUseCaseFormat(
            insertFavoriteTeamUseCase = InsertFavoriteTeamUseCase(repository),
            getAllLocalFavoriteTeamUseCase = GetAllLocalFavoriteTeamUseCase(repository),
            getFavoriteTeamUseCase = GetFavoriteTeamUseCase(repository),
            deleteFavoriteTeamUseCase = DeleteFavoriteTeamUseCase(repository)
        )
    }

}