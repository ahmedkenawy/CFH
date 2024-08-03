package com.ahmedkenawy.cfhtest.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.ahmedkenawy.cfhtest.utils.Constants.DATA_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing DataStore instances.
 * This module provides a singleton instance of DataStore for managing preferences.
 */
@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    /**
     * Provides a singleton instance of [DataStore] for [Preferences].
     *
     * @param context An [ApplicationContext] used to create the DataStore.
     * @return A singleton instance of [DataStore] configured with the name defined in [DATA_STORE_NAME].
     */
    @Provides
    @Singleton
    fun provideDataStorePreference(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.createDataStore(
            name = DATA_STORE_NAME
        )
    }
}
