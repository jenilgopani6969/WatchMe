package worldimage.watchme.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import worldimage.watchme.data.repository.MovieRepositoryImpl
import worldimage.watchme.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}