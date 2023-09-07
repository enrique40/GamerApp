package com.example.gamerapp.di

import com.example.gamerapp.data.repository.AuthRepositoryImpl
import com.example.gamerapp.domain.repository.AuthRespository
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.auth.GetCurrentUser
import com.example.gamerapp.domain.use_cases.auth.Login
import com.example.gamerapp.domain.use_cases.auth.Logout
import com.example.gamerapp.domain.use_cases.auth.Signup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providerFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providerUserRef(db: FirebaseFirestore)
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(impl: AuthRepositoryImpl): AuthRespository = impl

    @Provides
    fun providerAuthUseCase(respository: AuthRespository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(respository),
        login = Login(respository),
        logout = Logout(respository),
        signup = Signup(respository)
    )
}