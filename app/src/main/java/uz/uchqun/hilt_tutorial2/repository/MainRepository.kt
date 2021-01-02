package uz.uchqun.hilt_tutorial2.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.uchqun.hilt_tutorial2.db.BlogDao
import uz.uchqun.hilt_tutorial2.db.CacheMapper
import uz.uchqun.hilt_tutorial2.model.Blog
import uz.uchqun.hilt_tutorial2.network.BlogService
import uz.uchqun.hilt_tutorial2.network.NetworkMapper
import uz.uchqun.hilt_tutorial2.utils.DataState

class MainRepository constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {


    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)

            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()

            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}