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

/**
 * Dagger module for providing repository instances.
 * This module is used to provide dependencies related to repositories.
 */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    /**
     * Provides an instance of [AppRepository].
     *
     * @param dataStorePreference An instance of [DataStorePreference] used for local data operations.
     * @param apiService An instance of [ApiService] used for remote API operations.
     * @return An instance of [AppRepository] implemented by [AppRepositoryImp].
     */
    @Provides
    @Singleton
    fun provideUserRepository(
        dataStorePreference: DataStorePreference,
        apiService: ApiService
    ): AppRepository {
        return AppRepositoryImp(dataStorePreference, apiService)
    }
}
