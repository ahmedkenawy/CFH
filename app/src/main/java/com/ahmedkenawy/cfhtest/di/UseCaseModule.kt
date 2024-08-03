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


@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

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