package uz.uchqun.hilt_tutorial2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogCacheEntity: BlogCacheEntity):Long


    @Query("select *from blogs")
    suspend fun get():List<BlogCacheEntity>
}