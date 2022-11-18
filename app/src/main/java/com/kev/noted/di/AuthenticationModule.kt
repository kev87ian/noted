package com.kev.noted.di

import com.google.firebase.auth.FirebaseAuth
import com.kev.noted.repository.AuthRepository
import com.kev.noted.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
	@Provides
	@Singleton
	fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

	@Provides
	@Singleton
	fun providesAuthRepository(impl: AuthRepositoryImpl) : AuthRepository = impl
}