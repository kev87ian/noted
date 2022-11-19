package com.kev.noted.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.kev.noted.db.NotesDao
import com.kev.noted.db.NotesDatabase
import com.kev.noted.repository.AuthRepository
import com.kev.noted.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {
	@Provides
	@Singleton
	fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

	@Provides
	@Singleton
	fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

	@Provides
	@Singleton
	fun providesNotesDao(notesDatabase: NotesDatabase): NotesDao {
		return notesDatabase.notesDao()
	}

	@Provides
	@Singleton
	fun providesNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
		return NotesDatabase.getDbInstance(context)
	}
}