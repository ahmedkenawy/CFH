package com.ahmedkenawy.cfhtest.di

import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.AuthUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sign_up_use_case.SignUpUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.auth_use_case.sing_in_use_case.SignInUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.MainUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.home_use_case.HomeUseCase
import com.ahmedkenawy.cfhtest.domain.use_case.main_use_case.profile_use_case.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing UseCase instances.
 * This module provides singleton instances of use cases for authentication and main functionalities.
 */
@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    /**
     * Provides a singleton instance of [AuthUseCase].
     *
     * @param signInUseCase An instance of [SignInUseCase] used for sign-in operations.
     * @param signUpUseCase An instance of [SignUpUseCase] used for sign-up operations.
     * @return A singleton instance of [AuthUseCase] that aggregates [SignInUseCase] and [SignUpUseCase].
     */
    @Provides
    @Singleton
    fun provideAuthUseCase(
        signInUseCase: SignInUseCase,
        signUpUseCase: SignUpUseCase,
    ): AuthUseCase {
        return AuthUseCase(
            signInUseCase,
            signUpUseCase
        )
    }

    /**
     * Provides a singleton instance of [MainUseCase].
     *
     * @param profileUseCase An instance of [ProfileUseCase] used for profile-related operations.
     * @param homeUseCase An instance of [HomeUseCase] used for home-related operations.
     * @return A singleton instance of [MainUseCase] that aggregates [ProfileUseCase] and [HomeUseCase].
     */
    @Provides
    @Singleton
    fun provideMainUseCase(
        profileUseCase: ProfileUseCase,
        homeUseCase: HomeUseCase,
    ): MainUseCase {
        return MainUseCase(
            profileUseCase,
            homeUseCase
        )
    }
}
