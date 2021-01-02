package uz.uchqun.hilt_tutorial2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import uz.uchqun.hilt_tutorial2.db.BlogDao
import uz.uchqun.hilt_tutorial2.db.CacheMapper
import uz.uchqun.hilt_tutorial2.network.BlogService
import uz.uchqun.hilt_tutorial2.network.NetworkMapper
import uz.uchqun.hilt_tutorial2.repository.MainRepository
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}