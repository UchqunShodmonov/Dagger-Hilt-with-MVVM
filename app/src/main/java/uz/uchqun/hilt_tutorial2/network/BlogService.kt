package uz.uchqun.hilt_tutorial2.network

import retrofit2.http.GET

interface BlogService {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}