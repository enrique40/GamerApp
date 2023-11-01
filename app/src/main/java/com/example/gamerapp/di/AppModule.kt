package com.example.gamerapp.di

import com.example.gamerapp.core.Constants.USERS
import com.example.gamerapp.data.repository.AuthRepositoryImpl
import com.example.gamerapp.data.repository.UsersRepositoryImpl
import com.example.gamerapp.domain.repository.AuthRespository
import com.example.gamerapp.domain.repository.UsersRepository
import com.example.gamerapp.domain.use_cases.auth.AuthUseCases
import com.example.gamerapp.domain.use_cases.auth.GetCurrentUser
import com.example.gamerapp.domain.use_cases.auth.Login
import com.example.gamerapp.domain.use_cases.auth.Logout
import com.example.gamerapp.domain.use_cases.auth.Signup
import com.example.gamerapp.domain.use_cases.users.Create
import com.example.gamerapp.domain.use_cases.users.GetUserById
import com.example.gamerapp.domain.use_cases.users.SaveImage
import com.example.gamerapp.domain.use_cases.users.Update
import com.example.gamerapp.domain.use_cases.users.UsersUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()
    @Provides
    fun providerStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    fun providerUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(impl: AuthRepositoryImpl): AuthRespository = impl

    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providerAuthUseCase(respository: AuthRespository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(respository),
        login = Login(respository),
        logout = Logout(respository),
        signup = Signup(respository)
    )

    @Provides
    fun provideUsersUseCases(respository: UsersRepository) = UsersUseCase(
        create = Create(respository),
        getUserById = GetUserById(respository),
        update = Update(respository),
        saveImage = SaveImage(respository)
    )
}