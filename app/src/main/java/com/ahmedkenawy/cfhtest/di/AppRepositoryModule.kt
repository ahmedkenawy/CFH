package com.ahmedkenawy.cfhtest.di

import com.ahmedkenawy.cfhtest.data.local.DataStorePreference
import com.ahmedkenawy.cfhtest.data.remote.ApiService
import com.ahmedkenawy.cfhtest.data.repository.AppRepositoryImp
import com.ahmedkenawy.cfhtest.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        dataStorePreference: DataStorePreference,
        apiService: ApiService
    ): AppRepository {
        return AppRepositoryImp(dataStorePreference, apiService)
    }
}